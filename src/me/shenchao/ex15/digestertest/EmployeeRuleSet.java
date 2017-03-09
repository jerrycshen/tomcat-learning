package me.shenchao.ex15.digestertest;


import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * 将添加rule的操作隐藏起来，不在客户端添加
 */
public class EmployeeRuleSet extends RuleSetBase {

    public void addRuleInstances(Digester digester) {
        // add rules
        digester.addObjectCreate("employee", "me.shenchao.ex15.digestertest.Employee");
        digester.addSetProperties("employee");
        digester.addObjectCreate("employee/office", "me.shenchao.ex15.digestertest.Office");
        digester.addSetProperties("employee/office");
        digester.addSetNext("employee/office", "addOffice");
        digester.addObjectCreate("employee/office/address", "me.shenchao.ex15.digestertest.Address");
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");
    }
}
