/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.snmptest.domain;

/**
 *
 * @author MMayor
 */
public class User {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String name;
    private String password;

}
