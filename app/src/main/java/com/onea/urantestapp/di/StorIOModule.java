package com.onea.urantestapp.di;

import android.support.annotation.NonNull;

import com.onea.urantestapp.data.db.DBOpenHelper;
import com.onea.urantestapp.data.db.entities.Timestamp;
import com.onea.urantestapp.data.db.entities.TimestampStorIOSQLiteDeleteResolver;
import com.onea.urantestapp.data.db.entities.TimestampStorIOSQLiteGetResolver;
import com.onea.urantestapp.data.db.entities.TimestampStorIOSQLitePutResolver;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class StorIOModule {

  @Provides
  @Singleton
  @NonNull
  public StorIOSQLite provideStorIOSQLite(DBOpenHelper dbOpenHelper) {
    return DefaultStorIOSQLite.builder()
        .sqliteOpenHelper(dbOpenHelper)
        .addTypeMapping(Timestamp.class, SQLiteTypeMapping.<Timestamp>builder()
            .putResolver(new TimestampStorIOSQLitePutResolver())
            .getResolver(new TimestampStorIOSQLiteGetResolver())
            .deleteResolver(new TimestampStorIOSQLiteDeleteResolver())
            .build())
        .build();
  }

}
