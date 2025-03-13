package org.example.btap_cuoi_khoa_1.manager;

import utils.Utils;

import java.io.*;
import java.util.*;

public class UserManager {
    private static UserManager instance;
    private Map<String, String> userMap = new HashMap<>();
    private String currentUser = "";
    private UserManager() {}
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    public void loadUsers() {
        userMap.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Data/userAccount.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveUsers() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Utils.fileName))){
            for (Map.Entry<String, String> entry : userMap.entrySet()) {
                writer.write( entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            Utils.showAlert("errol!");
        }
    }
    public boolean checkName(String userName, String password){
        loadUsers();
        if (userMap.containsKey(userName)) {
            Utils.showAlert("useName existed!");
            return false;
        }
        return true;
    }
    public void addUser(String userName, String password) {
        if(checkName(userName,password) != true){
            return ;
        }
        String hashedPassword = DataEncryption.hashPassword(password);
        userMap.put(userName, hashedPassword);
        saveUsers();
    }
    public boolean logIn(String username, String password) {
        String hashedPassword = DataEncryption.hashPassword(password);
        if (hashedPassword.equals(userMap.get(username))) {
            currentUser = username;
            return true;
        }
        return false;
    }
    public String getCurrentUser() {
        return currentUser;
    }

}
