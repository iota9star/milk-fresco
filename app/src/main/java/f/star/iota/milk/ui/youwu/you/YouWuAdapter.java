package f.star.iota.milk.ui.youwu.you;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class YouWuAdapter extends BaseAdapter<YouWuViewHolder, YouWuBean> {

    @Override
    public YouWuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new YouWuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((YouWuViewHolder) holder).bindView(mBeans.get(position));
    }
}
