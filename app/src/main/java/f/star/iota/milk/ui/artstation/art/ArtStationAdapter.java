package f.star.iota.milk.ui.artstation.art;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class ArtStationAdapter extends BaseAdapter<ArtStationViewHolder, ArtStationBean> {

    @Override
    public ArtStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArtStationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ArtStationViewHolder) holder).bindView(mBeans.get(position));
    }
}
