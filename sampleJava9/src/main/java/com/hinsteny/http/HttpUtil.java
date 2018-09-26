package com.hinsteny.http;


import com.hinsteny.BusinessException;
import com.hinsteny.Status;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Hinsteny
 * @date 2017-10-28
 * @copyright: 2017 All rights reserved.
 */
public class HttpUtil {

    private static HttpClient httpClient = HttpClient.newBuilder().build();

    static {
        System.err.println(httpClient.version().toString());
    }
    /**
     * 进行http-get请求, 返回自定义类型的结果对象
     * @param url
     * @param respnse 需要有一个入参为String的构造函数, 内部实现http返回文本值解析逻辑
     * @return
     * @throws BusinessException
     */
    public static <T> T sendGet(String  url, Class<T> respnse, boolean async) throws BusinessException {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .build();

            HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandler.asString();
            if (async) {
                asyncExcute(request, responseBodyHandler);
                return null;
            } else {
                String data = excute(request, responseBodyHandler);
                // 可以用fastjson进行返回值解析
                return respnse.getDeclaredConstructor(String.class).newInstance(data);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new BusinessException(Status.EXCEPTION_DATA.getCode(), Status.EXCEPTION_DATA.getMsg());
        } catch (Exception e) {
            throw new BusinessException(Status.EXCEPTION_DATA.getCode(), Status.EXCEPTION_DATA.getMsg());
        }

    }

    public static String sendPost(String  url, Map<String, Object> param) throws BusinessException{
        try {
            HttpRequest.BodyProcessor body = HttpRequest.BodyProcessor.fromString(param.toString(), Charset.forName("UTF-8"));
            HttpRequest request = HttpRequest.newBuilder(new URI(url))
                    .PUT(body)
                    .build();

            HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandler.asString();
            return excute(request, responseBodyHandler);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new BusinessException(Status.EXCEPTION_DATA.getCode(), Status.EXCEPTION_DATA.getMsg());
        }
    }

    private static <T> T excute(HttpRequest req, HttpResponse.BodyHandler<T> responseBodyHandler) throws BusinessException {
        System.out.println(req.version());
        try {
            HttpResponse<T> response;
            response = httpClient.send(req, responseBodyHandler);
            System.out.println(response.version());
            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new BusinessException(Status.EXCEPTION.getCode(), Status.EXCEPTION.getMsg());
    }

    private static <T> void asyncExcute(HttpRequest req, HttpResponse.BodyHandler<T> responseBodyHandler) throws BusinessException {
        System.out.println(req.version());
        CompletableFuture<HttpResponse<T>> response = httpClient.sendAsync(req, responseBodyHandler);
        response.thenAccept(result->{
            if (null != result) {
               System.err.println(result);
            }
        });
    }


    public static void main(String[] args) throws BusinessException{
        String url, response;
        url = "https://github.com/Hinsteny";
        HttpUtil.sendGet(url, String.class, true);
        response = HttpUtil.sendGet(url, String.class, false);
        System.err.println(response);
        url = "https://cn.bing.com";
        response = HttpUtil.sendPost(url, new HashMap<>());
        System.err.println(response);


    }
}
