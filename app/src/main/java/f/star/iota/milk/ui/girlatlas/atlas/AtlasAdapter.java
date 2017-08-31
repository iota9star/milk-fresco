package f.star.iota.milk.ui.girlatlas.atlas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class AtlasAdapter extends BaseAdapter<AtlasViewHolder, AtlasBean> {

    @Override
    public AtlasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AtlasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AtlasViewHolder) holder).bindView(mBeans);
    }
}
