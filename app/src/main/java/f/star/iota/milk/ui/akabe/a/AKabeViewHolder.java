package f.star.iota.milk.ui.akabe.a;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.fresco.FrescoLoader;
import f.star.iota.milk.ui.akabe.kabe.KabeFragment;


public class AKabeViewHolder extends BaseViewHolder<AKabeBean> {

    @BindView(R.id.card_view)
    CardView mCardView;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;

    public AKabeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final AKabeBean bean) {
        FrescoLoader.load(mSimpleDraweeView, bean.getPreview());
        mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("从浏览器打开")
                        .setNegativeButton("嗯", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                open(bean.getUrl());
                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) mContext).addFragment(KabeFragment.newInstance(bean.getUrl()));
            }
        });
        mTextViewDescription.setText(bean.getDescription());
    }
}
