package f.star.iota.milk.ui.meimeizi.mei;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MeiMeiZiAdapter extends BaseAdapter<MeiMeiZiViewHolder, MeiMeiZiBean> {

    @Override
    public MeiMeiZiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiMeiZiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MeiMeiZiViewHolder) holder).bindView(mBeans.get(position));
    }
}
