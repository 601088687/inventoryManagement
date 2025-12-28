package com.boot.template.common.utils;

import com.google.common.base.Charsets;
import com.yomahub.tlog.httpclient.TLogHttpClientInterceptor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yishuai
 * @date 2022-05-10 14:30
 */
public class HttpClientHelper {
    private static final Logger log = LoggerFactory.getLogger(HttpClientHelper.class);

    private HttpOptions options;

    public HttpClientHelper() {
    }

    public HttpClientHelper(HttpOptions options) {
        this.options = options;
    }

    /**
     * 预留
     * 可设置重试次数 请求耗时限制等
     * httpOptions
     *
     * @return
     */
    public static HttpClientHelper create() {
        return new HttpClientHelper(new HttpOptions());
    }

    /**
     * 预留
     * 可设置重试次数 请求耗时限制等
     * httpOptions
     *
     * @return
     */
    public static HttpClientHelper create(HttpOptions httpOptions) {
        return new HttpClientHelper(httpOptions);
    }

    /**
     * process get request auto check if is http or https
     */
    public String doGet(String urlPath) {
        return doGet(urlPath, Charsets.UTF_8, null, null);
    }

    /**
     * Submit post request, auto check if is http or https
     */
    public String doPost(String urlPath, String content) {
        return doPost(urlPath, content, Charsets.UTF_8, null);
    }

    /**
     * Submit post request, default UTF-8, auto check if is http or https
     */
    public String doPost(String urlPath, Map<String, String> params) {
        return doPost(urlPath, params, Charsets.UTF_8, null);
    }

    /**
     * Submit post request with key/value map, specified encoding, headers
     */
    public String doPost(
            HttpClient client,
            String urlPath,
            Map<String, String> params,
            Charset charset,
            Map<String, String> headers) {
        Throwable failureException = null;
        boolean success = true;
        String response = null;
        long beginTime = System.currentTimeMillis();
        int statusCode = -1;
        try {

            HttpPost post = new HttpPost(urlPath);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    post.addHeader(e.getKey(), e.getValue());
                }
            }
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<>();
                for (Map.Entry<String, String> e : params.entrySet()) {
                    formParams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                }
                post.setEntity(new UrlEncodedFormEntity(formParams, charset));
            }


            HttpResponse httpResponse = client.execute(post);
            ResponseStatus responseStatus = handleResponse(httpResponse, charset);
            statusCode = responseStatus.statusCode;
            success = (statusCode >= 200 && statusCode < 300);
            response = responseStatus.response;
        } catch (IOException e) {
            failureException = e;
            success = false;
        } catch (Exception e) {
            failureException = e;
            success = false;
        }
        return response;
    }

    /**
     * Submit post request with specified encoding, auto check if is http or https
     */
    public String doPost(
            String urlPath, Map<String, String> params, Charset charset, Map<String, String> headers) {
        HttpClient client = buildClient(urlPath, null);
        return doPost(client, urlPath, params, charset, headers);
    }

    /**
     * Submit post request with specified encoding, auto check if is http or https
     */
    public String doPost(
            String urlPath, String content, Charset charset, Map<String, String> headers) {
        HttpClient client = buildClient(urlPath, null);
        return doPost(client, urlPath, content, charset, headers);
    }

    /**
     * Submit http post request and put content in body directly
     */
    private String doPost(
            HttpClient client,
            String urlPath,
            String content,
            Charset charset,
            Map<String, String> headers) {
        Throwable failureException = null;
        boolean success = true;
        String response = null;
        long beginTime = System.currentTimeMillis();
        int statusCode = -1;
        try {
            HttpPost post = new HttpPost(urlPath);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    post.addHeader(e.getKey(), e.getValue());
                }
            }

            post.setEntity(new ByteArrayEntity(content.getBytes(charset)));

            HttpResponse httpResponse = client.execute(post);
            ResponseStatus responseStatus = handleResponse(httpResponse, charset);
            statusCode = responseStatus.statusCode;
            success = (statusCode >= 200 && statusCode < 300);
            response = responseStatus.response;
        } catch (IOException e) {
            failureException = e;
            success = false;
        } catch (Exception e) {
            failureException = e;
            success = false;
        }
        return response;
    }

    /**
     * @param urlPath
     * @param charset
     * @param params
     * @param headers
     * @return
     */
    public String doGet(
            String urlPath, Charset charset, Map<String, String> params, Map<String, String> headers) {
        Throwable failureException = null;
        boolean success = true;
        String response = null;
        long beginTime = System.currentTimeMillis();
        int statusCode = -1;
        try {
            URIBuilder uriBuilder = new URIBuilder(urlPath);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> e : params.entrySet()) {
                    uriBuilder.addParameter(e.getKey(), e.getValue());
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    httpGet.addHeader(e.getKey(), e.getValue());
                }
            }

            HttpClient client = buildClient(urlPath, null);

            HttpResponse httpResponse = client.execute(httpGet);
            ResponseStatus responseStatus = handleResponse(httpResponse, charset);
            statusCode = responseStatus.statusCode;
            success = (statusCode >= 200 && statusCode < 300);
            response = responseStatus.response;
        } catch (IOException e) {
            failureException = e;
            success = false;
        } catch (Exception e) {
            failureException = e;
            success = false;
        }
        return response;
    }


    /**
     * Submit put request, default UTF-8, auto check if is http or https
     */
    public String doPut(String urlPath, Map<String, String> params, Map<String, String> headers) {
        HttpClient client = buildClient(urlPath, null);
        return doPut(client, urlPath, params, Charsets.UTF_8, headers);
    }

    /**
     * Submit put request with key/value map, specified encoding, headers
     */
    private String doPut(
            HttpClient client,
            String urlPath,
            Map<String, String> params,
            Charset charset,
            Map<String, String> headers) {
        Throwable failureException = null;
        boolean success = true;
        String response = null;
        long beginTime = System.currentTimeMillis();
        int statusCode = -1;
        try {

            HttpPut post = new HttpPut(urlPath);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    post.addHeader(e.getKey(), e.getValue());
                }
            }
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<>();
                for (Map.Entry<String, String> e : params.entrySet()) {
                    formParams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                }
                post.setEntity(new UrlEncodedFormEntity(formParams, charset));
            }


            HttpResponse httpResponse = client.execute(post);
            ResponseStatus responseStatus = handleResponse(httpResponse, charset);
            statusCode = responseStatus.statusCode;
            success = (statusCode >= 200 && statusCode < 300);
            response = responseStatus.response;
        } catch (IOException e) {
            failureException = e;
            success = false;
        } catch (Exception e) {
            failureException = e;
            success = false;
        }
        return response;
    }

    /**
     * 解析http响应报文
     *
     * @param response
     * @param charset
     * @return
     * @throws HttpResponseException
     * @throws IOException
     */
    private ResponseStatus handleResponse(HttpResponse response, Charset charset)
            throws HttpResponseException, IOException {
        final StatusLine statusLine = response.getStatusLine();
        final HttpEntity entity = response.getEntity();
        ResponseStatus responseStatus = new ResponseStatus();
        try {
            responseStatus.setStatusCode(statusLine.getStatusCode());
            responseStatus.setResponse(entity == null ? null : EntityUtils.toString(entity, charset));
        } finally {
            EntityUtils.consume(entity);
        }
        return responseStatus;
    }

    /**
     * No retry handler mechanism
     */
    private final HttpRequestRetryHandler NO_RETRIES_HANDLER =
            (exception, executionCount, context) -> false;

    private final HttpRequestRetryHandler RETRIES_HANDLER =
            new HttpRequestRetryHandler() {
                @Override
                public boolean retryRequest(IOException reqError, int executionCount, HttpContext context) {
                    if (executionCount > options.getRetryTimes()) {
                        return false;
                    }

                    boolean match = options.getRetryErrors().stream().anyMatch(ex -> ex.isInstance(reqError));
                    if (match) {
                        log.debug("http request retry , executionCount={}", executionCount);
                    }
                    return match;
                }
            };


    /**
     * Build http client with authentication mechanism
     */
    private HttpClient buildClient(String url, CredentialsProvider credentialsProvider) {

        HttpClientBuilder builder = HttpClientBuilder.create()
                .addInterceptorFirst(new TLogHttpClientInterceptor())
                .setConnectionManager(options.connectionManager());

        // Set UA
        if (StringUtils.isNotBlank(options.getUserAgent())) {
            builder.setUserAgent(options.getUserAgent());
        }

        // Set request params
        builder.setDefaultRequestConfig(options.requestConfig());

        // Set proxy
        if (options.isUseProxy()) {
            builder.setProxy(new HttpHost(options.getProxyHost(), options.getProxyPort()));
        }

        // Set connection reuse and keep alive
        final long keepAlive = options.getKeepAlive();
        if (keepAlive == 0) {
            builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
        } else {
            builder.setConnectionReuseStrategy(new DefaultConnectionReuseStrategy());
            builder.setKeepAliveStrategy(
                    new DefaultConnectionKeepAliveStrategy() {
                        @Override
                        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                            final long duration = super.getKeepAliveDuration(response, context);
                            return (duration == -1) ? keepAlive : duration;
                        }
                    });
        }

        // Retry mechanism
        if (options.getRetryTimes() > 0) {
            builder.setRetryHandler(RETRIES_HANDLER);
        } else {
            builder.setRetryHandler(NO_RETRIES_HANDLER);
        }

        // Authentication mechanism
        if (credentialsProvider != null) {
            builder.setDefaultCredentialsProvider(credentialsProvider);
        }

        // set ssl factory
        URI uri = URI.create(url);
        if ("https".equals(uri.getScheme())) {
            try {
                // Trust all
                SSLContext sslContext =
                        new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
                builder.setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext));
            } catch (Exception e) {
                log.warn("build SSLContext failed!", e);
            }
        }

        return builder.build();
    }

    public String doPostFormData(String url, Map<String, String> formDataMap) throws IOException {
        long start = System.currentTimeMillis();
        CloseableHttpClient aDefault = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = options.requestConfig();
        httpPost.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(formDataMap)) {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            /**
             * 源照片
             * 抓拍对比照片
             */
            formDataMap.forEach(multipartEntityBuilder::addTextBody);
            httpPost.setEntity(multipartEntityBuilder.build());
        }
        HttpResponse response = aDefault.execute(httpPost);
        ResponseStatus responseStatus = handleResponse(response, Charsets.UTF_8);
        int statusCode = responseStatus.getStatusCode();
        if (200 == statusCode) {
            long costTime = System.currentTimeMillis() - start;
            log.info("请求接口doPostFormData耗时,costTime:" + costTime);
            return responseStatus.getResponse();
        }
        log.error("请求接口异常," +
                "请求地址={" + url + "}," +
                "响应结果={" + responseStatus.getResponse() + "}");
        return null;
    }

    private class ResponseStatus {
        String response;
        int statusCode;


        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }
    }
}
