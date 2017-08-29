package f.star.iota.milk.ui.mmjpg.jpg;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class JpgAdapter extends BaseAdapter<JpgViewHolder, JpgBean> {

    @Override
    public JpgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JpgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((JpgViewHolder) holder).bindView(mBeans);
    }
}
