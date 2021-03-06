package f.star.iota.milk.ui.miaowu.miao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MiaoWuAdapter extends BaseAdapter<MiaoWuViewHolder, MiaoWuBean> {

    @Override
    public MiaoWuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MiaoWuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MiaoWuViewHolder) holder).bindView(mBeans.get(position));
    }
}
