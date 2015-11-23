package com.onea.urantestapp.network;

import retrofit.http.GET;
import rx.Observable;

public interface UranAPI {

  @GET("test.php")
  Observable<retrofit.Response<String>> getTimestamp();

}
