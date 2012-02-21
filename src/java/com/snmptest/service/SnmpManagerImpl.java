/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmptest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibSymbol;
import net.percederberg.mibble.MibValue;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.value.ObjectIdentifierValue;
import org.snmp4j.Snmp;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MMayor
 */
@Service
public class SnmpManagerImpl implements ISnmpManager {

    @Autowired
    @Qualifier("snmp")
    private Snmp snmp;
    @Autowired
    @Qualifier("config")
    private Properties props;

    public Map getMib(String address, String oid) {
        Map model = new HashMap();
        SimpleSnmpClient client = new SimpleSnmpClient("udp:" + address + "/161");
        if (oid != null) {
            try {
                System.out.println(oid);
                String value = client.getAsString(new OID(oid));
                model.put(oid, value);
            } catch (IOException ex) {
                Logger.getLogger(SnmpManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return model;
    }

    public String openFile(MultipartFile file) throws IOException {
        writeToFile(file);
        return readFromFile(file);
    }

    private String readFromFile(MultipartFile file) throws IOException {
        InputStream data = file.getInputStream();
        InputStreamReader isr = new InputStreamReader(data);
        Reader reader = new BufferedReader(isr);
        Writer writer = new StringWriter();

        streamToFile(file, reader);
        char[] cbuffer = new char[1024];
        int readBytes = 0;
        while ((readBytes = reader.read(cbuffer)) != -1) {
            writer.write(cbuffer, 0, readBytes);
            System.out.println("buffer: " + cbuffer);
        }
        data.close();
        return writer.toString();
    }

    public void writeToFile(MultipartFile file) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(props.getProperty("location") + file.getOriginalFilename());
        fos.write(file.getBytes());
        fos.close();
    }

    private void streamToFile(MultipartFile file, Reader reader) throws FileNotFoundException, IOException {
        OutputStream outStream = new FileOutputStream(props.getProperty("location") + "os_" + file.getOriginalFilename());
        Writer writeToFile = new OutputStreamWriter(outStream);

        char[] cbuffer = new char[1024];
        int redBytes;
        while ((redBytes = reader.read(cbuffer)) != -1) {
            writeToFile.write(cbuffer, 0, redBytes);
        }
        writeToFile.close();
        outStream.close();
    }

    public Map getMib(String address, String[] allOid) {
        Map model = new HashMap() {
        };
        for (int i = 0; i < allOid.length; i++) {
            String oid = allOid[i];
            model.putAll(getMib(address, oid));
        }
        return model;
    }

    public Mib loadMib(String fileName) throws Exception {
        File file = new File(props.getProperty("location") + fileName);
        MibLoader loader = new MibLoader();
        loader.addDir(file.getParentFile());
        return loader.load(file);
    }

    public List<String> listOfFiles() {
        File files = new File(props.getProperty("location"));
        FilenameFilter mibFilter = new MibFilter();
        List<String> fileNames = new ArrayList<String>();
        for (File file : files.listFiles(mibFilter)) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public Map extractOids(String fileName, String address) throws Exception {
        Mib mibData = loadMib(fileName);
        Map mibhash = extractOids(mibData);
        String[] oids = new String[mibhash.size()];
        int i = 0;
        for (Iterator it = mibhash.values().iterator(); it.hasNext();) {
            ObjectIdentifierValue oid = (ObjectIdentifierValue) it.next();
            if (oid.getAllChildren().length < 1) {
                oids[i] = oid.toString();
                
                i++;
            }
        }
        return getMib(address, oids);
    }

    private class MibFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mib"));
        }
    }

    private HashMap extractOids(Mib mib) {
        HashMap map = new HashMap();
        Iterator iter = mib.getAllSymbols().iterator();
        MibSymbol symbol;
        MibValue value;

        while (iter.hasNext()) {
            symbol = (MibSymbol) iter.next();
            value = extractOid(symbol);
            if (value != null) {
                map.put(symbol.getName(), value);
            }
        }
        return map;
    }

    private ObjectIdentifierValue extractOid(MibSymbol symbol) {
        MibValue value;
        if (symbol instanceof MibValueSymbol) {
            value = ((MibValueSymbol) symbol).getValue();
            if (value instanceof ObjectIdentifierValue) {
                return (ObjectIdentifierValue) value;
            }
        }
        return null;
    }
}
