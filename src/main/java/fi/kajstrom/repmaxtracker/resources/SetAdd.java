package fi.kajstrom.repmaxtracker.resources;

import java.util.Date;

public class SetAdd {
    private final long exerciseId;
    private final long userId;
    private final Date performedOn;
    private final Double weight;
    private final Integer repetitions;

    public SetAdd(long exerciseId, long userId, Date performedOn, Double weight, Integer repetitions) {
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.performedOn = performedOn;
        this.weight = weight;
        this.repetitions = repetitions;
    }

    public SetAdd() {
        exerciseId = 0;
        userId = 0;
        performedOn = null;
        weight = 0.0;
        repetitions = 0;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public long getUserId() {
        return userId;
    }

    public Date getPerformedOn() {
        return performedOn;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }
}
