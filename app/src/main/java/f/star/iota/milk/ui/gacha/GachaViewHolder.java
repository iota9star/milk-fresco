package f.star.iota.milk.ui.gacha;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.PCBean;
import f.star.iota.milk.fresco.FrescoLoader;

public class GachaViewHolder extends BaseViewHolder<GachaBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeViewImage;
    @BindView(R.id.text_view_author)
    TextView mTextViewAuthor;
    @BindView(R.id.text_view_rank)
    TextView mTextViewRank;
    @BindView(R.id.simple_drawee_view_avatar)
    SimpleDraweeView mSimpleDraweeViewAvatar;
    @BindView(R.id.card_view)
    CardView mCardView;

    public GachaViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<GachaBean> beans) {
        final GachaBean bean = beans.get(getAdapterPosition());
        FrescoLoader.load(mSimpleDraweeViewImage, bean.getPreview());
        FrescoLoader.load(mSimpleDraweeViewAvatar, bean.getAvatar());
        mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否下载")
                        .setNeutralButton("复制地址", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copy(bean.getUrl());
                            }
                        })
                        .setNegativeButton("浏览器打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                open(bean.getUrl());
                            }
                        })
                        .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                download(bean.getUrl(), bean.getPreview(),
                                        Menus.MENU_GACHA, null);
                            }
                        })
                        .show();
                return true;
            }
        });
        mTextViewAuthor.setText(bean.getAuthor());
        mTextViewRank.setText(bean.getRank());
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(getProcessingCompletedBeans(beans));
            }
        });
        ((FloatingToolbar) ButterKnife.findById((Activity) mContext, R.id.floating_toolbar)).setClickListener(new FloatingToolbar.ItemClickListener() {
            @Override
            public void onItemClick(MenuItem menuItem) {
                batchDownload(getProcessingCompletedBeans(beans));
            }

            @Override
            public void onItemLongClick(MenuItem menuItem) {

            }
        });
    }

    @Override
    protected List<PCBean> getProcessingCompletedBeans(List<GachaBean> beans) {
        List<PCBean> imgs = new ArrayList<>();
        for (GachaBean bean : beans) {
            imgs.add(new PCBean(bean.getUrl(), bean.getPreview(), Menus.MENU_GACHA,
                    "作者：" + bean.getUrl() + "\n\n" +
                            "排名：" + bean.getRank() + "\n\n" +
                            "下载地址：" + bean.getRank()));
        }
        return imgs;
    }
}
