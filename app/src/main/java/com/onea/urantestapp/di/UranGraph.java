package com.onea.urantestapp.di;

import com.onea.urantestapp.fragments.MainFragment;
import com.onea.urantestapp.views.adapters.TimestampAdapter;

public interface UranGraph {

  void inject(MainFragment mainFragment);

  void inject(TimestampAdapter.TimestampViewHolder timestampViewHolder);

}
