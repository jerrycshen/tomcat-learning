package me.shenchao.ex20.standardbean;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * 创建一个标准MBean
 */
public class StandardAgent {

    /**
     * 持有MBeanServer实例
     */
    private MBeanServer mBeanServer = null;

    public StandardAgent() {
        // 使用工厂方法直接返回一个JMX参考实现的默认server对象
        mBeanServer = MBeanServerFactory.createMBeanServer();
    }

    public MBeanServer getmBeanServer() {
        return mBeanServer;
    }


    /**
     * 每个MBean实例都通过一个对象名称(ObjectName)来唯一标识
     * 对象名称由两部分组成，域和一个键值对,中间用冒号分隔,例如:
     *  myDomain:type=Car,Color=blue
     * @param name name
     * @return
     */
    public ObjectName createObjectName(String name) {
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectName;
    }

    /**
     * MBeanServer的createMBean方法会将创建的MBean实例注册到server中
     * @param objectName MBean唯一标识
     * @param managedResourceClassName 要托管资源的类名
     */
    private void createStandardBean(ObjectName objectName, String managedResourceClassName) {
        try {
            // 由于MBean特殊的命名规范，所以无须指定MBean的名字
            mBeanServer.createMBean(managedResourceClassName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 到此，我们可以通过StandardAgent类来直接访问Car对象了,由于MBean接口的存在，我们可以选择哪些功能需要暴露出来，哪些隐藏
     * @param args
     */
    public static void main(String[] args) {
        StandardAgent agent = new StandardAgent();
        MBeanServer mBeanServer = agent.getmBeanServer();
        String domain = mBeanServer.getDefaultDomain();
        String managedResourceClassName = "me.shenchao.ex20.standardbean.Car";
        ObjectName objectName =  agent.createObjectName(domain + ":type=" + managedResourceClassName);
        agent.createStandardBean(objectName, managedResourceClassName);

        // manage MBean
        try {
            Attribute colorAttribute = new Attribute("Color", "blue");
            mBeanServer.setAttribute(objectName, colorAttribute);
            System.out.println(mBeanServer.getAttribute(objectName,"Color"));
            mBeanServer.invoke(objectName, "drive",null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
