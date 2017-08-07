package f.star.iota.milk.ui.umei.u;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class UMEIAdapter extends BaseAdapter<UMEIViewHolder, UMEIBean> {

    @Override
    public UMEIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UMEIViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UMEIViewHolder) holder).bindView(mBeans.get(position));
    }
}
