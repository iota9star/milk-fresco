package f.star.iota.milk.ui.donmai;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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


public class DonmaiViewHolder extends BaseViewHolder<DonmaiBean> {

    @BindView(R.id.text_view_tag)
    TextView mTextViewTag;
    @BindView(R.id.image_view_rating)
    ImageView mImageViewRating;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.card_view)
    CardView mCardView;

    public DonmaiViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<DonmaiBean> beans) {
        final DonmaiBean bean = beans.get(getAdapterPosition());
        mTextViewTag.setText(bean.getSize());
        String r = bean.getRating();
        FrescoLoader.load(mSimpleDraweeView, bean.getPreview());
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
                                        Menus.MENU_DANBOORU, null);
                            }
                        })
                        .show();
                return true;
            }
        });
        switch (r == null ? "o" : r) {
            case "s":
                mImageViewRating.setImageResource(R.drawable.ic_bookmark_green_24dp);
                break;
            case "q":
                mImageViewRating.setImageResource(R.drawable.ic_bookmark_yellow_24dp);
                break;
            case "e":
                mImageViewRating.setImageResource(R.drawable.ic_bookmark_red_24dp);
                break;
            default:
                mImageViewRating.setImageResource(R.drawable.ic_bookmark_blue_24dp);
                break;
        }
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
    protected List<PCBean> getProcessingCompletedBeans(List<DonmaiBean> beans) {
        List<PCBean> imgs = new ArrayList<>();
        for (DonmaiBean bean : beans) {
            imgs.add(new PCBean(bean.getUrl(), bean.getPreview(), Menus.MENU_DANBOORU,
                    "作者：" + bean.getAuthor() + "\n\n" +
                            "评级：" + bean.getRating() + "\n\n" +
                            "分辨率：" + bean.getSize() + "\n\n" +
                            "来源：" + bean.getSource() + "\n\n" +
                            "标签：" + bean.getTags() + "\n\n" +
                            "文件大小：" + Formatter.formatFileSize(mContext, bean.getFileSize()) + "\n\n" +
                            "得分：" + bean.getScore() + "\n\n" +
                            "下载地址：" + bean.getUrl()
            ));
        }
        return imgs;
    }
}
