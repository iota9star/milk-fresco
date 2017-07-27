package f.star.iota.milk.ui.donmai;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.Contracts;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.ShowImageBean;
import f.star.iota.milk.util.ConfigUtils;


public class DonmaiViewHolder extends BaseViewHolder<DonmaiBean> {

    @BindView(R.id.text_view_tag)
    TextView mTextViewTag;
    @BindView(R.id.image_view_rating)
    ImageView mImageViewRating;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;

    public DonmaiViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<DonmaiBean> beans) {
        final DonmaiBean bean = beans.get(getAdapterPosition());
        mTextViewTag.setText(bean.getSize());
        String r = bean.getRating();
        if (ConfigUtils.getR(aContext)) {
            switch (r == null ? "o" : r) {
                case "s":
                    if (bean.getUrl() != null) {
                        Uri uri = Uri.parse(bean.getPreview());
                        if (uri != null) {
                            mSimpleDraweeView.setImageURI(uri);
                        }
                    }
                    mSimpleDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
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
                                                    Contracts.Menu.MENU_DANBOORU, null);
                                        }
                                    })
                                    .show();
                            return true;
                        }
                    });
                    mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<ShowImageBean> imgs = new ArrayList<>();
                            for (DonmaiBean bean : beans) {
                                imgs.add(new ShowImageBean(bean.getUrl(), bean.getPreview(), Contracts.Menu.MENU_DANBOORU,
                                        "作者：" + bean.getAuthor() + "\n\n" +
                                                "评级：" + bean.getRating() + "\n\n" +
                                                "分辨率：" + bean.getSize() + "\n\n" +
                                                "来源：" + bean.getSource() + "\n\n" +
                                                "标签：" + bean.getTags() + "\n\n" +
                                                "文件大小：" + Formatter.formatFileSize(mContext, bean.getFileSize()) + "\n\n" +
                                                "得分：" + bean.getScore()));
                            }
                            show(imgs);
                        }
                    });
                    break;
                case "q":
                    mSimpleDraweeView.setImageResource(R.drawable.ic_r15);
                    mSimpleDraweeView.setOnClickListener(null);
                    mSimpleDraweeView.setOnLongClickListener(null);
                    break;
                case "e":
                    mSimpleDraweeView.setImageResource(R.drawable.ic_r18);
                    mSimpleDraweeView.setOnClickListener(null);
                    mSimpleDraweeView.setOnLongClickListener(null);
                    break;
                default:
                    mSimpleDraweeView.setImageResource(R.drawable.ic_r_unknown);
                    mSimpleDraweeView.setOnClickListener(null);
                    mSimpleDraweeView.setOnLongClickListener(null);
                    break;
            }
        } else {
            if (bean.getUrl() != null) {
                Uri uri = Uri.parse(bean.getPreview());
                if (uri != null) {
                    mSimpleDraweeView.setImageURI(uri);
                }
            }
            mSimpleDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
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
                                            Contracts.Menu.MENU_DANBOORU, null);
                                }
                            })
                            .show();
                    return true;
                }
            });
            mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<ShowImageBean> imgs = new ArrayList<>();
                    for (DonmaiBean bean : beans) {
                        imgs.add(new ShowImageBean(bean.getUrl(), bean.getPreview(), Contracts.Menu.MENU_DANBOORU,
                                "作者：" + bean.getAuthor() + "\n\n" +
                                        "评级：" + bean.getRating() + "\n\n" +
                                        "分辨率：" + bean.getSize() + "\n\n" +
                                        "来源：" + bean.getSource() + "\n\n" +
                                        "标签：" + bean.getTags() + "\n\n" +
                                        "文件大小：" + Formatter.formatFileSize(mContext, bean.getFileSize()) + "\n\n" +
                                        "得分：" + bean.getScore()));
                    }
                    show(imgs);
                }
            });
        }
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
    }
}
