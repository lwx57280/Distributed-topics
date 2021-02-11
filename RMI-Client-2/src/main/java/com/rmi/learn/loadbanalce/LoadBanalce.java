package com.rmi.learn.loadbanalce;

import java.util.List;

/**
 * 负载均衡
 * @Author:         cong zhi
 * @CreateDate:     2021/2/7 12:05
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/2/7 12:05
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface LoadBanalce {

    String selectHost(List<String> repos);
}
