package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import com.onea.urantestapp.Application;
import com.onea.urantestapp.data.db.DBOpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DBModule {

  @Provides
  @Singleton
  @NonNull
  public DBOpenHelper provideDbOpenHelper(Application application) {
    return new DBOpenHelper(application);
  }

}
