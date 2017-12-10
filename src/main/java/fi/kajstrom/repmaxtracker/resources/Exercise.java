package fi.kajstrom.repmaxtracker.resources;

public class Exercise {
    private final long id;
    private final String name;

    public Exercise(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Exercise() {
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
