package main;

import menu.Menu;

import java.io.*;

public class SysManagerImp implements SysManager {

    private final String USER_FILE_PATH = "./src/users.txt";


    @Override
    public void addUser() {
        try (ObjectOutputStream oos = new ObjectOutputStream()) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkPassword(String userdatum, String s) {
        return false;
    }

    @Override
    public String getUsersFilePath() {
        return null;
    }

    public void checkUsersFile() {
        File users = new File(USER_FILE_PATH);
        try {
            //IF users file already exists, shout check
            if (!users.createNewFile()) {
                Menu.fileOK();
            //ELSE create a new file
            } else {
                Menu.fileMissing();
                createUsersFile();
                Menu.fileCreated();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUsersFile() {
        try (FileWriter writer = new FileWriter(USER_FILE_PATH)) {
            writer.write("""
                    # Lines starting with # will be ignored
                    # Syntax [username,email,password]
                    # 2021 all rights reserved (?)
                    """);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
