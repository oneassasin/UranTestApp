package com.onea.urantestapp.data.db.tables;

import com.annotatedsql.annotation.sql.Autoincrement;
import com.annotatedsql.annotation.sql.Column;
import com.annotatedsql.annotation.sql.NotNull;
import com.annotatedsql.annotation.sql.PrimaryKey;
import com.annotatedsql.annotation.sql.Schema;
import com.annotatedsql.annotation.sql.Table;
import com.annotatedsql.annotation.sql.Unique;

@Schema(className = "UranDB", dbName = "uran.db", dbVersion = 1)
public interface UranStore {

  interface BaseTable {

    @PrimaryKey
    @Autoincrement
    @Column(type = Column.Type.INTEGER)
    @NotNull
    String ID = "id";

    @NotNull
    @Column(type = Column.Type.INTEGER)
    @Unique
    String CREATED_AT = "created_at";

  }

  @Table(TimestampStore.TABLE_NAME)
  interface TimestampStore extends BaseTable {

    String TABLE_NAME = "timestamps";

    @NotNull
    @Column(type = Column.Type.INTEGER)
    @Unique
    String VALUE = "value";

  }
  
}
