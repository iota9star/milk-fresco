package f.star.iota.milk.ui.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.liuguangqiang.cookie.OnActionClickListener;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import f.star.iota.milk.LockType;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.config.OtherConfig;
import f.star.iota.milk.ui.lock.SetPinLockActivity;
import f.star.iota.milk.ui.theme.ThemeActivity;
import f.star.iota.milk.util.MessageBar;

public class SettingsActivity extends BaseActivity {
    @BindViews({R.id.image_view_fingerprint, R.id.image_view_pin})
    List<ImageView> mImageViews;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @OnClick({R.id.card_view_display_download, R.id.card_view_use_info, R.id.card_view_widget, R.id.card_view_theme, R.id.linear_layout_fingerprint_lock, R.id.linear_layout_pin_lock})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view_use_info:
                startActivity(new Intent(mContext, UseInfoActivity.class));
                break;
            case R.id.card_view_theme:
                startActivity(new Intent(mContext, ThemeActivity.class));
                break;
            case R.id.card_view_widget:
                startActivity(new Intent(mContext, WidgetActivity.class));
                break;
            case R.id.card_view_display_download:
                startActivity(new Intent(mContext, DisplayAndDownloadActivity.class));
                break;
            case R.id.linear_layout_pin_lock:
                if (OtherConfig.isLock(aContext) != LockType.PIN) {
                    startActivity(new Intent(mContext, SetPinLockActivity.class));
                } else {
                    new AlertDialog.Builder(mContext)
                            .setTitle("操作")
                            .setPositiveButton("关闭 PIN", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    OtherConfig.setLock(aContext, LockType.NONE);
                                    OtherConfig.savePin(aContext, "");
                                }
                            })
                            .setNegativeButton("重新设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(mContext, SetPinLockActivity.class));
                                }
                            })
                            .show();
                }
                break;
            case R.id.linear_layout_fingerprint_lock:
                if (OtherConfig.isLock(aContext) == LockType.NONE) {
                    MessageBar.create(mContext, "请先设置至少一种解锁方式");
                    return;
                }
                FingerprintIdentify fingerprintIdentify = new FingerprintIdentify(mContext);
                if (!fingerprintIdentify.isHardwareEnable()) {
                    MessageBar.create(mContext, "您的设备可能不支持指纹解锁");
                    return;
                }
                if (!fingerprintIdentify.isRegisteredFingerprint()) {
                    MessageBar.create(mContext, "您可能还没有设置指纹，是否前往设置", "嗯", new OnActionClickListener() {
                        @Override
                        public void onClick() {
                            startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
                        }
                    });
                    return;
                }
                final boolean isOpen = OtherConfig.isOpenFingerprint(aContext);
                new AlertDialog.Builder(mContext)
                        .setTitle(isOpen ? "关闭指纹识别支持" : "开启指纹识别支持")
                        .setPositiveButton("嗯", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (isOpen) {
                                    OtherConfig.openFingerprint(aContext, false);
                                } else {
                                    OtherConfig.openFingerprint(aContext, true);
                                }
                            }
                        })
                        .show();
        }
    }

    @Override
    protected void init() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setTintToImageViews(mImageViews);
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

    @Override
    protected int getContentViewId() {
        return R.layout.activity_settings;
    }

}
