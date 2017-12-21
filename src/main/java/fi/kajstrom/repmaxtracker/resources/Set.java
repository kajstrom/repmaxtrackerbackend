package fi.kajstrom.repmaxtracker.resources;

import java.util.Date;

public class Set {
    private final long setId;
    private final long exerciseId;
    private final long userId;
    private final Date performedOn;
    private final Double weight;
    private final Integer repetitions;
    private final Double estimated1Rm;

    public Set(long setId, long exerciseId, long userId, Date performedOn, Double weight, Integer repetitions, Double estimated1Rm) {
        this.setId = setId;
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.performedOn = performedOn;
        this.weight = weight;
        this.repetitions = repetitions;
        this.estimated1Rm = estimated1Rm;
    }

    public Set() {
        setId = 0;
        exerciseId = 0;
        userId = 0;
        performedOn = null;
        weight = 0.0;
        repetitions = 0;
        estimated1Rm = 0.0;
    }

    public long getSetId() {
        return setId;
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

    public Double getEstimated1Rm() {
        return estimated1Rm;
    }
}
