package fi.kajstrom.repmaxtracker.resources;

import fi.kajstrom.repmaxtracker.domain.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    ExerciseService exerciseService;
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<ExerciseResource> all() {
        return exerciseService.getAll()
                .stream()
                .map((exercise) -> {
                    return new ExerciseResource(
                            exercise.getExerciseId(),
                            exercise.getName()
                    );
                })
                .collect(Collectors.toList());
    }
}
