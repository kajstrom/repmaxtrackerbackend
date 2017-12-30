package fi.kajstrom.repmaxtracker.resources;

import fi.kajstrom.repmaxtracker.domain.Set;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SetResource {
    private final long setId;
    private final long exerciseId;
    private final long userId;
    private final Date performedOn;
    private final Double weight;
    private final Integer repetitions;
    private final Double estimated1Rm;

    @NotNull
    public static SetResource from(Set set) {
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

    public SetResource(long setId, long exerciseId, long userId, Date performedOn, Double weight, Integer repetitions, Double estimated1Rm) {
        this.setId = setId;
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.performedOn = performedOn;
        this.weight = weight;
        this.repetitions = repetitions;
        this.estimated1Rm = estimated1Rm;
    }

    public SetResource() {
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
