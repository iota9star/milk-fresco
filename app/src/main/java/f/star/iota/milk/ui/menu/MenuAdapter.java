package f.star.iota.milk.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lb.auto_fit_textview.AutoResizeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import f.star.iota.milk.R;
import f.star.iota.milk.ui.login.LoginActivity;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<MenuBean> mList;

    public MenuAdapter() {
        this.mList = new ArrayList<>();
    }

    public interface OnMenuItemClickListener {
        void onClick(MenuBean menu);
    }

    private OnMenuItemClickListener onMenuItemClickListener;

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        final MenuBean bean = mList.get(position);
        holder.mAutoFitTextViewName.setText(bean.getName());
        holder.mAutoFitTextViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onClick(bean);
                }
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

    class MenuViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simple_drawee_view_image)
        SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.image_button_more)
        ImageButton mImageButtonMore;
        @BindView(R.id.auto_fit_text_view_name)
        AutoResizeTextView mAutoFitTextViewName;
        private Context mContext;

        @OnClick(R.id.image_button_more)
        public void onClick() {
            showPopUpWindow();
        }

        public MenuViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        private void showPopUpWindow() {
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
            listPopupWindow.setAnchorView(mImageButtonMore);
            listPopupWindow.setModal(true);
            listPopupWindow.show();
        }
    }
}
