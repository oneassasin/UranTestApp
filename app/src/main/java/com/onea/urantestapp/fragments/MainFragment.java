package com.onea.urantestapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.onea.urantestapp.Application;
import com.onea.urantestapp.R;
import com.onea.urantestapp.data.db.entities.Timestamp;
import com.onea.urantestapp.data.db.tables.UranStore;
import com.onea.urantestapp.managers.NetworkManager;
import com.onea.urantestapp.network.UranAPI;
import com.onea.urantestapp.utils.NoInternetException;
import com.onea.urantestapp.utils.NotFoundException;
import com.onea.urantestapp.views.adapters.TimestampAdapter;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
  @Inject
  NetworkManager networkManager;
  @Inject
  UranAPI uranAPI;

  private final Random random = new Random();

  public MainFragment() {
    Application.getApplication().getComponent().inject(this);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
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
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    TimestampAdapter timestampAdapter = new TimestampAdapter();
    recyclerView.setAdapter(timestampAdapter);
    storIOSQLite.get()
        .listOfObjects(Timestamp.class)
        .withQuery(Query.builder()
            .table(UranStore.TimestampStore.TABLE_NAME)
            .limit(5)
            .orderBy(String.format("%s DESC", UranStore.TimestampStore.VALUE))
            .distinct(true)
            .build())
        .prepare()
        .createObservable()
        .debounce(1, TimeUnit.SECONDS)
        .compose(this.bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(timestampAdapter::setTimestampList);
  }

  @OnClick(R.id.fragment_main_change_bg_button)
  public void onChangeBackgroundButtonClicked() {
    int red = random.nextInt(255);
    int green = random.nextInt(255);
    int blue = random.nextInt(255);
    int color = Color.rgb(red, green, blue);
    relativeLayout.setBackgroundColor(color);
  }

  @OnClick(R.id.fragment_main_data_download_button)
  public void onDownloadButtonClicked() {
    networkManager.getNetworkObservable()
        .compose(this.bindToLifecycle())
        .flatMap(networkState -> {
          if (networkState == NetworkManager.NetworkState.NOT_CONNECTED)
            return Observable.error(new NoInternetException());
          return Observable.just(networkState);
        })
        .flatMap(result -> uranAPI.getTimestamp())
        .timeout(10, TimeUnit.SECONDS, Observable.error(new TimeoutException()))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(stringResponse -> {
          String[] array = stringResponse.body().split("\n");
          for (String string : array) {
            if (string.startsWith("    [REQUEST_TIME]")) {
              String time = string.split(" => ")[1];
              return Observable.just(time);
            }
          }
          return Observable.error(new NotFoundException());
        })
        .flatMap(value -> storIOSQLite.put()
            .object(new Timestamp(Long.valueOf(value) * 1000))
            .prepare()
            .createObservable())
        .subscribe(result -> {
        }, throwable -> {
          if (throwable instanceof NoInternetException) {
            Snackbar
                .make(getView(), R.string.error_no_internet, Snackbar.LENGTH_LONG)
                .show();
            return;
          }
          if (throwable instanceof NotFoundException) {
            Snackbar
                .make(getView(), R.string.error_not_found, Snackbar.LENGTH_LONG)
                .show();
            return;
          }
          if (throwable instanceof TimeoutException) {
            Snackbar
                .make(getView(), R.string.error_timeout, Snackbar.LENGTH_LONG)
                .show();
            return;
          }
          Snackbar
              .make(getView(), R.string.error_unknown, Snackbar.LENGTH_LONG)
              .show();
        });
  }

}
