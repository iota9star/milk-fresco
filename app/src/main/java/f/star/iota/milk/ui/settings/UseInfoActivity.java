package f.star.iota.milk.ui.settings;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.irozon.sneaker.Sneaker;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.util.ConfigUtils;
import f.star.iota.milk.util.FileUtils;
import f.star.iota.milk.util.SnackbarUtils;

public class UseInfoActivity extends BaseActivity {
    @BindView(R.id.text_view_rx)
    TextView mTextViewRx;
    @BindView(R.id.text_view_tx)
    TextView mTextViewTx;
    @BindView(R.id.text_view_cache_size)
    TextView mTextViewCacheSize;
    @BindView(R.id.text_view_open_count)
    TextView mTextViewOpenCount;
    @BindViews({R.id.image_view_delete_cache, R.id.image_view_download, R.id.image_view_upload})
    List<ImageView> mImageViews;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @OnClick({R.id.card_view_open_count, R.id.linear_layout_cache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_cache:
                clearCache();
                break;
            case R.id.card_view_open_count:
                SnackbarUtils.create(mContext, "清空历史打开次数", "嗯", new Sneaker.OnSneakerClickListener() {
                    @Override
                    public void onSneakerClick(View view) {
                        ConfigUtils.saveOpenCount(aContext, 0);
                        bindOpenCount();
                    }
                });
                break;
        }
    }

    private void clearCache() {
        SnackbarUtils.create(mContext, "清空缓存", "嗯", new Sneaker.OnSneakerClickListener() {
            @Override
            public void onSneakerClick(View view) {
                File cacheDir = FileUtils.getDiskCacheDir(mContext);
                if (cacheDir != null && cacheDir.exists() && cacheDir.isDirectory()) {
                    if (!FileUtils.deleteDirsFiles(cacheDir)) {
                        SnackbarUtils.create(mContext, "已清空");
                    } else {
                        SnackbarUtils.create(mContext, "可能部分缓存文件未被清除，请稍后再试");
                    }
                }
                bindCacheSize();
            }
        });
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bindTrafficInfo();
        bindCacheSize();
        bindOpenCount();
        setTintToImageViews(mImageViews);
    }

    private void bindOpenCount() {
        mTextViewOpenCount.setText(String.format("%s 次", String.valueOf(ConfigUtils.getOpenCount(aContext))));
    }

    private void bindCacheSize() {
        File cacheDir = FileUtils.getDiskCacheDir(mContext);
        if (cacheDir != null && cacheDir.exists() && cacheDir.isDirectory()) {
            mTextViewCacheSize.setText(FileUtils.getFileSize(FileUtils.getFolderSize(cacheDir)));
        }
    }

    private void setTintToImageViews(List<ImageView> views) {
        for (ImageView view : views) {
            setTintToImageView(view);
        }
    }

    private void setTintToImageView(ImageView view) {
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        ColorStateList colorStateList = ColorStateList.valueOf(typedValue.data);
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }

    private void bindTrafficInfo() {
        try {
            ApplicationInfo applicationInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            int uid = applicationInfo.uid;
            long uidRxBytes = TrafficStats.getUidRxBytes(uid);
            long uidTxBytes = TrafficStats.getUidTxBytes(uid);
            mTextViewRx.setText(FileUtils.getFileSize(uidRxBytes));
            mTextViewTx.setText(FileUtils.getFileSize(uidTxBytes));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting_use_info;
    }

}
