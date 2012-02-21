/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.snmptest.domain;

/**
 *
 * @author MMayor
 */
public class MIBBrowserForm {
    private String address;
    private String mib;

    public MIBBrowserForm(String address, String mib) {
        this.address = address;
        this.mib = mib;
    }

    public MIBBrowserForm() {
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the mib
     */
    public String getMib() {
        return mib;
    }

    /**
     * @param mib the mib to set
     */
    public void setMib(String mib) {
        this.mib = mib;
    }
}
