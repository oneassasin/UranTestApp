package com.onea.urantestapp.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onea.urantestapp.Application;
import com.onea.urantestapp.R;
import com.onea.urantestapp.data.db.entities.Timestamp;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class TimestampAdapter extends RecyclerView.Adapter<TimestampAdapter.TimestampViewHolder> {

  private final List<Timestamp> timestampList = new LinkedList<>();

  @Override
  public TimestampAdapter.TimestampViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.item_material_single_text, parent, false);
    return new TimestampViewHolder(view);
  }

  @Override
  public void onBindViewHolder(TimestampAdapter.TimestampViewHolder holder, int position) {
    Timestamp timestamp = timestampList.get(position);
    holder.setTimestamp(timestamp);
  }

  @Override
  public int getItemCount() {
    return timestampList.size();
  }

  public void setTimestampList(List<Timestamp> timestampList) {
    this.notifyItemRangeRemoved(0, this.timestampList.size());
    this.timestampList.clear();
    for (Timestamp timestamp : timestampList)
      this.timestampList.add(timestamp);
    this.notifyItemRangeInserted(0, this.timestampList.size());
  }

  public final class TimestampViewHolder extends RecyclerView.ViewHolder {

    private Timestamp timestamp;

    @Inject
    DateFormat simpleDateFormat;

    @Bind(R.id.item_material_single_text_text_view)
    TextView textView;

    public TimestampViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      Application.getApplication().getComponent().inject(this);
    }

    public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
      textView.setText(simpleDateFormat.format(timestamp.getValue()));
    }

  }

}
