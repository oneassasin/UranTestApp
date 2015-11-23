package com.onea.urantestapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public final class BroadcastObservable {

  private BroadcastObservable() {
  }

  public static Observable<Intent> fromBroadcast(Context context, IntentFilter intentFilter) {
    return Observable.create(subscriber -> {
      final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          if (subscriber.isUnsubscribed())
            return;
          subscriber.onNext(intent);
        }
      };

      final Subscription subscription = Subscriptions.create(() -> {
        context.unregisterReceiver(broadcastReceiver);
      });

      context.registerReceiver(broadcastReceiver, intentFilter);
      subscriber.add(subscription);
    });
  }

}
