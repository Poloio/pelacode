package main;

import menu.Menu;

public class Main {
    public static void main(String[] args) {
        SysManagerImp manager = new SysManagerImp();
        manager.checkUsersFile();

        boolean exit = false;
        do {
            char option = Menu.mainMenu();
            switch (option) {
                case 1:
                    String[] userdata = Menu.askUserData();
                    manager.checkPassword(userdata[0],userdata[1]);
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
            }
        } while (!exit);
    }

    private static void signIn() {
    }
}
