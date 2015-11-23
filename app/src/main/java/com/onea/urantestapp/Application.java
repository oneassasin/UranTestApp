package com.onea.urantestapp;

import com.onea.urantestapp.di.UranComponent;

public final class Application extends android.app.Application {

  private UranComponent component;

  public UranComponent getComponent() {
    return component;
  }

  private static Application application;

  public static Application getApplication() {
    return application;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    component = UranComponent.Initializer.init(this);
  }

}
