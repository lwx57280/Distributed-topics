package com.rmi.learn.zk;

import com.google.common.collect.Lists;
import com.rmi.learn.loadbanalce.LoadBanalce;
import com.rmi.learn.loadbanalce.RandomLoadBanalce;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * 实现类
 *
 * @Author: cong zhi
 * @CreateDate: 2021/2/7 11:44
 * @UpdateUser: cong zhi
 * @UpdateDate: 2021/2/7 11:44
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ServiceDiscoveryImpl implements ServiceDiscovery {

    List<String> repos = Lists.newArrayList();

    private String address;

    private CuratorFramework curatorFramework;

    public ServiceDiscoveryImpl(String address) {
        this.address = address;
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(address)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

  /*  {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(address)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }
*/

    @Override
    public String discovery(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            repos = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常");
        }
        // 动态发现服务节点变化
        registerWatcher(path);
        // 负载均衡机制
        LoadBanalce loadBanalce = new RandomLoadBanalce();
        // 返回调用服务地址
        return loadBanalce.selectHost(repos);
    }

    /**
     * 监听服务节点
     *
     * @param path
     */
    private void registerWatcher(final String path) {

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {

            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        // 将当前事件添加到当前节点上
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher 异常:" + e);
        }
    }
}
