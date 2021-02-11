package com.rmi.learn.loadbanalce;

import java.util.List;

/**
 * 抽象类构造模板方法
 * @Author:         cong zhi
 * @CreateDate:     2021/2/7 16:31
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/2/7 16:31
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public abstract class AbstractLoadBanalce implements LoadBanalce {

    protected abstract String doSelect(List<String> repos);

    @Override
    public String selectHost(List<String> repos) {
        if (repos == null || repos.size() == 0) {
            return null;
        }
        if (repos.size() == 1) {
            return repos.get(0);
        }
        return doSelect(repos);
    }
}
