package f.star.iota.milk.ui.download;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;

import java.io.File;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import f.star.iota.milk.R;
import f.star.iota.milk.fresco.FrescoLoader;
import f.star.iota.milk.util.MessageBar;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_FINISH = 1;
    public static final int TYPE_ING = 2;
    private final NumberFormat mNumberFormat;
    private OnOpenListener onOpenListener;
    private OnSetBannerListener onSetBannerListener;
    private List<DownloadTask> mTasks;
    private int mType;

    public DownloadAdapter() {
        mNumberFormat = NumberFormat.getPercentInstance();
        mNumberFormat.setMinimumFractionDigits(2);
    }

    public void setOnOpenListener(OnOpenListener onOpenListener) {
        this.onOpenListener = onOpenListener;
    }

    public void setOnSetBannerListener(OnSetBannerListener onSetBannerListener) {
        this.onSetBannerListener = onSetBannerListener;
    }

    public void updateData(int type) {
        this.mType = type;
        if (type == TYPE_ALL) {
            mTasks = OkDownload.restore(DownloadManager.getInstance().getAll());
        }
        if (type == TYPE_FINISH) {
            mTasks = OkDownload.restore(DownloadManager.getInstance().getFinished());
        }
        if (type == TYPE_ING) {
            mTasks = OkDownload.restore(DownloadManager.getInstance().getDownloading());
        }
        Collections.reverse(mTasks);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_download, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DownloadTask task = mTasks.get(position);
        String holderTag = task.progress.tag;
        task.register(new ListDownloadListener(holderTag, holder));
        holder.setTag(holderTag);
        holder.setTask(task);
        holder.bind();
    }

    public void unRegister() {
        Map<String, DownloadTask> taskMap = OkDownload.getInstance().getTaskMap();
        for (DownloadTask task : taskMap.values()) {
            task.unRegister(task.progress.tag);
        }
    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }


    private void loadFile(File file, ViewHolder holder) {
        if (file != null && file.exists()) {
            FrescoLoader.loadForDownload(holder.mSimpleDraweeView, "file://" + file.getAbsolutePath());
        } else {
            MessageBar.create(holder.mContext, "文件不见了？");
        }
    }

    public interface OnOpenListener {
        void open(String filePath);
    }

    public interface OnSetBannerListener {
        void set(String filePath);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.simple_drawee_view_image)
        SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.text_view_percent)
        TextView mTextViewPercent;
        @BindView(R.id.text_view_index)
        TextView mTextViewIndex;
        @BindView(R.id.text_view_source)
        TextView mTextViewSource;
        @BindView(R.id.text_view_progress)
        TextView mTextViewProgress;
        @BindView(R.id.text_view_status)
        TextView mTextViewStatus;
        @BindView(R.id.image_button_action)
        ImageButton mButtonAction;
        @BindView(R.id.image_button_more)
        ImageButton mImageButtonMore;
        private DownloadTask mTask;
        private String mTag;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        void setTask(DownloadTask task) {
            this.mTask = task;
        }

        public void bind() {
            Progress progress = mTask.progress;
            String preview = (String) progress.extra1;
            String folder = (String) progress.extra2;
            FrescoLoader.loadForDownload(mSimpleDraweeView, preview);
            mTextViewIndex.setText(String.valueOf(mTasks.size() - getAdapterPosition()));
            mImageButtonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopUpWindow();
                }
            });
            mTextViewSource.setText(folder);
            refresh(progress);
        }

        void refresh(Progress progress) {
            String currentSize = Formatter.formatFileSize(mContext, progress.currentSize);
            String totalSize = Formatter.formatFileSize(mContext, progress.totalSize);
            mTextViewProgress.setText(currentSize + "/" + totalSize);
            switch (progress.status) {
                case Progress.NONE:
                    mTextViewStatus.setText("停止");
                    mButtonAction.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    break;
                case Progress.PAUSE:
                    mTextViewStatus.setText("暂停中");
                    mButtonAction.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    break;
                case Progress.ERROR:
                    mTextViewStatus.setText("下载出错");
                    mButtonAction.setImageResource(R.drawable.ic_replay_white_24dp);
                    break;
                case Progress.WAITING:
                    mTextViewStatus.setText("等待中");
                    mButtonAction.setImageResource(R.drawable.ic_hourglass_empty_white_24dp);
                    break;
                case Progress.FINISH:
                    mTextViewStatus.setText("下载完成");
                    mButtonAction.setImageResource(R.drawable.ic_open_in_new_white_24dp);
                    String path = progress.filePath;
                    FrescoLoader.loadForDownload(mSimpleDraweeView, "file://" + path);
                    break;
                case Progress.LOADING:
                    String speed = Formatter.formatFileSize(mContext, progress.speed);
                    mTextViewStatus.setText(String.format("%s/s", speed));
                    mButtonAction.setImageResource(R.drawable.ic_pause_white_24dp);
                    break;
            }
            mTextViewPercent.setText(mNumberFormat.format(progress.fraction));
        }

        private void showPopUpWindow() {
            final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
            listPopupWindow.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,
                    new String[]{"删除", "设为BANNER", "复制链接", "浏览器打开"}));
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    Progress progress = mTask.progress;
                    if (pos == 0) {
                        delete();
                    } else if (pos == 1) {
                        if (onSetBannerListener != null) {
                            onSetBannerListener.set(progress.filePath);
                        }
                    } else if (pos == 2) {
                        copyUrl(progress.url);
                    } else if (pos == 3) {
                        openUrl(progress.url);
                    }
                    listPopupWindow.dismiss();
                }
            });
            listPopupWindow.setWidth((int) mContext.getResources().getDimension(R.dimen.v196dp));
            listPopupWindow.setAnchorView(mImageButtonMore);
            listPopupWindow.setModal(true);
            listPopupWindow.show();
        }

        private void copyUrl(String url) {
            ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("image_url", url));
        }

        private void delete() {
            new AlertDialog.Builder(mContext)
                    .setIcon(R.mipmap.app_icon)
                    .setTitle("真的要删除吗")
                    .setNeutralButton("移出列表", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mTask.remove();
                            updateData(mType);
                        }
                    })
                    .setNegativeButton("移出并且删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mTask.remove(true);
                            updateData(mType);
                        }
                    })
                    .setPositiveButton("算了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        }

        private void openUrl(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            mContext.startActivity(intent);
        }

        @OnClick(R.id.image_button_action)
        public void start() {
            Progress progress = mTask.progress;
            switch (progress.status) {
                case Progress.PAUSE:
                case Progress.NONE:
                case Progress.ERROR:
                    mTask.start();
                    break;
                case Progress.LOADING:
                    mTask.pause();
                    break;
                case Progress.FINISH:
                    String filePath = progress.filePath;
                    if (filePath == null) break;
                    if (onOpenListener != null) {
                        onOpenListener.open(filePath);
                    }
                    FrescoLoader.loadForDownload(mSimpleDraweeView, "file://" + filePath);
                    break;
            }
            refresh(progress);
        }

        @OnClick(R.id.simple_drawee_view_image)
        public void onClick() {
            if (onSetBannerListener != null) {
                onSetBannerListener.set(mTask.progress.filePath);
            }
        }

        String getTag() {
            return mTag;
        }

        void setTag(String tag) {
            this.mTag = tag;
        }
    }

    private class ListDownloadListener extends DownloadListener {

        private final ViewHolder holder;

        ListDownloadListener(Object tag, ViewHolder holder) {
            super(tag);
            this.holder = holder;
        }

        @Override
        public void onStart(Progress progress) {
        }

        @Override
        public void onProgress(Progress progress) {
            if (tag == holder.getTag()) {
                holder.refresh(progress);
            }
        }

        @Override
        public void onError(Progress progress) {
        }

        @Override
        public void onFinish(File file, Progress progress) {
            if (tag == holder.getTag()) {
                loadFile(file, holder);
            }
            updateData(mType);
        }

        @Override
        public void onRemove(Progress progress) {
        }
    }
}
