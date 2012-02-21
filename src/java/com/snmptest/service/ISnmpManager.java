/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmptest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.percederberg.mibble.Mib;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MMayor
 */
public interface ISnmpManager {

    public Map getMib(String address, String oid);

    public Map getMib(String address, String[] oid);

    public Mib loadMib(String fileName) throws Exception;

    public Map extractOids(String fileName, String address) throws Exception;

    public String openFile(MultipartFile file) throws IOException;

    public List<String> listOfFiles();
    
    public void writeToFile(MultipartFile file) throws FileNotFoundException, IOException;
}
