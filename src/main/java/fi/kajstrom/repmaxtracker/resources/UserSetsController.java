package fi.kajstrom.repmaxtracker.resources;

import fi.kajstrom.repmaxtracker.domain.Set;
import fi.kajstrom.repmaxtracker.domain.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/sets")
public class UserSetsController {
    @Autowired
    private SetService setService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<SetResource> getUserSets(@PathVariable(value="userId") String reqUserId) {
        Long userId = Long.parseLong(reqUserId);

        return setService.getUserSets(userId)
                .stream()
                .map((Set set) -> {
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
}
