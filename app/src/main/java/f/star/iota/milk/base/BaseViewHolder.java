package f.star.iota.milk.base;

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
import android.widget.Toast;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import f.star.iota.milk.R;
import f.star.iota.milk.util.FileUtils;
import f.star.iota.milk.util.SnackbarUtils;


public abstract class BaseViewHolder<B extends BaseBean> extends RecyclerView.ViewHolder {
    protected Context mContext;
    protected Context aContext;

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
            SnackbarUtils.create(mContext, "任务已存在");
        } else {
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
            SnackbarUtils.create(mContext, "开始下载：" + url);
        }
    }

    protected void show(final List<ShowImageBean> images) {
        final View overlayView = LayoutInflater.from(mContext).inflate(R.layout.view_overlay_watch, null);
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
                .setFormatter(new ImageViewer.Formatter<ShowImageBean>() {
                    @Override
                    public String format(ShowImageBean customImage) {
                        return customImage.getUrl();
                    }
                })
                .setStartPosition(getAdapterPosition())
                .setImageChangeListener(new ImageViewer.OnImageChangeListener() {
                    @Override
                    public void onImageChange(int position) {
                        final ShowImageBean image = images.get(position);
                        index.setText((position + 1) + "/" + images.size());
                        info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new AlertDialog.Builder(mContext)
                                        .setMessage(image.getDescription())
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
                                    Toast.makeText(mContext, "开始下载：" + image.getUrl(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                })
                .setCustomDraweeHierarchyBuilder(GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                        .setFailureImage(R.drawable.ic_report_problem_white_48dp)
                        .setFailureImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE)
                        .setProgressBarImage(progressBarDrawable))
                .setOverlayView(overlayView)
                .show();
    }
}
