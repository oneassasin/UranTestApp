package com.onea.urantestapp.managers;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.onea.urantestapp.utils.BroadcastObservable;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.BehaviorSubject;

public final class NetworkManager {

  private final Context context;
  private final ConnectivityManager connectivityManager;

  private final BehaviorSubject<NetworkState> behaviorSubject = BehaviorSubject.create();

  public NetworkManager(Context context) {
    this.context = context;
    this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    setupNetworkListener();
  }

  private void setupNetworkListener() {
    IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    BroadcastObservable.fromBroadcast(context, intentFilter)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(intent -> {
          NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
          if (networkInfo == null) {
            behaviorSubject.onNext(NetworkState.NOT_CONNECTED);
            return;
          }
          if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            behaviorSubject.onNext(NetworkState.WIFI);
            return;
          }
          if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            behaviorSubject.onNext(NetworkState.MOBILE);
            return;
          }
          behaviorSubject.onNext(NetworkState.NOT_CONNECTED);
        });
  }

  public Observable<NetworkState> getNetworkObservable() {
    return behaviorSubject.asObservable();
  }

  public enum NetworkState {
    NOT_CONNECTED,
    MOBILE,
    WIFI
  }

}
