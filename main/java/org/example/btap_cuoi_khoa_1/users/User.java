package org.example.btap_cuoi_khoa_1.users;

import java.io.Serializable;

public class User implements Serializable {
    private String useName;
    private String password;

    public User(String useName, String password) {
        this.useName = useName;
        this.password = password;
    }

    public String getUseName() {
        return useName;
    }

    public String getPassword() {
        return password;
    }
}
