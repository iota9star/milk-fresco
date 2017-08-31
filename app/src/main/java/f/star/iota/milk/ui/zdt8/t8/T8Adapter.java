package f.star.iota.milk.ui.zdt8.t8;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class T8Adapter extends BaseAdapter<T8ViewHolder, T8Bean> {

    @Override
    public T8ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new T8ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((T8ViewHolder) holder).bindView(mBeans);
    }
}
