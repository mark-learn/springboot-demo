package com.mark.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@Component
public class DemoRestTemplateUtils {

    @Autowired
    private static RestTemplate restTemplate;

    public static String sendSimple(String url) {
        return sendSimple(url, null, HttpMethod.GET, new HttpHeaders());
    }

    public static String sendSimple(String url, Map<String, ?> urlParam) {
        return sendSimple(url, urlParam, HttpMethod.GET);
    }

    public static String sendSimple(String url, Map<String, ?> urlParam, HttpHeaders headers) {
        return sendSimple(url, urlParam, HttpMethod.GET, headers);
    }

    public static String sendSimple(String url, Map<String, ?> urlParam, HttpMethod method) {
        return sendSimple(url, urlParam, method, new HttpHeaders());
    }

    /*** 发送简单请求，不含body** @param url      url* @param urlParam 用?和&拼接在url后面的参数* @param method   请求方式* @param headers  请求头* @return body*/
    public static String sendSimple(String url, Map<String, ?> urlParam, HttpMethod method, HttpHeaders headers) {
        if (urlParam == null) {
            urlParam = new HashMap<>(0);
        }
        // url参数拼接
        url = handleUrlParam(url, urlParam);
        HttpEntity<MultiValueMap<String, ?>> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url, method, requestEntity, String.class, urlParam).getBody();
    }

    public static String sendForm(String url, Map<String, ?> body) {
        return sendForm(url, null, body, HttpMethod.POST, new HttpHeaders());
    }

    public static String sendForm(String url, Map<String, ?> urlParam, Map<String, ?> body) {
        return sendForm(url, urlParam, body, HttpMethod.POST, new HttpHeaders());
    }

    public static String sendForm(String url, Map<String, ?> urlParam, Map<String, ?> body, HttpMethod method) {
        return sendForm(url, urlParam, body, method, new HttpHeaders());
    }

    public static String sendForm(String url, Map<String, ?> urlParam, Map<String, ?> body, HttpMethod method, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return send(url, urlParam, body, method, headers);
    }

    public static String sendJson(String url, Map<String, ?> body) {
        return sendJson(url, null, body, HttpMethod.POST, new HttpHeaders());
    }

    public static String sendJson(String url, Map<String, ?> urlParam, Map<String, ?> body) {
        return sendJson(url, urlParam, body, HttpMethod.POST, new HttpHeaders());
    }

    public static String sendJson(String url, Map<String, ?> urlParam, Map<String, ?> body, HttpMethod method) {
        return sendJson(url, urlParam, body, method, new HttpHeaders());
    }

    public static String sendJson(String url, Map<String, ?> urlParam, Map<String, ?> body, HttpMethod method, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return send(url, urlParam, body, method, headers);
    }

    /*** 复杂请求发送** @param url
     * @param urlParam 用?和&拼接在url后面的参数*
     * @param body     请求体
     * @param method   请求方式
     * @param headers  请求头
     * @return body*/
    public static String send(String url, Map<String, ?> urlParam, Map<String, ?> body, HttpMethod method, HttpHeaders headers) {
        if (urlParam == null) {
            urlParam = new HashMap<>(0);
        }
        // url参数拼接
        url = handleUrlParam(url, urlParam);
        HttpEntity<Map<String, ?>> requestEntity = null;
        if (Objects.equals(headers.getContentType(), MediaType.APPLICATION_JSON)) {
            requestEntity = new HttpEntity<>(body, headers);
        }
        if (Objects.equals(headers.getContentType(), MediaType.APPLICATION_FORM_URLENCODED)) {
            // body参数处理
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            Iterator<? extends Map.Entry<String, ?>> iterator = body.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ?> next = iterator.next();
                param.add(next.getKey(), next.getValue());
            }
            requestEntity = new HttpEntity<>(param, headers);
        }
        return restTemplate.exchange(url, method, requestEntity, String.class, urlParam).getBody();
    }

    /*** url参数拼接**
     * @param url
     * @param urlParam
     * @return
     */
    private static String handleUrlParam(String url, Map<String, ?> urlParam) {
        if (urlParam == null || urlParam.isEmpty()) {
            return url;
        }
        Iterator<? extends Map.Entry<String, ?>> iterator = urlParam.entrySet().iterator();
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");
        while (iterator.hasNext()) {
            Map.Entry<String, ?> entry = iterator.next();
            urlBuilder.append(entry.getKey()).append("={").append(entry.getKey()).append("}").append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        return urlBuilder.toString();
    }
}