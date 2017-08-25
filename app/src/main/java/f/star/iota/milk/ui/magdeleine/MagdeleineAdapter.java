package f.star.iota.milk.ui.magdeleine;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MagdeleineAdapter extends BaseAdapter<MagdeleineViewHolder, MagdeleineBean> {

    @Override
    public MagdeleineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MagdeleineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MagdeleineViewHolder) holder).bindView(mBeans);
    }
}
