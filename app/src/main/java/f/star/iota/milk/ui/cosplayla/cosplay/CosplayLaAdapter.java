package f.star.iota.milk.ui.cosplayla.cosplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class CosplayLaAdapter extends BaseAdapter<CosplayLaViewHolder, CosplayLaBean> {

    @Override
    public CosplayLaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CosplayLaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CosplayLaViewHolder) holder).bindView(mBeans.get(position));
    }
}
