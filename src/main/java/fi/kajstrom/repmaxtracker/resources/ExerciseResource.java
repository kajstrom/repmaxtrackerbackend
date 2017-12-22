package fi.kajstrom.repmaxtracker.resources;

public class ExerciseResource {
    private final long id;
    private final String name;

    public ExerciseResource(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExerciseResource() {
        id = 0;
        name = "";
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
