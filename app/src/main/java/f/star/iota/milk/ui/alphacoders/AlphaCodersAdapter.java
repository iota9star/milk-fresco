package f.star.iota.milk.ui.alphacoders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class AlphaCodersAdapter extends BaseAdapter<AlphaCodersViewHolder, AlphaCodersBean> {

    @Override
    public AlphaCodersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlphaCodersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AlphaCodersViewHolder) holder).bindView(mBeans);
    }
}
