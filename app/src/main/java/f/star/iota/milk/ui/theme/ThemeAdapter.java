package f.star.iota.milk.ui.theme;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.R;


public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private final List<ThemeBean> themes;
    private OnItemClickListener onItemClickListener;

    public ThemeAdapter() {
        themes = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ThemeBean theme = themes.get(position);
        if (theme.isSelected()) {
            holder.image.setImageResource(R.drawable.ic_item_theme_checked);
        } else {
            holder.image.setImageResource(R.drawable.ic_item_theme_uncheck);
        }
        holder.image.setColorFilter(ContextCompat.getColor(holder.mContext, theme.getColor()));
        holder.description.setText(theme.getDescription());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ThemeBean bean : themes) {
                    bean.setSelected(false);
                }
                theme.setSelected(true);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(theme.getTheme());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public void add(List<ThemeBean> themes) {
        int size = this.themes.size();
        this.themes.addAll(themes);
        notifyItemRangeInserted(size, themes.size());
    }

    public interface OnItemClickListener {
        void onClick(int theme);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final Context aContext;
        @BindView(R.id.image_view_image)
        ImageView image;
        @BindView(R.id.text_view_description)
        TextView description;
        @BindView(R.id.linear_layout_theme_container)
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            aContext = mContext.getApplicationContext();
            ButterKnife.bind(this, itemView);
        }
    }
}
