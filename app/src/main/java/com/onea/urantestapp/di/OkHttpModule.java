package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class OkHttpModule {

  @Provides
  @Singleton
  @NonNull
  public OkHttpClient provideOkHttpClient() {
    OkHttpClient okHttpClient = new OkHttpClient();
    return okHttpClient;
  }

}
