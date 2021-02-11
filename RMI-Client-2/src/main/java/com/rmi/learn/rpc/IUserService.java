package com.rmi.learn.rpc;
/**
 * client只要调用对应的接口方法就可以远程调用服务端的具体实现
 * @Author:         li cong zhi
 * @CreateDate:     2021/1/17 11:29
 * @UpdateUser:     li cong zhi
 * @UpdateDate:     2021/1/17 11:29
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface IUserService {


    String sayHello(String msg);

}
