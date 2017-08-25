package f.star.iota.milk.ui.justinmaller.maller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MallerAdapter extends BaseAdapter<MallerViewHolder, MallerBean> {

    @Override
    public MallerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MallerViewHolder) holder).bindView(mBeans);
    }
}
