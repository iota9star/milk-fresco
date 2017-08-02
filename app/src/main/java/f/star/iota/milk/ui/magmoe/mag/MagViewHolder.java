package f.star.iota.milk.ui.magmoe.mag;


import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.ui.magmoe.moe.MoeFragment;

public class MagViewHolder extends BaseViewHolder<MagBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_title)
    TextView mTextViewTitle;
    @BindView(R.id.text_view_subtitle)
    TextView mTextViewSubtitle;
    @BindView(R.id.card_view)
    CardView mCardView;

    public MagViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final MagBean bean) {
        if (bean.getUrl() != null) {
            Uri uri = Uri.parse(bean.getPreview());
            if (uri != null) {
                TypedValue typedValue = new TypedValue();
                mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
                ColorStateList colorStateList = ColorStateList.valueOf(typedValue.data);
                ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
                progressBarDrawable.setColor(colorStateList.getDefaultColor());
                progressBarDrawable.setBarWidth(mContext.getResources().getDimensionPixelOffset(R.dimen.v8dp));
                progressBarDrawable.setRadius(mContext.getResources().getDimensionPixelOffset(R.dimen.v64dp));
                GenericDraweeHierarchy hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources()).setFadeDuration(300).setFailureImage(R.mipmap.app_icon).setFailureImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setProgressBarImage(progressBarDrawable).setRoundingParams(RoundingParams.fromCornersRadius(mContext.getResources().getDimension(R.dimen.v2dp))).build();
                mSimpleDraweeView.setHierarchy(hierarchyBuilder);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build();
                DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request).setOldController(mSimpleDraweeView.getController()).build();
                mSimpleDraweeView.setController(controller);
            }
        }
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
                ((BaseActivity) mContext).addFragment(MoeFragment.newInstance(bean.getUrl()));
            }
        });
        mTextViewTitle.setText(bean.getTitle());
        mTextViewSubtitle.setText(bean.getSubtitle());
    }
}
