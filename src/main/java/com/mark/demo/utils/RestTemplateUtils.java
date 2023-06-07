package com.mark.demo.utils;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class RestTemplateUtils {

    private static RestTemplate restTemplate = new RestTemplate();

    private HttpEntity httpEntity;

    /**
     * 构造函数已经私有化，只能通过create方法构造HttpContent
     *
     * @return HttpContent
     */
    public static RestTemplateUtils create() {
        return new RestTemplateUtils();
    }

    /**
     * get请求返回模板对象
     *
     * @param url   url
     * @param clazz T.class
     * @param <T>   T
     * @return T
     */
    public static <T> T getForObj(String url, Class<T> clazz) {
        return RestTemplateUtils.restTemplate.getForObject(url, clazz);
    }

    /**
     * get请求返回模板对象
     *
     * @param url   url
     * @param clazz t.class
     * @param <T>   模板
     * @return T
     */
    public <T> T postForObj(String url, Class<T> clazz) {
        this.build();
        return restTemplate.postForObject(url, this.httpEntity, clazz);
    }

    /**
     * header
     */
    private HttpHeaders httpHeaders;

    private String contentType;

    /**
     * body
     */
    private HashMap<String, Object> params;

    private RestTemplateUtils() {
    }

    /**
     * 添加Header内容
     *
     * @param headerName  key
     * @param headerValue value
     * @return HttpContent
     */
    public RestTemplateUtils addHeader(String headerName, String headerValue) {
        if (Objects.isNull(httpHeaders)) {
            httpHeaders = new HttpHeaders();
        }
        httpHeaders.add(headerName, headerValue);
        return this;
    }

    /**
     * 设置请求体
     *
     * @param key   key
     * @param value value
     * @return HttpContent
     */
    public RestTemplateUtils addBody(String key, Object value) {
        if (Objects.isNull(params)) {
            params = new HashMap<>();
        }
        params.put(key, value);
        return this;
    }

    /**
     * 设置content-Type
     *
     * @param contentType string
     * @return HttpContent
     */
    public RestTemplateUtils setContentType(String contentType) {
        if (!StringUtils.hasLength(contentType)) {
            throw new RuntimeException("content-Type can not be empty,please check your mediaType and try again ~");
        }
        this.contentType = contentType;
        if (Objects.isNull(httpHeaders)) {
            httpHeaders = new HttpHeaders();
        }
        httpHeaders.setContentType(MediaType.parseMediaType(contentType));
        return this;
    }

    /**
     * 构建HttpEntity对象
     * 不设置Content-Type默认application/json
     */
    private void build() {
        if (Objects.isNull(this.params)) {
            throw new RuntimeException("body must be not empty,please add body and try again ~");
        }
        if (Objects.isNull(contentType) || MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
            this.setContentType(MediaType.APPLICATION_JSON_VALUE);
            JSONObject jsonObject = new JSONObject();
            for (String s : params.keySet()) {
                try {
                    jsonObject.put(s, params.get(s));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            String body = jsonObject.toString();
            this.httpEntity = new HttpEntity<>(body, httpHeaders);
        } else if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)) {
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            for (String s : params.keySet()) {
                formData.add(s, params.get(s));
            }
            this.httpEntity = new HttpEntity<>(formData, httpHeaders);
        } else {
            throw new RuntimeException("sorry this tool only support content-Type include {application/json,multipart/form-data}");
        }
    }

    public static void main(String[] args) {
        Map json = RestTemplateUtils.getForObj("https://markabc.top/test", Map.class); //发送post请求
        System.out.println("json = " + json);
    }
}