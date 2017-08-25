package f.star.iota.milk.ui.kuvva.va;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class VaAdapter extends BaseAdapter<VaViewHolder, VaBean> {

    @Override
    public VaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VaViewHolder) holder).bindView(mBeans);
    }
}
