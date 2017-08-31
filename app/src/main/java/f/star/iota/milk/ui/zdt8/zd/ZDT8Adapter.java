package f.star.iota.milk.ui.zdt8.zd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class ZDT8Adapter extends BaseAdapter<ZDT8ViewHolder, ZDT8Bean> {

    @Override
    public ZDT8ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZDT8ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ZDT8ViewHolder) holder).bindView(mBeans.get(position));
    }
}
