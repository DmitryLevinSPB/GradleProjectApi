public class SuccessRegister {
    private static Integer id;
    private static String token;

    public SuccessRegister(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessRegister() {
    }

    public static Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
