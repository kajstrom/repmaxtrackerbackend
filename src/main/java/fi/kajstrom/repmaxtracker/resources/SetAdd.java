package fi.kajstrom.repmaxtracker.resources;

import java.util.Date;

public class SetAdd {
    private final long exerciseId;
    private final long userId;
    private final Date perfomedOn;
    private final Double weight;
    private final Integer repetitions;

    public SetAdd(long exerciseId, long userId, Date perfomedOn, Double weight, Integer repetitions) {
        this.exerciseId = exerciseId;
        this.userId = userId;
        this.perfomedOn = perfomedOn;
        this.weight = weight;
        this.repetitions = repetitions;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public long getUserId() {
        return userId;
    }

    public Date getPerfomedOn() {
        return perfomedOn;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }
}
