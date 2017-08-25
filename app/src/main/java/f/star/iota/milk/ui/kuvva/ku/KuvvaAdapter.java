package f.star.iota.milk.ui.kuvva.ku;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class KuvvaAdapter extends BaseAdapter<KuvvaViewHolder, KuvvaBean> {

    @Override
    public KuvvaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KuvvaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((KuvvaViewHolder) holder).bindView(mBeans.get(position));
    }
}
