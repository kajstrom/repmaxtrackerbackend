package fi.kajstrom.repmaxtracker.resources;


import fi.kajstrom.repmaxtracker.domain.Set;
import fi.kajstrom.repmaxtracker.domain.SetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sets")
public class SetController {
    private static final Logger log = LoggerFactory.getLogger(SetController.class);

    @Autowired
    private SetService setService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSet(@RequestBody SetAddResource set) {
        Integer setId = setService.addSet(
                set.getExerciseId(),
                set.getUserId(),
                set.getPerformedOn(),
                set.getWeight(),
                set.getRepetitions()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{set_id}")
                .buildAndExpand(setId).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<SetResource> getSets() {
        return setService.allSets()
                .stream()
                .map((set) -> {
                    return new SetResource(
                            set.getSetId(),
                            set.getExerciseId(),
                            set.getUserId(),
                            set.getPerformedOn(),
                            set.getWeight(),
                            set.getRepetitions(),
                            set.getEstimated1Rm()
                    );
                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/{setId}")
    public @ResponseBody SetResource getSet(@PathVariable(value = "setId") String reqSetId) throws ResourceNotFoundException {
        long setId = Long.parseLong(reqSetId);

        Set set = setService.getSet(setId);

        if (set == null) {
            throw new ResourceNotFoundException(String.format("No set found with id: %s", setId));
        }

        return setToSetResource(set);
    }

    private SetResource setToSetResource(Set set) {
        return new SetResource(
                set.getSetId(),
                set.getExerciseId(),
                set.getUserId(),
                set.getPerformedOn(),
                set.getWeight(),
                set.getRepetitions(),
                set.getEstimated1Rm()
        );
    }
}
