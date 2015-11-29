package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import com.onea.urantestapp.network.UranAPI;
import com.onea.urantestapp.utils.ToStringConverterFactory;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public final class UranAPIModule {

  @Provides
  @Singleton
  @NonNull
  public UranAPI provideUranAPI(OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://android-logs.uran.in.ua/")
        .client(okHttpClient)
        .addConverterFactory(new ToStringConverterFactory())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    return retrofit.create(UranAPI.class);
  }

}
