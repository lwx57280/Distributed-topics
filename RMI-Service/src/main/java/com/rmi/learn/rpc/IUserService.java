package com.rmi.learn.rpc;
/**
 * @Description:    定义接口，将rpc-server-api打包提供给client端使用，
 * @Author:         cong zhi
 * @CreateDate:     2021/1/17 11:28
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/1/17 11:28
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface IUserService {


    String sayHello(String msg);

}
