package pe.edu.cibertec.apirestventascibertec.exception;

public class MaxUploadSizeExceededException extends RuntimeException {

    public MaxUploadSizeExceededException(String message){
        super(message);
    }
}
