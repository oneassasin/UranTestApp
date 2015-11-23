package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import com.onea.urantestapp.Application;
import com.onea.urantestapp.managers.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ManagersModule {

  @Provides
  @Singleton
  @NonNull
  public NetworkManager provideNetworkManager(Application application) {
    return new NetworkManager(application);
  }

}
