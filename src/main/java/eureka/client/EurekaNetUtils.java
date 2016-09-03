package eureka.client;

import okhttp3.*;
import okio.ByteString;

import java.io.IOException;

/**
 * Created by serv on 16/9/3.
 */
public class EurekaNetUtils {

    private static OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient();
    }

    public static void delete(String url){
        Request request = new Request.Builder().url(url).delete().build();
        try {
            Response execute = httpClient.newCall(request).execute();
            if(!execute.isSuccessful()){
                throw new EurekaClientException(execute.body().string(),execute.code());
            }
        } catch (IOException e) {
            throw new EurekaClientException(e,5000);
        }
    }

    public static String get(String url){
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response execute = httpClient.newCall(request).execute();
            if(!execute.isSuccessful()){
                throw new EurekaClientException(execute.body().string(),execute.code());
            }
            return execute.body().string();
        } catch (IOException e) {
            throw new EurekaClientException(e,5000);
        }
    }

    public static void put(String url){
        Request request = new Request.Builder().url(url).put(RequestBody.create(
                MediaType.parse("text/xml"), ByteString.EMPTY
        )).build();
        try {
            Response execute = httpClient.newCall(request).execute();
            if(!execute.isSuccessful()){
                throw new EurekaClientException(execute.body().string(),execute.code());
            }
        } catch (IOException e) {
            throw new EurekaClientException(e,5000);
        }
    }
}
