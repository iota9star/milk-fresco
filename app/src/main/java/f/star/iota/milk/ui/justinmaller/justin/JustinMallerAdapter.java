package f.star.iota.milk.ui.justinmaller.justin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class JustinMallerAdapter extends BaseAdapter<JustinMallerViewHolder, JustinMallerBean> {

    @Override
    public JustinMallerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JustinMallerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((JustinMallerViewHolder) holder).bindView(mBeans.get(position));
    }
}
