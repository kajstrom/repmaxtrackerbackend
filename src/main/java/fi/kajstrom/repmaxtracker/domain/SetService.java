package fi.kajstrom.repmaxtracker.domain;

import fi.kajstrom.repmaxtracker.infrastructure.database.SetGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("setService")
public class SetService {

    private SetGateway setGateway;

    @Autowired
    public SetService(SetGateway setGateway) {
        this.setGateway = setGateway;
    }

    public Integer addSet(long exerciseId, long userId, Date performedOn, Double weight, Integer repetitions) {
        Set set = new Set();
        Double estimated1Rm = calculate1Rm(weight, repetitions);

        set.setExerciseId(exerciseId)
                .setUserId(userId)
                .setPerformedOn(performedOn)
                .setWeight(weight)
                .setRepetitions(repetitions)
                .setEstimated1Rm(estimated1Rm);

        return setGateway.addSet(set);
    }

    private Double calculate1Rm(Double weight, Integer repetitions) {
        return (weight * repetitions * 0.0333) + weight;
    }
}
