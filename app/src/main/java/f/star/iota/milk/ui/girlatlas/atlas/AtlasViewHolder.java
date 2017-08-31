package f.star.iota.milk.ui.girlatlas.atlas;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.PCBean;
import f.star.iota.milk.fresco.FrescoLoader;

public class AtlasViewHolder extends BaseViewHolder<AtlasBean> {
    @BindView(R.id.card_view)
    CardView mCardView;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;

    public AtlasViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<AtlasBean> beans) {
        final AtlasBean bean = beans.get(getAdapterPosition());
        final HashMap<String, String> headers = bean.getHeaders();
        String referer = headers.get("Referer");
        referer = referer.replace("?display=2", "");
        headers.put("Host", "girlatlas.b0.upaiyun.com");
        headers.put("Referer", referer);
        headers.put("accept", "image/webp,image/*,*/*;q=0.8");
        beans.get(getAdapterPosition()).setHeaders(headers);
        FrescoLoader.load(mSimpleDraweeView, bean.getUrl(), headers);
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
                                download(bean.getUrl(), bean.getUrl(),
                                        Menus.MENU_GIRL_ATLAS, headers);
                            }
                        })
                        .show();
                return true;
            }
        });
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
    protected List<PCBean> getProcessingCompletedBeans(List<AtlasBean> beans) {
        List<PCBean> imgs = new ArrayList<>();
        for (AtlasBean bean : beans) {
            imgs.add(new PCBean(bean.getUrl(), bean.getUrl(), Menus.MENU_GIRL_ATLAS,
                    "下载地址：" + bean.getUrl(), bean.getHeaders()));
        }
        return imgs;
    }
}
