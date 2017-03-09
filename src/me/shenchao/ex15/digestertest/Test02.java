package me.shenchao.ex15.digestertest;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Test02 {

    public static void main(String[] args) throws IOException, SAXException {
        String path = System.getProperty("user.dir") + File.separator  + "etc";
        File file = new File(path, "employee2.xml");
        Digester digester = new Digester();
        // add rules
        digester.addObjectCreate("employee", "me.shenchao.ex15.digestertest.Employee");
        digester.addSetProperties("employee");
        digester.addObjectCreate("employee/office", "me.shenchao.ex15.digestertest.Office");
        digester.addSetProperties("employee/office");
        digester.addSetNext("employee/office", "addOffice");
        digester.addObjectCreate("employee/office/address", "me.shenchao.ex15.digestertest.Address");
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");

        Employee employee = (Employee) digester.parse(file);
        List offices = employee.getOffices();
        Iterator iterator = offices.iterator();
        while (iterator.hasNext()) {
            Office office = (Office) iterator.next();
            Address address = office.getAddress();
            System.out.println(office.getDescription());
            System.out.println("Address: " + address.getStreetName()+ " " + address.getStreetNumber());
        }
    }
}
