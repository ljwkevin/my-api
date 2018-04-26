package com.yd.feign.service;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yd on  2018-04-26
 * @description
 **/
public class GaodeMapApiTest {

    public static void main(String[] args) {
        String contextPath = "http://restapi.amap.com";
        MapApi api = Feign.builder()
//                .client(RibbonClient.create())//ConfigurationManager.loadPropertiesFromResources("sample-client.properties");
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .client(new OkHttpClient())
                .target(MapApi.class, contextPath);

        String key = "89e51c7379bc5544035285a938555498";
        String subdistrict = "3";
        //todo 待测试，返回数据 没磨合
        Result result = api.getcityBy(subdistrict, key);

        System.out.println("\n api:" + result);
    }


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    interface MapApi {
        @RequestLine("GET /v3/config/district?subdistrict={subdistrict}&key={key}")
        Result getcityBy(@Param(value = "subdistrict") String subdistrict, @Param(value = "key") String key);
    }

    static class Result {
        String status;
        String info;
        String infocode;
        String count;
        Suggestion suggestion;
        List<Districts> districts;
    }

    static class Suggestion {
        List<String> keywords;
        List<String> cities;
    }

    static class Districts {
        List<String> citycode;
        String adcode;
        String name;
        String center;
        String level;
        List<DistrictsLow> districts = new ArrayList<DistrictsLow>();
    }

    static class DistrictsLow {
        String citycode;
        String adcode;
        String name;
        String center;
        String level;
        List<DistrictsLow> districts = new ArrayList<DistrictsLow>();
    }
}
