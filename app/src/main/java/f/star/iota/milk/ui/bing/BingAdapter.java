package f.star.iota.milk.ui.bing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class BingAdapter extends BaseAdapter<BingViewHolder, BingBean.ImagesBean> {

    @Override
    public BingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BingViewHolder) holder).bindView(mBeans);
    }
}
