package f.star.iota.milk.ui.artstation.station;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class StationAdapter extends BaseAdapter<StationViewHolder, StationBean> {

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StationViewHolder) holder).bindView(mBeans);
    }
}
