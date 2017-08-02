package f.star.iota.milk.ui.splash;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import f.star.iota.milk.LockType;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.ui.lock.PinLockActivity;
import f.star.iota.milk.ui.main.MainActivity;
import f.star.iota.milk.util.ConfigUtils;
import f.star.iota.milk.util.SnackbarUtils;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_event)
    TextView mTextViewEvent;
    @BindView(R.id.text_view_year)
    TextView mTextViewYear;
    @BindView(R.id.text_view_day)
    TextView mTextViewDay;
    @BindView(R.id.linear_layout_history)
    LinearLayout mLinearLayoutHistoryContainer;
    @BindView(R.id.frame_layout_splash_container)
    FrameLayout mFrameLayoutSplashContainer;
    @BindView(R.id.button_go)
    Button mButtonGo;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private SplashPresenter mPresenter;

    @OnClick({R.id.button_go})
    public void onClick() {
        go();
    }

    private void go() {
        if (ConfigUtils.isLock(aContext) == LockType.PIN) {
            startActivity(new Intent(mContext, PinLockActivity.class));
        } else if (ConfigUtils.isLock(aContext) == LockType.NONE) {
            startActivity(new Intent(mContext, MainActivity.class));
        }
        finish();
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = new SplashPresenter(this);
        mPresenter.getImage();
        mRefreshLayout.autoRefresh();
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        ColorStateList colorStateList = ColorStateList.valueOf(typedValue.data);
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setColor(colorStateList.getDefaultColor());
        progressBarDrawable.setBarWidth(mContext.getResources().getDimensionPixelOffset(R.dimen.v16dp));
        progressBarDrawable.setRadius(mContext.getResources().getDimensionPixelOffset(R.dimen.v64dp));
        GenericDraweeHierarchy hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                .setPlaceholderImage(R.mipmap.app_icon)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE)
                .setProgressBarImage(progressBarDrawable).build();
        mSimpleDraweeView.setHierarchy(hierarchyBuilder);
    }

    private void bindPalette(Palette.Swatch swatch) {
        mLinearLayoutHistoryContainer.setBackgroundColor(swatch.getRgb());
        mTextViewDay.setTextColor(swatch.getTitleTextColor());
        mTextViewYear.setTextColor(swatch.getTitleTextColor());
        mTextViewEvent.setTextColor(swatch.getBodyTextColor());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void getSuccess(HistoryBean bean) {
        mFrameLayoutSplashContainer.removeView(mButtonGo);
        if (bean.getYear().trim().replace(" ", "").length() > 3) {
            mLinearLayoutHistoryContainer.setVisibility(View.VISIBLE);
            mTextViewYear.setText(bean.getYear());
            mTextViewDay.setText(bean.getDay());
            mTextViewEvent.setText(bean.getEvent());
            if (mTextViewEvent.getLineCount() > 1) {
                mTextViewEvent.setText(String.format("\u3000\u3000%s", bean.getEvent()));
            }
        }
        endSplash();
    }

    private void endSplash() {
        ObjectAnimator.ofFloat(mSimpleDraweeView, "scaleX", 1f, 1.6f).setDuration(3000).start();
        ObjectAnimator.ofFloat(mSimpleDraweeView, "scaleY", 1f, 1.6f).setDuration(3000).start();
        mSimpleDraweeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                go();
            }
        }, 3600);
    }

    @Override
    public void getSuccess(String url) {
        ControllerListener<ImageInfo> controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.finishRefresh(true);
                    }
                }, 960);
                mPresenter.getHistory();
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                mRefreshLayout.finishRefresh(false);
                go();
            }
        };

        if (url != null) {
            Uri uri = Uri.parse(url);
            if (uri != null) {
                Postprocessor postprocessor = new BasePostprocessor() {
                    @Override
                    public void process(Bitmap bitmap) {
                        if (bitmap == null) return;
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                List<Palette.Swatch> swatches = palette.getSwatches();
                                for (Palette.Swatch swatch : swatches) {
                                    if (swatch != null) {
                                        bindPalette(swatch);
                                    }
                                }
                            }
                        });
                    }
                };
                ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setPostprocessor(postprocessor)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setOldController(mSimpleDraweeView.getController())
                        .setControllerListener(controllerListener)
                        .setImageRequest(imageRequest)
                        .build();
                mSimpleDraweeView.setController(draweeController);
            }
        }
    }

    @Override
    public void getError(String error) {
        SnackbarUtils.create(mContext, error);
        endSplash();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }
}
