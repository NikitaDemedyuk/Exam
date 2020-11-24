package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    private int clientId;
    private String clientName;
    private String clientDate;
    private String clientInsurance;
    private int clientAmount;
    private String clientTypeCurrency;

    Client() {
        clientId = 0;
        clientName = "";
        clientDate = "";
        clientInsurance = "";
        clientAmount = 0;
        clientTypeCurrency = "$";
    }
    Client(int id, String name, String date, String insurance, int amount, String typeCurrency) {
        clientId = id;
        clientName = name;
        clientDate = date;
        clientInsurance = insurance;
        clientAmount = amount;
        clientTypeCurrency = typeCurrency;
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() { return clientName; }

    public String getClientDate() { return clientDate; }

    public String getClientInsurance() { return clientInsurance; }

    public int getClientAmount() { return clientAmount; }

    public String getClientTypeCurrency() { return  clientTypeCurrency; }

    public static List<List<String>> readUsingFileReader(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<String> stringList = new ArrayList();
        while ((line = br.readLine()) != null) {
            stringList.add(line);
        }
        br.close();
        fr.close();

        String delimeter = ",";
        List<List<String>> stringListD = new ArrayList();
        for (int i = 0; i < stringList.size(); ++i) {
            stringListD.add(Arrays.asList(stringList.get(i).split(delimeter)));
        }
        return stringListD;
    }


    public static List<Client> goToListClient(List<List<String>> listStringD) throws IOException {
        List<Client> clientList = new ArrayList<>();
        for (int i = 0; i < listStringD.size(); ++i) {
            Client client = new Client(Integer.parseInt(listStringD.get(i).get(0)), listStringD.get(i).get(1), listStringD.get(i).get(2), listStringD.get(i).get(3), Integer.parseInt(listStringD.get(i).get(4)), listStringD.get(i).get(5));
            clientList.add(client);
        }
        return clientList;
    }

    public static void printClientList(List <Client> clientList) {
        for (int i = 0; i < clientList.size(); ++i) {
            System.out.println("Id : " + clientList.get(i).clientId + " | Name: " + clientList.get(i).clientName + " | Date: " + clientList.get(i).clientDate + " | Insurance: " + clientList.get(i).clientInsurance + " | Sum: " + clientList.get(i).clientAmount + " | Type of sum: " + clientList.get(i).clientTypeCurrency);  ;
        }
    }

    public static void printClientPerson(List <Client> clientList, int index) {
        System.out.println("Id : " + clientList.get(index).clientId + " | Name: " + clientList.get(index).clientName + " | Date: " + clientList.get(index).clientDate + " | Insurance: " + clientList.get(index).clientInsurance + " | Sum: " + clientList.get(index).clientAmount + " | Type of sum: " + clientList.get(index).clientTypeCurrency);  ;
    }

    public static void sortBubble(List <Client> clientList) {
        for (int i = 0; i < clientList.size() - 1; ++i) {
            for (int j = 0; j < clientList.size() - 1; ++j) {
                if (clientList.get(i).clientAmount < clientList.get(i + 1).clientAmount) {
                    Client tmp = new Client(clientList.get(i).clientId, clientList.get(i).clientName, clientList.get(i).clientDate, clientList.get(i).clientInsurance, clientList.get(i).clientAmount, clientList.get(i).clientTypeCurrency);
                    clientList.set(i, clientList.get(i + 1));
                    clientList.set(i + 1, tmp);
                }
            }
        }
    }

    public static void makeTopInsurance(List <Client> clientList) {
        for (int i = 0; i < clientList.size(); ++i) {

            // 1 euro = 3 dollars
            // 1 pound = 5 dollars

            if (clientList.get(i).getClientTypeCurrency().equals("Euro")) {
                clientList.get(i).clientTypeCurrency = "Dollars";
                clientList.get(i).clientAmount *= 3;
            }

            if (clientList.get(i).getClientTypeCurrency().equals("Pound")) {
                clientList.get(i).clientTypeCurrency = "Dollars";
                clientList.get(i).clientAmount *= 5;
            }
        }
    }

    public static void printClientListTopInsurance(List <Client> clientList) {
        for (int i = 0; i < clientList.size(); ++i) {
            System.out.println("Id : " + clientList.get(i).clientId + " | Name: " + clientList.get(i).clientName + " | Sum: " + clientList.get(i).clientAmount + " | Type of sum: " + clientList.get(i).clientTypeCurrency);  ;
        }
    }
}