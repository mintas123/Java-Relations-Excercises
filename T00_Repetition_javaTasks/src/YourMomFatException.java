public class YourMomFatException extends RuntimeException {

private Exception error;

    public YourMomFatException(String error,Throwable err) {
        super(error, err);
    }
}
