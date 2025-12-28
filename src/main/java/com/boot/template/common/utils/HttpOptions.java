package com.boot.template.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpOptions {

    /**
     * 读写超时时间 单位: milliseconds
     */
    private int socketTimeout = 5000;

    /**
     * 连接超时时间 单位: milliseconds
     */
    private int connectTimeout = 5000;

    /**
     * 最大连接数
     */
    private int maxConnections = 1024;

    /**
     * 每个路由最大连接数
     */
    private int maxConnectionsPerRoute = 256;

    /**
     * 从连接池里获取连接的超时时间 单位: milliseconds
     */
    private int connectionPoolTimeout = 500;

    /**
     * 重试次数
     */
    private int retryTimes = 2;

    /**
     * 最大连接存活时间，0表示无限制。单位: seconds
     */
    private int timeToLive = 3600;

    /**
     * 保持连接时长。单位: seconds
     */
    private int keepAlive = 0;

    /**
     * HTTP User-Agent
     */
    private String userAgent = null;

    /**
     * 是否使用代理
     */
    private boolean useProxy = false;

    /**
     * 代理服务器地址
     */
    private String proxyHost = null;

    /**
     * 代理服务器端口
     */
    private int proxyPort = 0;

    public HttpOptions() {
    }

    public HttpOptions(int socketTimeout, int connectTimeout) {
        this.socketTimeout = socketTimeout;
        this.connectTimeout = connectTimeout;
    }

    /**
     * 重试异常
     */
    private Set<Class<? extends Exception>> retryErrors =
            new HashSet<>(Arrays.asList(ConnectException.class, SocketTimeoutException.class));

    /**
     * Create http connection pool, set max total connection and max connection per route
     */
    public HttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager manager =
                new PoolingHttpClientConnectionManager(this.getTimeToLive(), TimeUnit.SECONDS);
        manager.setDefaultMaxPerRoute(this.getMaxConnectionsPerRoute());
        manager.setMaxTotal(this.getMaxConnections());

        // Socket configuration
        SocketConfig.Builder builder = SocketConfig.custom();
        builder.setSoTimeout(this.getSocketTimeout());
        builder.setTcpNoDelay(Boolean.TRUE);

        manager.setDefaultSocketConfig(builder.build());
        return manager;
    }

    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(this.getConnectTimeout())
                .setSocketTimeout(this.getSocketTimeout())
                .setConnectionRequestTimeout(this.getConnectionPoolTimeout())
                .build();
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public HttpOptions setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public HttpOptions setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public HttpOptions setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

    public HttpOptions setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        return this;
    }

    public int getConnectionPoolTimeout() {
        return connectionPoolTimeout;
    }

    public HttpOptions setConnectionPoolTimeout(int connectionPoolTimeout) {
        this.connectionPoolTimeout = connectionPoolTimeout;
        return this;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public HttpOptions setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public HttpOptions setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public HttpOptions setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public HttpOptions setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public HttpOptions setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
        return this;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public HttpOptions setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
        return this;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public HttpOptions setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    public Set<Class<? extends Exception>> getRetryErrors() {
        return retryErrors;
    }

    public HttpOptions setRetryErrors(Set<Class<? extends Exception>> retryErrors) {
        this.retryErrors = retryErrors;
        return this;
    }
}