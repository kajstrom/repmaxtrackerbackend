package fi.kajstrom.repmaxtracker.resources;

public class ErrorResource {
    private final int errorCode;
    private final String errorMessage;

    public ErrorResource(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResource() {
        errorCode = 0;
        errorMessage = "";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
