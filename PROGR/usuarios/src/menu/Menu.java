package menu;

import classes.UserImp;

public class Menu {

    private static final String MAIN_MENU = """
            Select an option:
            [1] Log In
            [2] Sign In
            [3] Exit
            """;

    private static final String ASK_USER_EMAIL = "Type a username/email:";
    private static final String ASK_USER = "Type an username:";
    private static final String ASK_EMAIL = "Type an email:";
    private static final String ASK_PASSWORD = "Type a password:";

    public static char mainMenu() {
        System.out.println(MAIN_MENU);
        return Utilidades.pedirCadena().charAt(0);
    }

    //Returns an array where [0] is the user/email and [1] is the password
    //For Manager to search coincidences
    public static String[] askUserData() {
        String[] loginData = new String[2];

        System.out.println(ASK_USER_EMAIL);
        loginData[0] = Utilidades.pedirCadena();

        System.out.println(ASK_PASSWORD);
        loginData[1] = Utilidades.pedirCadena();

        return loginData;
    }

    public static UserImp askSignIn() {
        String username;
        String email;
        String password;

        System.out.println(ASK_USER);
        username = Utilidades.pedirCadena();
        System.out.println(ASK_EMAIL);
        email = Utilidades.pedirCadena();
        System.out.println(ASK_PASSWORD);
        password = Utilidades.pedirCadena();

        System.out.println("User: "+username+"\n" +
                "Email: "+email+"\n" +
                "Password: "+password+"\n");

        return new UserImp(username,email,password);
    }

    public static void fileOK() {
        System.out.println("Users file is correct, proceeding...");
    }

    public static void fileMissing() {
        System.out.println("""
                Missing users info file, might be a problem.
                Creating a new one...
                """);
    }

    public static void fileCreated() {
        System.out.println("""
                Users file successfully created.
                """);
    }
}
