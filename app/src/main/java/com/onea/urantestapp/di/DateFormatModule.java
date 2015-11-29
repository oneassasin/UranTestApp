package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DateFormatModule {

  @Provides
  @Singleton
  @NonNull
  public DateFormat provideDateFormat() {
    return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
  }

}
