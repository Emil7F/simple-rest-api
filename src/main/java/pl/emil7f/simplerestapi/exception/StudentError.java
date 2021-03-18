package pl.emil7f.simplerestapi.exception;

public enum StudentError {

    STUDENT_NOT_FOUND("Student does not exists");

    private String message;

    StudentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
