package f.star.iota.milk.ui.gacha;


import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.Contracts;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.ShowImageBean;

public class GachaViewHolder extends BaseViewHolder<GachaBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeViewImage;
    @BindView(R.id.text_view_author)
    TextView mTextViewAuthor;
    @BindView(R.id.text_view_rank)
    TextView mTextViewRank;
    @BindView(R.id.simple_drawee_view_avatar)
    SimpleDraweeView mSimpleDraweeViewAvatar;

    public GachaViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<GachaBean> beans) {
        final GachaBean bean = beans.get(getAdapterPosition());
        if (bean.getUrl() != null) {
            Uri url = Uri.parse(bean.getUrl());
            if (url == null) return;
            mSimpleDraweeViewImage.setImageURI(url);
        }

        if (bean.getAvatar() != null) {
            Uri avatar = Uri.parse(bean.getAvatar());
            if (avatar == null) return;
            mSimpleDraweeViewAvatar.setImageURI(avatar);
        }
        mSimpleDraweeViewImage.setOnLongClickListener(new View.OnLongClickListener() {
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
                                        Contracts.Menu.MENU_GACHA, null);
                            }
                        })
                        .show();
                return true;
            }
        });
        mSimpleDraweeViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShowImageBean> imgs = new ArrayList<>();
                for (GachaBean bean : beans) {
                    imgs.add(new ShowImageBean(bean.getUrl(), bean.getPreview(), Contracts.Menu.MENU_GACHA,
                            "作者：" + bean.getUrl() + "\n\n" +
                                    "排名：" + bean.getRank() + "\n\n" +
                                    "下载地址：" + bean.getRank()));
                }
                show(imgs);
            }
        });
        mTextViewAuthor.setText(bean.getAuthor());
        mTextViewRank.setText(bean.getRank());
    }
}
