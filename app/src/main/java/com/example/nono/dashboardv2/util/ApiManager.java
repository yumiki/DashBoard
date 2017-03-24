package com.example.nono.dashboardv2.util;

import android.util.Log;

import com.example.nono.dashboardv2.data.Meteo;
import com.example.nono.dashboardv2.data.Velib;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;
import rx.Observer;

/**
 * Created by nono on 24/03/2017.
 */

public class ApiManager {
    public interface ApiManagerService{
        @GET("/api/formats/{id}")
        Meteo getMeteo(@Path("id") int id);

        //@GET("/api/formats/{id}")
        //Meteo getMeteo(@Path("id") int id);
        @GET("/search/?dataset=stations-velib-disponibilites-en-temps-reel&q=20+Rue+Guillaume+Bertrand&facet=banking&facet=bonus&facet=status&facet=contract_name")
        Observable<Velib> getVelib();

        @GET("/search/")
        Observable<Velib> getVelib(@Query("dataset") String dataset,
                                   @Query("q") String address,
                                   @Query("facet")List<String> facets);
    }

    private static final String ENDPOINT_LOCAL = "http://192.168.0.11";
    //https://opendata.paris.fr/api/records/1.0/search/?dataset=stations-velib-disponibilites-en-temps-reel&q=20+Rue+Guillaume+Bertrand&facet=banking&facet=bonus&facet=status&facet=contract_name
    private static final String ENDPOINT_DIST = "https://opendata.paris.fr/api/records/1.0";

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
            .create();

    private static RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Cache-Control", "no-cache");
            request.addHeader("Accept", "*/*");
        }
    };

    private static final RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT_LOCAL)
            .setConverter(new GsonConverter(gson))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setRequestInterceptor(requestInterceptor)
            .setErrorHandler(cause -> {
                Response r = cause.getResponse();
                if (r != null && r.getStatus() == 405) {
                    //MainActivity.this.notAllowed();
                }
                if (r != null && r.getStatus() == 404) {
                    //MainActivity.this.notAllowed();
                    Log.v("ApiFail","404 !!!!!");
                    //MainActivity.
                }
                //assert r != null;
                //Log.v("ApiFail",r.getBody().toString());
                return cause;
            })
            .build();

    private static final RestAdapter restAdapterDist = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT_DIST)
            .setConverter(new GsonConverter(gson))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setRequestInterceptor(requestInterceptor)
            .setErrorHandler(cause -> {
                Response r = cause.getResponse();
                if (r != null && r.getStatus() == 405) {
                    //MainActivity.this.notAllowed();
                }
                if (r != null && r.getStatus() == 404) {
                    //MainActivity.this.notAllowed();
                    Log.v("ApiFail","404 !!!!!");
                    //MainActivity.
                }
                return cause;
            })
            .build();

    public static ApiManagerService apiManagerLocal = restAdapter.create(ApiManagerService.class);
    public static ApiManagerService apiManagerDist = restAdapterDist.create(ApiManagerService.class);
}
