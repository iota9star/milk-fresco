package f.star.iota.milk.ui.isujin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class IsujinAdapter extends BaseAdapter<IsujinViewHolder, IsujinBean> {

    @Override
    public IsujinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IsujinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IsujinViewHolder) holder).bindView(mBeans);
    }
}
