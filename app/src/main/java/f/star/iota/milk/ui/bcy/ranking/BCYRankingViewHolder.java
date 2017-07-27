package f.star.iota.milk.ui.bcy.ranking;


import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.ui.bcy.ing.IngFragment;

public class BCYRankingViewHolder extends BaseViewHolder<BCYRankingBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_author)
    TextView mTextViewAuthor;
    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;
    @BindView(R.id.text_view_rank)
    TextView mTextViewRank;
    @BindView(R.id.simple_drawee_view_avatar)
    SimpleDraweeView mSimpleDraweeViewAvatar;

    public BCYRankingViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final BCYRankingBean bean) {
        if (bean.getPreview() != null) {
            Uri uri = Uri.parse(bean.getPreview());
            if (uri != null) {
                mSimpleDraweeView.setImageURI(uri);
            }
        }
        if (bean.getAvatar() != null) {
            Uri avatar = Uri.parse(bean.getAvatar());
            if (avatar != null) {
                mSimpleDraweeViewAvatar.setImageURI(avatar);
            }
        }
        mSimpleDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
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
        mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) mContext).addFragment(IngFragment.newInstance(bean.getUrl()));
            }
        });
        mTextViewAuthor.setText(bean.getAuthor());
        mTextViewRank.setText(bean.getRank());
        mTextViewDescription.setText(bean.getDescription());
    }
}
