package f.star.iota.milk.ui.mmjpg.mm;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.fresco.FrescoLoader;
import f.star.iota.milk.ui.mmjpg.jpg.JpgFragment;


public class MmjpgViewHolder extends BaseViewHolder<MmjpgBean> {

    @BindView(R.id.card_view)
    CardView mCardView;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_tag)
    TextView mTextViewTag;
    @BindView(R.id.text_view_description)
    TextView mTextViewDescription;

    public MmjpgViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final MmjpgBean bean) {
        HashMap<String, String> headers = bean.getHeaders();
        headers.put("Accept", "image/webp,image/*,*/*;q=0.8");
        headers.put("Referer", "http://www.mmjpg.com/");
        headers.put("Host", "img.mmjpg.com");
        FrescoLoader.load(mSimpleDraweeView, bean.getPreview(), headers);
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
                ((BaseActivity) mContext).addFragment(JpgFragment.newInstance(bean.getUrl()));
            }
        });
        mTextViewTag.setText(bean.getDate());
        mTextViewDescription.setText(bean.getDescription());
    }
}
