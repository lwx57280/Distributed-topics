package com.rmi.learn.loadbanalce;

import java.util.List;
import java.util.Random;
/**
 * 随机负载均衡
 * @Author:         cong zhi
 * @CreateDate:     2021/2/7 12:16
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/2/7 12:16
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class RandomLoadBanalce extends AbstractLoadBanalce {

    @Override
    protected String doSelect(List<String> repos) {
        int len = repos.size();
        return repos.get(new Random().nextInt(len));
    }
}
