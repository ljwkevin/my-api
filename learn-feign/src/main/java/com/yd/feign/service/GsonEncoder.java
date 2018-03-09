package com.yd.feign.service;

import com.google.gson.Gson;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class GsonEncoder implements Encoder {
    private final Gson gson;
 
    public GsonEncoder() {
      this.gson = new Gson();
    }
 
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
      template.body(gson.toJson(object, bodyType));
    }
  }