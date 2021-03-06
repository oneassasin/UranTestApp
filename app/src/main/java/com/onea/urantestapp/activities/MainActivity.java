package com.onea.urantestapp.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.onea.urantestapp.R;
import com.onea.urantestapp.fragments.MainFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public final class MainActivity extends RxAppCompatActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.activity_main_frame_layout, new MainFragment())
          .commit();
    }
  }

}
