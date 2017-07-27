package f.star.iota.milk.ui.booru;

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


public class BooruViewHolder extends BaseViewHolder<BooruBean> {

    @BindView(R.id.text_view_tag)
    TextView mTextViewTag;
    @BindView(R.id.image_view_rating)
    ImageView mImageViewRating;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;

    public BooruViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<BooruBean> beans) {
        final BooruBean bean = beans.get(getAdapterPosition());
        mTextViewTag.setText(bean.getSize());
        if (bean.getPreview() != null) {
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
                                        Contracts.Menu.MENU_YANDE + "_" + Contracts.Menu.MENU_KONACHAN + "_" + Contracts.Menu.MENU_LOLIBOORU, null);
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
                for (BooruBean bean : beans) {
                    imgs.add(new ShowImageBean(bean.getUrl(), bean.getPreview(), Contracts.Menu.MENU_YANDE + "_" + Contracts.Menu.MENU_KONACHAN + "_" + Contracts.Menu.MENU_LOLIBOORU,
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
        String r = bean.getRating();
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
