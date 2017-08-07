package f.star.iota.milk.fresco;


import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

public class ResizeViewControllerListener extends BaseControllerListener<ImageInfo> {

    private final SimpleDraweeView draweeView;
    private int width;
    private int height;

    public ResizeViewControllerListener(SimpleDraweeView draweeView, int width, int height) {
        this.draweeView = draweeView;
        this.width = width;
        this.height = height;
    }

    @Override
    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
        if (imageInfo == null || draweeView == null) {
            return;
        }
        ViewGroup.LayoutParams lp = draweeView.getLayoutParams();
        int parentWidth = ((ViewGroup) draweeView.getParent()).getWidth();
        if (parentWidth == width && lp.width == width && lp.height == height) {
            return;
        }
        if (parentWidth == 0) return;
        int rawWidth = imageInfo.getWidth();
        int rawHeight = imageInfo.getHeight();
        lp.width = parentWidth;
        lp.height = (int) (rawHeight / (float) rawWidth * lp.width);
        draweeView.requestLayout();
    }
}
