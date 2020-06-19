package io.github.trumeen.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Administrator
 * @version $
 * @time 2020/3/29 19:07
 * @des
 * @updateAuthor $
 * @updateDate $
 * @updateDes
 */
public class Base64GsonConverterFacctory extends Converter.Factory {

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static Base64GsonConverterFacctory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new Base64GsonConverterFacctory(gson);
    }

    private final Gson gson;

    private Base64GsonConverterFacctory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new Base64GsonResponseBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new Base64GsonRequestBodyConverter<>(gson, adapter);
    }
}
