package f.star.iota.milk.ui.tngou;


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

public class TGViewHolder extends BaseViewHolder<TGBean.TngouBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;

    public TGViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<TGBean.TngouBean> beans) {
        final TGBean.TngouBean bean = beans.get(getAdapterPosition());
        if (bean.getImg() != null) {
            Uri uri = Uri.parse(bean.getImg());
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
                                copy(bean.getImg());
                            }
                        })
                        .setNegativeButton("浏览器打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                open(bean.getImg());
                            }
                        })
                        .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                download(bean.getImg(), bean.getImg(),
                                        Contracts.Menu.MENU_TNGOU, null);
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
                for (TGBean.TngouBean bean : beans) {
                    imgs.add(new ShowImageBean(bean.getImg(), bean.getImg(), Contracts.Menu.MENU_TNGOU,
                            "标题：" + bean.getTitle() + "\n\n" +
                                    "下载地址：" + bean.getImg()));
                }
                show(imgs);
            }
        });
        mTextViewDescription.setText(bean.getTitle());
    }
}
