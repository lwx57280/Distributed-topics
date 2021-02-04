package com.rmi.learn.rpc;

import java.io.Serializable;

/**
 * @Description:    请求rpc时需要的参数
 * @Author:         li cong zhi
 * @CreateDate:     2021/1/16 17:52
 * @UpdateUser:     li cong zhi
 * @UpdateDate:     2021/1/16 17:52
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 4186108850712886052L;
    /**
     * 类
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private Object[] parameters;



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
