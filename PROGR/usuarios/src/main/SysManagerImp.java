package main;

import menu.Menu;

import java.io.*;

public class SysManagerImp implements SysManager {

    private final String USER_FILE_PATH = "./src/users.txt";


    @Override
    public void addUser() {

    }

    @Override
    public boolean checkPassword(String user, String password) {
        /*TODO hacer de nuevo la implementación
        boolean res = false;
        try (FileReader reader = new FileReader(USER_FILE_PATH)) {
            int nextChar;
            String dataList = new;

            while((nextChar = reader.read()) != -1) {
                String nextUser;
                StringBuilder nUserBuilder = new StringBuilder();
                if(nextChar == '[') {
                    //Loop to read user entry starting at [
                    while((char)(nextChar = reader.read()) != ']') {

                        if ((char)nextChar != ',') {
                            nUserBuilder.append((char)nextChar);
                        } else {

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
        */
        return true;
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
