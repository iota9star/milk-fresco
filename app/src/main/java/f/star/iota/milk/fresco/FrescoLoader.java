package f.star.iota.milk.fresco;


import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.HashMap;

import f.star.iota.milk.R;


public class FrescoLoader {

    public static void load(SimpleDraweeView draweeView, String path) {
        load(draweeView, path, null);
    }

    public static void load(SimpleDraweeView draweeView, String path, HashMap<String, String> headers) {
        if (TextUtils.isEmpty(path) || draweeView == null) return;
        Uri uri = Uri.parse(path);
        if (uri == null) return;
        if (headers != null) {
            MyOkHttpNetworkFetcher.Headers.put(uri, headers);
        }
        String extension = path.contains(".") ? path.substring(path.lastIndexOf(".", path.length())) : "";
        Context context = draweeView.getContext();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        ColorStateList colorStateList = ColorStateList.valueOf(typedValue.data);
        ProgressBarDrawable progress = new ProgressBarDrawable();
        progress.setColor(colorStateList.getDefaultColor());
        progress.setBarWidth(context.getResources().getDimensionPixelOffset(R.dimen.v8dp));
        progress.setRadius(context.getResources().getDimensionPixelOffset(R.dimen.v64dp));
        GenericDraweeHierarchy hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
                .setFadeDuration(300)
                .setPlaceholderImage(R.drawable.ic_placeholder)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER)
                .setFailureImage(R.drawable.ic_placeholder)
                .setFailureImageScaleType(ScalingUtils.ScaleType.CENTER)
                .setProgressBarImage(progress)
                .setRoundingParams(RoundingParams.fromCornersRadius(context.getResources().getDimension(R.dimen.v2dp)))
                .build();
        draweeView.setHierarchy(hierarchyBuilder);
        ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        if (extension.contains("jpg") || extension.contains("jpeg")) {
            requestBuilder.setProgressiveRenderingEnabled(true);
        } else {
            requestBuilder.setProgressiveRenderingEnabled(false);
        }
        int width = draweeView.getWidth();
        int height = draweeView.getHeight();
        if (width > 0 && height > 0) {
            requestBuilder.setResizeOptions(new ResizeOptions(width, height));
        }
        ImageRequest request = requestBuilder.build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new ResizeViewControllerListener(draweeView, width, height))
                .build();
        draweeView.setController(controller);
    }

    public static void loadForDownload(SimpleDraweeView draweeView, String path) {
        if (TextUtils.isEmpty(path) || draweeView == null) return;
        Uri uri = Uri.parse(path);
        if (uri == null) return;
        ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        int width = draweeView.getWidth();
        int height = draweeView.getHeight();
        if (width > 0 && height > 0) {
            requestBuilder.setResizeOptions(new ResizeOptions(width, height));
        }
        ImageRequest request = requestBuilder.build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(controller);
    }
}