package f.star.iota.milk.ui.animepictures.anime;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class AnimePictureAdapter extends BaseAdapter<AnimePictureViewHolder, AnimePictureBean> {

    @Override
    public AnimePictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnimePictureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AnimePictureViewHolder) holder).bindView(mBeans.get(position));
    }
}
