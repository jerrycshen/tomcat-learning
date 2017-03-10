package me.shenchao.ex20.modelbean;

import javax.management.*;
import javax.management.modelmbean.*;

/**
 *  创建模型MBean实例，并管理Car对象
 */
public class ModelAgent {

    private String MANANGED_CLASS_NAME = "me.shenchao.ex20.modelbean.Car";

    private MBeanServer mBeanServer = null;

    public ModelAgent() {
        mBeanServer = MBeanServerFactory.createMBeanServer();
    }

    public MBeanServer getmBeanServer() {
        return mBeanServer;
    }

    private ObjectName createObjectName(String name) {
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectName;
    }

    /**
     * 创建一个模型MBean过程相比标准MBean会复杂很多，通过ModelBeanInfo来选择暴露哪些方法，属性给调用方
     * @param objectName
     * @param mbeanName
     * @return
     */
    private ModelMBean createMBean(ObjectName objectName, String mbeanName) {
        ModelMBeanInfo mBeanInfo = createModelMBeanInfo(objectName, mbeanName);
        RequiredModelMBean modelMBean = null;
        try {
            modelMBean = new RequiredModelMBean(mBeanInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMBean;
    }

    private ModelMBeanInfo createModelMBeanInfo(ObjectName inMbeanObjectName, String inMbeanName) {
        ModelMBeanInfo mBeanInfo = null;
        // 暴露给外部调用的属性
        ModelMBeanAttributeInfo[] attributeInfos = new ModelMBeanAttributeInfo[1];
        ModelMBeanOperationInfo[] operationInfos = new ModelMBeanOperationInfo[3];
        try {
            attributeInfos[0] = new ModelMBeanAttributeInfo("Color", "java.lang.String", "the color.", true, true, false, null);
            operationInfos[0] = new ModelMBeanOperationInfo("drive", "the drive method", null, "void", MBeanOperationInfo.ACTION, null);
            operationInfos[1] = new ModelMBeanOperationInfo("getColor", "get color attribute", null, "java.lang.String", MBeanOperationInfo.ACTION, null);

            // 描述信息
            Descriptor setColorDesc = new DescriptorSupport(new String[] {
                "name=setColor", "descriptorType=operation", "class=" + MANANGED_CLASS_NAME, "role=operation"
            });
            MBeanParameterInfo[] setColorParams = new MBeanParameterInfo[] {
                new MBeanParameterInfo("color", "java.lang.String", "new Color value")
            };
            operationInfos[2] = new ModelMBeanOperationInfo("setColor", "set color attribute", setColorParams,"void",MBeanOperationInfo.ACTION,setColorDesc);

            mBeanInfo = new ModelMBeanInfoSupport(MANANGED_CLASS_NAME, null, attributeInfos, null, operationInfos, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mBeanInfo;
    }

    public static void main(String[] args) {
        ModelAgent agent = new ModelAgent();
        MBeanServer mBeanServer = agent.getmBeanServer();
        Car car = new Car();
        String domain = mBeanServer.getDefaultDomain();
        ObjectName objectName = agent.createObjectName(domain + ":type=MyCar");
        String mBeanName = "myMBean";
        ModelMBean modelMBean = agent.createMBean(objectName, mBeanName);
        try {
            modelMBean.setManagedResource(car, "ObjectReference");
            mBeanServer.registerMBean(modelMBean, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Attribute attribute = new Attribute("Color", "green");
            mBeanServer.setAttribute(objectName, attribute);
            String color = (String) mBeanServer.getAttribute(objectName, "Color");
            System.out.println(color);

            mBeanServer.invoke(objectName, "drive", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
