package f.star.iota.milk.ui.akabe.a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class AKabeAdapter extends BaseAdapter<AKabeViewHolder, AKabeBean> {

    @Override
    public AKabeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AKabeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AKabeViewHolder) holder).bindView(mBeans.get(position));
    }
}
