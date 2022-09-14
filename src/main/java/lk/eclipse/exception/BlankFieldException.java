package lk.eclipse.exception;

public class BlankFieldException extends InvalidRoleException{
    public BlankFieldException(String field) {
        super(field);
    }

    public BlankFieldException(String message, String field) {
        super(message, field);
    }

    public BlankFieldException(String message, Throwable cause, String field) {
        super(message, cause, field);
    }

    public BlankFieldException(Throwable cause, String field) {
        super(cause, field);
    }
}
