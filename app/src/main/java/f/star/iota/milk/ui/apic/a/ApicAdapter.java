package f.star.iota.milk.ui.apic.a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class ApicAdapter extends BaseAdapter<ApicViewHolder, ApicBean> {

    @Override
    public ApicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ApicViewHolder) holder).bindView(mBeans.get(position));
    }
}
