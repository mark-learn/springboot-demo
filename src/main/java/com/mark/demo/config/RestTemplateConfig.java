package com.mark.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        //默认JDK 实现
        //ClientHttpRequestFactory factory = getSimpleClientHttpRequestFactory();
        //切换 HttpClient 实现
        //ClientHttpRequestFactory factory = getHttpComponentsClientHttpRequestFactory();
        //切换 OkHttp 实现
        ClientHttpRequestFactory factory = getOkHttp3ClientHttpRequestFactory();
        return new RestTemplate(factory);
    }


    public ClientHttpRequestFactory getSimpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10 * 1000);
        httpRequestFactory.setReadTimeout(30 * 1000);
        httpRequestFactory.setBufferRequestBody(true);
        httpRequestFactory.setChunkSize(1024 * 4);
        httpRequestFactory.setOutputStreaming(true);
        return httpRequestFactory;
    }

    public OkHttp3ClientHttpRequestFactory getOkHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory httpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10 * 1000);
        httpRequestFactory.setReadTimeout(30 * 1000);
        httpRequestFactory.setWriteTimeout(30 * 1000);
        return httpRequestFactory;
    }

    //HttpClient 实现
//    public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() {
//        SSLConnectionSocketFactory sslConnectionSocketFactory;
//        try {
//            // 默认信任所有证书
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (x509Certificates, s) -> true).build();
//            // AllowAllHostnameVerifier: 这种方式不对主机名进行验证，验证功能被关闭，是个空操作(域名验证)
//            sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//        } catch (Exception ex) {
//            sslConnectionSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
//        }
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", new PlainConnectionSocketFactory())
//                .register("https", sslConnectionSocketFactory)
//                .build();
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
//        //总连接数
//        cm.setMaxTotal(40);
//        //每个路由的最大并发数连接
//        cm.setDefaultMaxPerRoute(1000);
//
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(10 * 1000)
//                .setConnectionRequestTimeout(10 * 1000)
//                .setSocketTimeout(30 * 1000)
//                .setRedirectsEnabled(true)
//                .setMaxRedirects(5)
//                .build();
//        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
//                .setConnectionManager(cm)
//                .setConnectionManagerShared(true)
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
//        //超时设置(单位毫秒）
//        httpRequestFactory.setConnectTimeout(10 * 1000);
//        httpRequestFactory.setConnectionRequestTimeout(10 * 1000);
//        httpRequestFactory.setReadTimeout(30 * 1000);
//        return httpRequestFactory;
//    }
}
