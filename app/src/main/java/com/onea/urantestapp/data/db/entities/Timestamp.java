package com.onea.urantestapp.data.db.entities;

import com.onea.urantestapp.data.db.tables.UranStore;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import java.util.Date;

@StorIOSQLiteType(table = UranStore.TimestampStore.TABLE_NAME)
public final class Timestamp {

  public Timestamp() {
  }

  public Timestamp(Long value) {
    this(null, new Date().getTime(), value);
  }

  public Timestamp(Long id, Long createdAt, Long value) {
    this.id = id;
    this.createdAt = createdAt;
    this.value = value;
  }

  @StorIOSQLiteColumn(name = UranStore.TimestampStore.VALUE)
  Long value;

  @StorIOSQLiteColumn(name = UranStore.BaseTable.ID, key = true)
  Long id;

  @StorIOSQLiteColumn(name = UranStore.BaseTable.CREATED_AT)
  Long createdAt;

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Timestamp timestamp = (Timestamp) o;

    if (value != null ? !value.equals(timestamp.value) : timestamp.value != null) return false;
    if (id != null ? !id.equals(timestamp.id) : timestamp.id != null) return false;
    return !(createdAt != null ? !createdAt.equals(timestamp.createdAt) : timestamp.createdAt != null);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Timestamp{" +
        "value=" + value +
        ", id=" + id +
        ", createdAt=" + createdAt +
        '}';
  }

}
