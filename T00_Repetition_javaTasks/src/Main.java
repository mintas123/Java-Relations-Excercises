public class Main {
    public static void main(String[] args) {

        try {
            doStuff();
        }
        catch (Exception e) {
            throw new YourMomFatException(
                "You suck", e);
            }

        }



    public static void doStuff() {

    }
}
