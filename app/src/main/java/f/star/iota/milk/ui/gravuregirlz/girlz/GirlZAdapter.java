package f.star.iota.milk.ui.gravuregirlz.girlz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class GirlZAdapter extends BaseAdapter<GirlZViewHolder, GirlZBean> {

    @Override
    public GirlZViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlZViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GirlZViewHolder) holder).bindView(mBeans);
    }
}
