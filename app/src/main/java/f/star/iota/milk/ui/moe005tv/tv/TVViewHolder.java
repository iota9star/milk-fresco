package f.star.iota.milk.ui.moe005tv.tv;


import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.ShowImageBean;

public class TVViewHolder extends BaseViewHolder<TVBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.card_view)
    CardView mCardView;

    public TVViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<TVBean> beans) {
        final TVBean bean = beans.get(getAdapterPosition());
        if (bean.getUrl() != null) {
            Uri uri = Uri.parse(bean.getUrl());
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
                                download(bean.getUrl(), bean.getUrl(),
                                        Menus.MENU_MOE005TV, null);
                            }
                        })
                        .show();
                return true;
            }
        });
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShowImageBean> imgs = new ArrayList<>();
                for (TVBean bean : beans) {
                    imgs.add(new ShowImageBean(bean.getUrl(), bean.getUrl(), Menus.MENU_MOE005TV,
                            "下载地址：" + bean.getUrl()));
                }
                show(imgs);
            }
        });
    }
}
