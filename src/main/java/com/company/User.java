package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private String userName;
    private String userLogin;
    private String userEmail;
    private String userCode;
    private String userStatus;

    public User () {
        userName = "";
        userLogin = "";
        userEmail = "";
        userCode = "";
        userStatus = "";
    }

    public User(String name, String login, String email, String code, String status) {
        userName = name;
        userLogin = login;
        userEmail = email;
        userCode = code;
        userStatus = status;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLogin() { return userLogin; }

    public String getUserEmail() { return userEmail; }

    public String getUserCode() { return userCode; }

    public boolean enterSystem(List <User> userList) {
        int correct = 0;
        for (int i = 0; i < userList.size(); ++i) {
            if ((this.userLogin.equals(userList.get(i).userLogin)) && (this.userCode.equals(userList.get(i).userCode))) {
                ++correct;
            }
        }
        if (correct >= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public static List<User> goToListUser(List<List<String>> listStringD) throws IOException {
        List<User> clientList = new ArrayList<>();
        for (int i = 0; i < listStringD.size(); ++i) {
            User user = new User(listStringD.get(i).get(0), listStringD.get(i).get(1), listStringD.get(i).get(2), listStringD.get(i).get(3), listStringD.get(i).get(4));
            clientList.add(user);
        }
        return clientList;
    }

    public int findClient(List <Client> clientList, int number) {
        int findClient = 0;
        int index = -1;
        for (int i = 0; i < clientList.size(); ++i) {
            if (number == clientList.get(i).getClientId()) {
                index = i;
            }
        }

        if (index == -1) {
            return -2;
        }

        if (this.userStatus.equals("USER")) {
            return -1;
        }

        else {
            return index;
        }
    }

    public static void printUserList(List <User> userList) {
        for (int i = 0; i < userList.size(); ++i) {
            System.out.println("Username : " + userList.get(i).userName + " | Login: " + userList.get(i).userLogin + " | Email: " + userList.get(i).userEmail + " | Password: " + userList.get(i).userCode + " | Status: " + userList.get(i).userStatus);
        }
    }

    public void addUserToFile() throws IOException {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("\n" + this.userName + "\n" + this.userLogin + "\n" + this.userEmail + "\n" + this.userCode + "\nUser");
            bufferWriter.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

