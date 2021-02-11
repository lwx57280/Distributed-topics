package com.rmi.learn.zk;

/**
 * 注册中心
 * @Author:         cong zhi
 * @CreateDate:     2021/2/4 9:50
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/2/4 9:50
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface RegisterCenter {

    /**
     * 注册服务名称和服务地址
     * @author      cong zhi
     * @params      serviceName 服务名
     * @return      serviceAddress 服务地址
     * @date        2021/2/4 16:12
     */
    void register(String serviceName, String serviceAddress);
}
