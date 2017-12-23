package fi.kajstrom.repmaxtracker.domain;

import java.util.Date;

public class Set {
    private long setId;
    private long exerciseId;
    private long userId;
    private Date performedOn;
    private Double weight;
    private Integer repetitions;
    private Double estimated1Rm;

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

    public Set setSetId(long setId) {
        this.setId = setId;
        return this;
    }

    public Set setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
        return this;
    }

    public Set setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public Set setPerformedOn(Date performedOn) {
        this.performedOn = performedOn;
        return this;
    }

    public Set setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Set setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
        return this;
    }

    public Set setEstimated1Rm(Double estimated1Rm) {
        this.estimated1Rm = estimated1Rm;
        return this;
    }
}
