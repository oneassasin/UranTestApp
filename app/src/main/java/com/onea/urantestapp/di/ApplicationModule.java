package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import com.onea.urantestapp.Application;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @NonNull
  public Application provideApplication() {
    return application;
  }

}
