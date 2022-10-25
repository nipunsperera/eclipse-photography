package lk.eclipse.exception;

public class InvalidRoleException extends Exception {
    private String field;

    public InvalidRoleException(String field) {
        super();
        this.field = field;
    }

    public InvalidRoleException(String message, String field) {
        super(message);
        this.field = field;
    }

    public InvalidRoleException(String message, Throwable cause,String field) {
        super(message, cause);
        this.field = field;
    }

    public InvalidRoleException(Throwable cause,String field) {
        super(cause);
        this.field = field;
    }

    public String getField(){
        return field;
    }
}
