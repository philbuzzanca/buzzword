package sample;

/**
 * Created by Phil on 11/26/2016.
 */
public class CreateProfileException extends Exception {
    public CreateProfileException(String message){
        super(message);
    }
    public CreateProfileException(String message, Throwable throwable){
        super(message, throwable);
    }
}
