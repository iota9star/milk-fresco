package f.star.iota.milk.ui.zerochan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class ZerochanAdapter extends BaseAdapter<ZerochanViewHolder, ZerochanBean> {

    @Override
    public ZerochanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZerochanViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ZerochanViewHolder) holder).bindView(mBeans);
    }
}
