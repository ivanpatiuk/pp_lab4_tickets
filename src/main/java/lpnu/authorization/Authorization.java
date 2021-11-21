package lpnu.authorization;

public final class Authorization {

    private static Authorization instance;

    private static String password = "admin";
    private static boolean authorized = false;

    private Authorization() {
    }

    public static Authorization getInstance() {
        if (instance == null) {
            instance = new Authorization();
        }
        return instance;
    }


    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        password = password;
    }

    public static boolean isAutorized() {
        return authorized;
    }

    public static void setAutorized(boolean autorized) {
        authorized = autorized;
    }
}
