package com.bolo.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 我的轮询算法：集群中的每台服务器依次连续执行五次
 */
public class MyRoundRobinRule extends AbstractLoadBalancerRule {
    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;
    private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);

    public MyRoundRobinRule() {
        this.nextServerCyclicCounter = new AtomicInteger(0);
    }

    public MyRoundRobinRule(ILoadBalancer lb) {
        this();
        this.setLoadBalancer(lb);
    }

    // 当前服务器被调用的次数统计
    private static int total = 0;
    // 每台服务器连续调用次数
    private final static int MAX_TOL = 5;

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        } else {
            Server server = null;
            int count = 0;

            while (true) {
                if (server == null && count++ < 10) {
                    List<Server> reachableServers = lb.getReachableServers();
                    List<Server> allServers = lb.getAllServers();
                    int upCount = reachableServers.size();
                    int serverCount = allServers.size();
                    if (upCount != 0 && serverCount != 0) {
                        // 如果上次调用的服务器的调用次数小于最大连续次数，则直接返回当前服务器，否则获取集群中的下一个服务器
                        if (total < MAX_TOL) {
                            log.info("上次调用的服务器总调用次数{}次，小于{}次，继续调用", this.nextServerCyclicCounter.get(), MAX_TOL);
                            server = allServers.get(this.nextServerCyclicCounter.get());
                            ++total;
                        } else {
                            log.info("获取集群中的下个服务器");
                            int nextServerIndex = this.incrementAndGetModulo(serverCount);
                            server = (Server) allServers.get(nextServerIndex);
                            total = 0;
                        }
                        if (server == null) {
                            Thread.yield();
                        } else {
                            if (server.isAlive() && server.isReadyToServe()) {
                                return server;
                            }

                            server = null;
                        }
                        continue;
                    }

                    log.warn("No up servers available from load balancer: " + lb);
                    return null;
                }

                if (count >= 10) {
                    log.warn("No available alive servers after 10 tries from load balancer: " + lb);
                }

                return server;
            }
        }
    }

    private int incrementAndGetModulo(int modulo) {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = (current + 1) % modulo;
        } while (!this.nextServerCyclicCounter.compareAndSet(current, next));

        return next;
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }


}
