package f.star.iota.milk.ui.taotutt.tao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class TAOTUTTAdapter extends BaseAdapter<TAOTUTTViewHolder, TAOTUTTBean> {

    @Override
    public TAOTUTTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TAOTUTTViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TAOTUTTViewHolder) holder).bindView(mBeans.get(position));
    }
}
