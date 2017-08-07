package f.star.iota.milk.ui.umei.mei;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MEIAdapter extends BaseAdapter<MEIViewHolder, MEIBean> {

    @Override
    public MEIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MEIViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MEIViewHolder) holder).bindView(mBeans);
    }
}
