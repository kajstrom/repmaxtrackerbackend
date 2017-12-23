package fi.kajstrom.repmaxtracker.domain;

public class Exercise {
    private long exerciseId;
    private String name;

    public Exercise(long exerciseId, String name) {
        this.exerciseId = exerciseId;
        this.name = name;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }
}
