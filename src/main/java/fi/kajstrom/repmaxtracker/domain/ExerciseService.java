package fi.kajstrom.repmaxtracker.domain;

import fi.kajstrom.repmaxtracker.infrastructure.database.ExerciseGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("exerciseService")
public class ExerciseService {
    private ExerciseGateway exerciseGateway;

    @Autowired
    public ExerciseService(ExerciseGateway exerciseGateway) {
        this.exerciseGateway = exerciseGateway;
    }

    public List<Exercise> getAll() {
        return exerciseGateway.getAll();
    }
}
