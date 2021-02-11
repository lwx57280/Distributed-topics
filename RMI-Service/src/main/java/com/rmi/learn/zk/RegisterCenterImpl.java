package com.rmi.learn.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 注册中心实现
 *
 * @Author: cong zhi
 * @CreateDate: 2021/2/4 16:15
 * @UpdateUser: cong zhi
 * @UpdateDate: 2021/2/4 16:15
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class RegisterCenterImpl implements RegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        // 注册服务
        String servicePath = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            // 判断 /registrys/product-service是否存在，不存在则创建
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }
            // 获取完整路径
            String addressPath = servicePath + "/" + serviceAddress;
            // 创建临时节点
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功" + rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
