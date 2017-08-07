package f.star.iota.milk.ui.gravuregirlz.gravure;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class GravureGirlZAdapter extends BaseAdapter<GravureGirlZViewHolder, GravureGirlZBean> {

    @Override
    public GravureGirlZViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GravureGirlZViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GravureGirlZViewHolder) holder).bindView(mBeans.get(position));
    }
}
