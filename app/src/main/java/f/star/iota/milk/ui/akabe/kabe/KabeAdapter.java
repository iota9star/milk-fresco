package f.star.iota.milk.ui.akabe.kabe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class KabeAdapter extends BaseAdapter<KabeViewHolder, KabeBean> {

    @Override
    public KabeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KabeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pure_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((KabeViewHolder) holder).bindView(mBeans);
    }
}
