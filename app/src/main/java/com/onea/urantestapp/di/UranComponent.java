package com.onea.urantestapp.di;

import com.onea.urantestapp.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    ApplicationModule.class,
    DBModule.class,
    ManagersModule.class,
    OkHttpModule.class,
    StorIOModule.class,
    UranAPIModule.class
})
public interface UranComponent extends UranGraph {

  class Initializer {

    private Initializer() {
    }

    public static UranComponent init(Application application) {
      return DaggerUranComponent.builder()
          .applicationModule(new ApplicationModule(application))
          .build();
    }

  }

}
