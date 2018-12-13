package eureka.client;

import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import okhttp3.*;
import okio.ByteString;

import java.io.IOException;

/**
 * Created by serv on 16/9/3.
 */
public class EurekaNetUtils {

    private static OkHttpClient httpClient;

    private static EurekaXmlJacksonCodec codec;

    static {
        httpClient = new OkHttpClient();
        codec = new EurekaXmlJacksonCodec();
    }

    public static void delete(String url) {
        Request request = new Request.Builder().url(url).delete().build();
        try {
            Response execute = httpClient.newCall(request).execute();
            if (!execute.isSuccessful()) {
                throw new EurekaClientException(execute.body().string(), execute.code());
            }
        } catch (IOException e) {
            throw new EurekaClientException(e, 5000);
        }
    }

    public static <T> T get(String url, Class<T> tClass) {
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response execute = httpClient.newCall(request).execute();
            if (!execute.isSuccessful()) {
                throw new EurekaClientException(execute.body().string(), execute.code());
            }
            String body = execute.body().string();
            if (body != null) {
                return codec.getObjectMapper(tClass).readValue(body, tClass);
            }
            throw new EurekaClientException("error body: " + body, 10000);
        } catch (IOException e) {
            throw new EurekaClientException(e, 5000);
        }
    }

    public static <T> void post(String url, Class<T> tClass, T obj) {
        try {
            Request request = new Request.Builder().url(url)
                    .header("Content-Type","application/xml")
                    .post(RequestBody.create(
                    MediaType.parse("application/xml"),
                    codec.getObjectMapper(tClass).writeValueAsBytes(obj)
            )).build();
            Response execute = httpClient.newCall(request).execute();
            if (!execute.isSuccessful()) {
                throw new EurekaClientException(execute.body().string(), execute.code());
            }
        } catch (IOException e) {
            throw new EurekaClientException(e, 5000);
        }
    }

    public static void put(String url) {
        Request request = new Request.Builder().url(url).put(RequestBody.create(
                MediaType.parse("text/xml"), ByteString.EMPTY
        )).build();
        try {
            Response execute = httpClient.newCall(request).execute();
            if (!execute.isSuccessful()) {
                throw new EurekaClientException(execute.body().string(), execute.code());
            }
        } catch (IOException e) {
            throw new EurekaClientException(e, 5000);
        }
    }
}
