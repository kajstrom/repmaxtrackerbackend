package fi.kajstrom.repmaxtracker.resources;


public class AddGreeting {
    private final String name;

    public AddGreeting(String name) {
        this.name = name;
    }

    public AddGreeting() {
        name = "";
    }

    public String getName() {
        return name;
    }
}
