package f.star.iota.milk.ui.yuriimg;


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
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.PCBean;
import f.star.iota.milk.fresco.FrescoLoader;


public class YuriImgViewHolder extends BaseViewHolder<YuriImgBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_tag)
    TextView mTextViewTag;
    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;
    @BindView(R.id.card_view)
    CardView mCardView;

    public YuriImgViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<YuriImgBean> beans) {
        final YuriImgBean bean = beans.get(getAdapterPosition());
        final HashMap<String, String> headers = bean.getHeaders();
        headers.put("Referer", "http://yuriimg.com/");
        headers.put("Host", "yuri.logacg.com");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept", "image/webp,image/*,*/*;q=0.8");
        FrescoLoader.load(mSimpleDraweeView, bean.getPreview(), headers);
        headers.put("Referer", "http://yuriimg.com/");
        headers.put("Host", "yuriimg.com");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
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
                                        Menus.MENU_YURIIMG, headers);
                            }
                        })
                        .show();
                return true;
            }
        });

        mTextViewTag.setText(bean.getSize());
        mTextViewDescription.setText(bean.getDescription());
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(getProcessingCompletedBeans(beans, headers));
            }
        });
        ((FloatingToolbar) ButterKnife.findById((Activity) mContext, R.id.floating_toolbar)).setClickListener(new FloatingToolbar.ItemClickListener() {
            @Override
            public void onItemClick(MenuItem menuItem) {
                batchDownload(getProcessingCompletedBeans(beans, headers));
            }

            @Override
            public void onItemLongClick(MenuItem menuItem) {

            }
        });
    }

    @Override
    protected List<PCBean> getProcessingCompletedBeans(List<YuriImgBean> beans, HashMap<String, String> headers) {
        List<PCBean> imgs = new ArrayList<>();
        for (YuriImgBean bean : beans) {
            imgs.add(new PCBean(bean.getUrl(), bean.getPreview(), Menus.MENU_YURIIMG,
                    "描述：" + bean.getDescription() + "\n\n" + "下载地址：" + bean.getUrl(),
                    headers));
        }
        return imgs;
    }
}
