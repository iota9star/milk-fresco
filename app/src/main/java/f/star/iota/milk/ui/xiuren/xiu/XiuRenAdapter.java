package f.star.iota.milk.ui.xiuren.xiu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class XiuRenAdapter extends BaseAdapter<XiuRenViewHolder, XiuRenBean> {

    @Override
    public XiuRenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XiuRenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((XiuRenViewHolder) holder).bindView(mBeans.get(position));
    }
}
