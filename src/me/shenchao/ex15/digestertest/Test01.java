package me.shenchao.ex15.digestertest;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Test01 {

    public static void main(String[] args) throws IOException, SAXException {
        String path = System.getProperty("user.dir") + File.separator + "etc";
        File file = new File(path, "employee1.xml");
        Digester digester = new Digester();
        // add rules
        // 每次创建一个对象都会保存到一个栈中
        digester.addObjectCreate("employee", "me.shenchao.ex15.digestertest.Employee");
        digester.addSetProperties("employee");
        // 此方法调用栈顶部的对象
        digester.addCallMethod("employee", "printName");

        // 在预定义所有规则之后，进行解析xml
        Employee employee = (Employee) digester.parse(file);
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
    }
}
