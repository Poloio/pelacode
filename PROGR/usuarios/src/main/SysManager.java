package main;

public interface SysManager {

    void addUser();
    boolean checkPassword(String userdatum, String s);
    String getUsersFilePath();

}
