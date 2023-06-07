package com.mark.demo.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Service;
import retrofit2.http.GET;

import java.util.Map;

/**
 * @author mark
 * @date 2023年06月07日 10:23:02
 */
@RetrofitClient(baseUrl = "http://markabc.top")
public interface MarkTestApiClient {

    @GET(value = "test")
    Map test();

}
