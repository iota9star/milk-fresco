package f.star.iota.milk.ui.mmjpg.mm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MmjpgAdapter extends BaseAdapter<MmjpgViewHolder, MmjpgBean> {

    @Override
    public MmjpgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MmjpgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MmjpgViewHolder) holder).bindView(mBeans.get(position));
    }
}
