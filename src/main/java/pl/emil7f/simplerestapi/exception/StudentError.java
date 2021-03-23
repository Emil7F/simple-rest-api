package pl.emil7f.simplerestapi.exception;

public enum StudentError {

    STUDENT_NOT_FOUND("Student does not exists"),
    STUDENT_EMAIL_ALREADY_EXIST("Student email already exist"),
    STUDENT_IS_NOT_ACTIVE("Student is not active");

    private String message;

    StudentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
