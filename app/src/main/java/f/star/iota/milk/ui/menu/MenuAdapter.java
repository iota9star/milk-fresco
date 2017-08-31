package f.star.iota.milk.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.R;
import f.star.iota.milk.ui.login.LoginActivity;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private final List<MenuBean> mList;
    private OnMenuItemClickListener onMenuItemClickListener;

    public MenuAdapter() {
        this.mList = new ArrayList<>();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, int position) {
        final MenuBean bean = mList.get(position);
        holder.mButtonIndex.setText(String.valueOf(position + 1));
        holder.mButtonName.setText(bean.getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onClick(bean);
                }
            }
        });
        holder.mButtonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.showPopUpWindow(holder.mCardView);
            }
        });
        if (bean.getBanner() != null) {
            Uri uri = Uri.parse(bean.getBanner());
            if (uri != null) {
                holder.mSimpleDraweeView.setImageURI(uri);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(List<MenuBean> menu) {
        mList.addAll(menu);
        notifyItemRangeInserted(0, menu.size());
    }

    public interface OnMenuItemClickListener {
        void onClick(MenuBean menu);
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.simple_drawee_view_image)
        SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.card_view)
        CardView mCardView;
        @BindView(R.id.button_name)
        Button mButtonName;
        @BindView(R.id.button_index)
        Button mButtonIndex;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        private void showPopUpWindow(View view) {
            final MenuBean bean = mList.get(getAdapterPosition());
            String[] menus;
            if (bean.getLoginUrl() != null && bean.getLoginUrl().length() > 5) {
                menus = new String[]{"主页", "登录"};
            } else {
                menus = new String[]{"主页"};
            }
            final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
            listPopupWindow.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,
                    menus));
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (pos == 0) {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bean.getUrl())));
                    } else if (pos == 1) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.putExtra("login_url", bean.getLoginUrl());
                        mContext.startActivity(intent);
                    }
                }
            });
            listPopupWindow.setWidth((int) mContext.getResources().getDimension(R.dimen.v72dp));
            listPopupWindow.setAnchorView(view);
            listPopupWindow.setModal(true);
            listPopupWindow.show();
        }
    }
}
