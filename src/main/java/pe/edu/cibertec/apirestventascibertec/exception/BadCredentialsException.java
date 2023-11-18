package pe.edu.cibertec.apirestventascibertec.exception;

public class BadCredentialsException extends RuntimeException  {
    public BadCredentialsException (String message){
        super(message);
    }
}
