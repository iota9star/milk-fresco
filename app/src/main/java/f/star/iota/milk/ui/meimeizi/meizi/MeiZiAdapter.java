package f.star.iota.milk.ui.meimeizi.meizi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MeiZiAdapter extends BaseAdapter<MeiZiViewHolder, MeiZiBean> {

    @Override
    public MeiZiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiZiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MeiZiViewHolder) holder).bindView(mBeans);
    }
}
