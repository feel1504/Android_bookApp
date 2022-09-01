package com.example.bookapp.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String account;
    private String password;
    private int level;

    public User(){};
    public User(String s, String s1, int i) {
        account=s;
        password=s1;
        level=i;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }
}
