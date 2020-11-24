package com.company;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.company.Client.*;
import static com.company.User.goToListUser;
import static com.company.User.printUserList;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileNameClient = "/Users/nikita/Documents/Hometask university/Java/New Exam/clients1.txt";
        String fileNameUser = "/Users/nikita/Documents/Hometask university/Java/New Exam/users1.txt";

        List<List<String>> clientsListD = readUsingFileReader(fileNameClient);
        List<List<String>> userListD = readUsingFileReader(fileNameUser);

        Scanner in = new Scanner(System.in);

        List<Client> clientList = goToListClient(clientsListD);
        List<User> userList = goToListUser(userListD);

        System.out.print("Show the list of users and clients?\n1 - Yes\n2 - Not\nEnter: ");
        int show = in.nextInt();
        if (show == 1) {
            System.out.println("List of clients");
            printClientList(clientList);
            System.out.println("List of users");
            printUserList(userList);
            System.out.print("\n");
            in.nextLine();
        }

        in.nextLine();
        System.out.println("Enter login: ");
        String enterLogin = in.nextLine();

        System.out.println("Enter password: ");
        String enterPassword = in.nextLine();

        User enterUser = new User("", enterLogin, "", enterPassword, "USER");

        if (enterUser.enterSystem(userList)) {
            System.out.print("Correct!\n");

            System.out.print("Enter the variant: \n1 - View all enteries\n2 - Find client\n3 - Find rich users\n4 - Add new user\n5 - Find middle sum of insurance for each person\n6 - Exit\n");
            System.out.print("Enter: ");
            int variant = in.nextInt();

            if (variant == 1) {
                printClientList(clientList);
            }

            if (variant == 2) {
                int id = in.nextInt();
                int index = enterUser.findClient(clientList, id);
                if (index == -2) {
                    System.out.println("There isn't such person in list");
                }

                if (index == -1) {
                    System.out.println("You are not an ADMIN");
                }
                else {
                    printClientPerson(clientList, index);
                }
            }

            if (variant == 3) {
                sortBubble(clientList);
                printClientList(clientList);
            }

            if (variant == 4) {

                System.out.println("Enter name of user: ");
                String newUserName = in.nextLine();

                System.out.println("Enter login of user: ");
                String newUserLogin = in.nextLine();

                System.out.println("Enter Email of user: ");
                String newUserEmail = in.nextLine();

                System.out.println("Enter code: ");
                String newUserCode = in.nextLine();

                User newUser = new User(newUserName, newUserLogin, newUserEmail, newUserCode, "USER");

                int counterEquals = 0;
                for (int i = 0; i < userList.size(); ++i) {
                    if (userList.get(i).getUserName().equals(newUserName)) {
                        System.out.print("This user has already exists in the system\n");
                        ++counterEquals;
                    }
                }
                if (counterEquals == 0) {
                    newUser.addUserToFile();
                }
            }

            if (variant == 5) {
                makeTopInsurance(clientList);
                printClientListTopInsurance(clientList);
            }

            if (variant == 6) {
                System.out.println("Exit");
            }

        }
        else {
            System.out.print("Not correct login or password\n");
        }
    }
}

