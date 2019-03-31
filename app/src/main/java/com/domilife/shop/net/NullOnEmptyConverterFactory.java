package com.domilife.shop.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Administrator
 * @name MyAmazonAPI
 * @class name：com.demo.myamazonapi.api.interceptor
 * @class describe
 * @time 2018/6/20 18:20
 * @change
 * @class describe
 */

public class NullOnEmptyConverterFactory extends Converter.Factory {

  public static final NullOnEmptyConverterFactory create() {
    return new NullOnEmptyConverterFactory();
  }

  private NullOnEmptyConverterFactory() {
  }

  //将响应对象responseBody转成目标类型对象(也就是Call里给定的类型)
  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                          Retrofit retrofit) {
    //判断当前的类型是否是我们需要处理的类型
    if (NoBodyEntity.class.equals(type)) {
      //是则创建一个Converter返回转换数据
      return new Converter<ResponseBody, NoBodyEntity>() {
        @Override
        public NoBodyEntity convert(ResponseBody value) throws IOException {
          //这里直接返回null是因为我们不需要使用到响应体,本来也没有响应体.
          //返回的对象会存到response.body()里.
          return new NoBodyEntity();
        }
      };
    }
    return null;
  }

  //其它两个方法我们不需要使用到.所以不需要重写.
  @Override
  public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                        Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
    return null;
  }

  @Override
  public Converter<?, String> stringConverter(Type type, Annotation[] annotations,
                                              Retrofit retrofit) {
    return null;
  }
}
