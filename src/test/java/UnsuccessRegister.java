public class UnsuccessRegister {

    private static String error;

    private UnsuccessRegister() {
    }

    public UnsuccessRegister(String error) {
        this.error = error;
    }

    public static String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
