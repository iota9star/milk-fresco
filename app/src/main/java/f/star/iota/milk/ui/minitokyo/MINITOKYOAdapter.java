package f.star.iota.milk.ui.minitokyo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class MINITOKYOAdapter extends BaseAdapter<MINITOKYOViewHolder, MINITOKYOBean> {

    @Override
    public MINITOKYOViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MINITOKYOViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MINITOKYOViewHolder) holder).bindView(mBeans);
    }
}
