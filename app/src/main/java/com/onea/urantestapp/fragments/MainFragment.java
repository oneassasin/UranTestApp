package com.onea.urantestapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.onea.urantestapp.Application;
import com.onea.urantestapp.R;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class MainFragment extends RxFragment {

  @Bind(R.id.fragment_main_change_bg_button)
  Button changeBackgroundButton;
  @Bind(R.id.fragment_main_data_download_button)
  Button dataDownloadButton;
  @Bind(R.id.fragment_main_recycler_view)
  RecyclerView recyclerView;
  @Bind(R.id.fragment_main_relative_layout)
  RelativeLayout relativeLayout;
  @Inject
  StorIOSQLite storIOSQLite;

  private final Random random = new Random();

  public MainFragment() {
    Application.getApplication().getComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    onChangeBackgroundButtonClicked();
  }

  @OnClick(R.id.fragment_main_change_bg_button)
  public void onChangeBackgroundButtonClicked() {
    int red = random.nextInt(255);
    int green = random.nextInt(255);
    int blue = random.nextInt(255);
    int color = Color.rgb(red, green, blue);
    relativeLayout.setBackgroundColor(color);
  }

}
