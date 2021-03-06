package f.star.iota.milk.ui.mmonly.only;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class OnlyAdapter extends BaseAdapter<OnlyViewHolder, OnlyBean> {

    @Override
    public OnlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OnlyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OnlyViewHolder) holder).bindView(mBeans);
    }
}
