package com.onea.urantestapp.di;

import com.onea.urantestapp.Application;

import dagger.Component;

@Component(modules = {
  ApplicationModule.class
})
public interface UranComponent {

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
