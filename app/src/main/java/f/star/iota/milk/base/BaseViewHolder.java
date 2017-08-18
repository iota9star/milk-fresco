package f.star.iota.milk.base;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import f.star.iota.milk.R;
import f.star.iota.milk.fresco.MyOkHttpNetworkFetcher;
import f.star.iota.milk.util.FileUtils;
import f.star.iota.milk.util.SnackbarUtils;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseViewHolder<B extends BaseBean> extends RecyclerView.ViewHolder {
    protected final Context mContext;
    protected final Context aContext;

    protected BaseViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        aContext = mContext.getApplicationContext();
        ButterKnife.bind(this, itemView);
    }

    public void bindView(List<B> beans) {
    }

    public void bindView(B bean) {
    }

    protected List<PCBean> getProcessingCompletedBeans(List<B> beans, HashMap<String, String> headers) {
        return null;
    }

    protected List<PCBean> getProcessingCompletedBeans(List<B> beans) {
        return getProcessingCompletedBeans(beans, null);
    }

    protected void open(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        mContext.startActivity(intent);
    }

    protected void copy(String url) {
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("image_url", url));
    }

    protected void download(String url, String preview, String folder, Map<String, String> headers) {
        if (OkDownload.getInstance().hasTask(url)) {
            SnackbarUtils.create(mContext, "任务已存在：" + url);
        } else {
            buildTaskAndDownload(url, preview, folder, headers);
            SnackbarUtils.create(mContext, "开始下载：" + url);
        }
    }

    private void buildTaskAndDownload(String url, String preview, String folder, Map<String, String> headers) {
        GetRequest<File> request = OkGo.get(url);
        if (headers != null) {
            for (Map.Entry<String, String> val : headers.entrySet()) {
                request.headers(val.getKey(), val.getValue());
            }
        }
        OkDownload.request(url, request)
                .extra1(preview)
                .extra2(folder)
                .folder(FileUtils.getDownloadDir() + folder)
                .save()
                .start();
    }

    protected void batchDownload(final List<PCBean> images) {
        new AlertDialog.Builder(mContext)
                .setTitle("下载当前已加载的图片")
                .setNeutralButton("嗯", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dealBatchDownload(images);
                    }
                })
                .setNegativeButton("按错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void dealBatchDownload(final List<PCBean> images) {
        Observable.interval(0, 640, TimeUnit.MILLISECONDS)
                .take(images.size())
                .map(new Function<Long, PCBean>() {
                    @Override
                    public PCBean apply(@NonNull Long aLong) throws Exception {
                        return images.get(aLong.intValue());
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .filter(new Predicate<PCBean>() {
                    @Override
                    public boolean test(@NonNull PCBean bean) throws Exception {
                        return !OkDownload.getInstance().hasTask(bean.getUrl());
                    }
                })
                .subscribe(new Consumer<PCBean>() {
                    @Override
                    public void accept(PCBean bean) throws Exception {
                        buildTaskAndDownload(bean.getUrl(), bean.getPreview(), bean.getFolder(), bean.getHeaders());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
        SnackbarUtils.create(mContext, "如有错误或是任务已存在，将自动跳过");
    }

    protected void show(final List<PCBean> images) {
        @SuppressLint("InflateParams") final View overlayView = LayoutInflater.from(mContext).inflate(R.layout.view_overlay_watch, null);
        final Button info = ButterKnife.findById(overlayView, R.id.button_info);
        final Button download = ButterKnife.findById(overlayView, R.id.button_download);
        final Button index = ButterKnife.findById(overlayView, R.id.button_index);
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        ColorStateList colorStateList = ColorStateList.valueOf(typedValue.data);
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setColor(colorStateList.getDefaultColor());
        progressBarDrawable.setBarWidth(mContext.getResources().getDimensionPixelOffset(R.dimen.v16dp));
        progressBarDrawable.setRadius(mContext.getResources().getDimensionPixelOffset(R.dimen.v64dp));
        new ImageViewer.Builder<>(mContext, images)
                .setFormatter(new ImageViewer.Formatter<PCBean>() {
                    @Override
                    public String format(PCBean customImage) {
                        String url = customImage.getUrl();
                        if (url != null) {
                            Uri uri = Uri.parse(url);
                            if (uri != null) {
                                MyOkHttpNetworkFetcher.Headers.put(uri, customImage.getHeaders());
                            }
                        }
                        return url;
                    }
                })
                .setStartPosition(getAdapterPosition())
                .setImageChangeListener(new ImageViewer.OnImageChangeListener() {
                    @Override
                    public void onImageChange(int position) {
                        final PCBean image = images.get(position);
                        index.setText((position + 1) + "/" + images.size());
                        info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                @SuppressLint("InflateParams") View dialog = LayoutInflater.from(mContext).inflate(R.layout.dialog_image_info, null);
                                ((TextView) ButterKnife.findById(dialog, R.id.text_view_description)).setText(image.getDescription());
                                new AlertDialog.Builder(mContext)
                                        .setView(dialog)
                                        .show();
                            }
                        });
                        download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                boolean hasTask = OkDownload.getInstance().hasTask(image.getUrl());
                                final GetRequest<File> request = OkGo.get(image.getUrl());
                                Map<String, String> headers = image.getHeaders();
                                if (headers != null) {
                                    for (Map.Entry<String, String> val : headers.entrySet()) {
                                        request.headers(val.getKey(), val.getValue());
                                    }
                                }
                                if (hasTask) {
                                    new AlertDialog.Builder(mContext)
                                            .setMessage("已在下载列表，是否重新下载？")
                                            .setNegativeButton("嗯", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    OkDownload.request(image.getUrl(), request)
                                                            .extra1(image.getPreview())
                                                            .extra2(image.getFolder())
                                                            .folder(FileUtils.getDownloadDir() + image.getFolder())
                                                            .save()
                                                            .restart();
                                                }
                                            })
                                            .setNeutralButton("还是算了", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            })
                                            .show();
                                } else {
                                    OkDownload.request(image.getUrl(), request)
                                            .extra1(image.getPreview())
                                            .extra2(image.getFolder())
                                            .folder(FileUtils.getDownloadDir() + image.getFolder())
                                            .save()
                                            .start();
                                    Toast.makeText(mContext, "开始下载：" + image.getUrl(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                })
                .setCustomDraweeHierarchyBuilder(GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                        .setPlaceholderImage(R.drawable.ic_placeholder)
                        .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER)
                        .setFailureImage(R.drawable.ic_placeholder)
                        .setFailureImageScaleType(ScalingUtils.ScaleType.CENTER)
                        .setProgressBarImage(progressBarDrawable))
                .setOverlayView(overlayView)
                .allowZooming(true)
                .allowSwipeToDismiss(true)
                .show();
    }
}
