package f.star.iota.milk.ui.miaowu.wu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class WuAdapter extends BaseAdapter<WuViewHolder, WuBean> {

    @Override
    public WuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((WuViewHolder) holder).bindView(mBeans);
    }
}
