package io.github.trumeen.net;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Administrator
 * @version $
 * @time 2020/3/29 19:08
 * @des
 * @updateAuthor $
 * @updateDate $
 * @updateDes
 */
class Base64GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    Base64GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = new String(Base64.decode(value.string(), Base64.DEFAULT));
//            System.out.println("response:" + response);
            return adapter.fromJson(response);
        } finally {
            value.close();
        }
    }
}
