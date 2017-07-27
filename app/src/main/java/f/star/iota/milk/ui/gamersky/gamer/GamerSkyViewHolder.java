package f.star.iota.milk.ui.gamersky.gamer;


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
import f.star.iota.milk.ui.gamersky.sky.SkyFragment;


public class GamerSkyViewHolder extends BaseViewHolder<GamerSkyBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;

    public GamerSkyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final GamerSkyBean bean) {
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
                ((BaseActivity) mContext).addFragment(SkyFragment.newInstance(bean.getUrl().replace(".shtml", "_")));
            }
        });
        mTextViewDescription.setText(bean.getDescription());
    }
}
