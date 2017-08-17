package f.star.iota.milk.ui.settings;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.OnClick;
import f.star.iota.milk.R;
import f.star.iota.milk.SourceType;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.config.WidgetConfig;

public class WidgetActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.text_view_interval_juzi)
    TextView mTextViewIntervalJuzi;
    @BindView(R.id.text_view_interval_today_in_history)
    TextView mTextViewIntervalTodayInHistory;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.radio_group_source)
    RadioGroup mRadioGroupBannerSource;
    @BindView(R.id.radio_apic)
    RadioButton mRadioButtonApic;
    @BindView(R.id.radio_moeimg)
    RadioButton mRadioButtonMoeimg;
    @BindView(R.id.radio_bing)
    RadioButton mRadioButtonBing;
    @BindView(R.id.radio_gank)
    RadioButton mRadioButtonGank;
    @BindView(R.id.radio_wallhaven)
    RadioButton mRadioButtonWallHaven;
    @BindView(R.id.radio_simple_desktops)
    RadioButton mRadioButtonSimpleDesktops;
    @BindView(R.id.radio_yuriimg)
    RadioButton mRadioButtonYuriimg;

    @BindView(R.id.switch_compat_is_set_wallpaper)
    SwitchCompat mSwitchCompatIsSetWallpaper;
    @BindView(R.id.switch_compat_wallpaper_is_blur)
    SwitchCompat mSwitchCompatWallpaperIsBlur;
    @BindView(R.id.switch_compat_widget_is_blur)
    SwitchCompat mSwitchCompatWidgetIsBlur;
    @BindView(R.id.switch_compat_widget_is_save)
    SwitchCompat mSwitchCompatWidgetIsSave;
    @BindView(R.id.text_view_blur_value)
    TextView mTextViewBlurValue;

    @OnClick({R.id.linear_layout_juzi, R.id.linear_layout_today_in_history, R.id.linear_layout_blur_value})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_juzi:
                final MaterialNumberPicker juzi = getNumberPicker(1);
                new AlertDialog.Builder(this)
                        .setTitle("刷新间隔（分钟）")
                        .setView(juzi)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int value = juzi.getValue();
                                WidgetConfig.saveJuziInterval(aContext, value);
                                bindJuziInterval(value);
                            }
                        })
                        .show();
                break;
            case R.id.linear_layout_today_in_history:
                final MaterialNumberPicker today = getNumberPicker(2);
                new AlertDialog.Builder(this)
                        .setTitle("刷新间隔（分钟）")
                        .setView(today)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int value = today.getValue();
                                WidgetConfig.saveTodayInHistroyInterval(aContext, value);
                                bindTodayInHistoryInterval(value);
                            }
                        })
                        .show();
                break;
            case R.id.linear_layout_blur_value:
                final MaterialNumberPicker blur = getNumberPicker();
                new AlertDialog.Builder(this)
                        .setTitle("模糊度")
                        .setMessage("\u3000\u3000数值越大效果越好，但对手机性能要求越高，请设置适合的自己的数值")
                        .setView(blur)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int value = blur.getValue();
                                WidgetConfig.saveWidgetBlurValue(aContext, value);
                                bindBlurValue(value);
                            }
                        })
                        .show();
                break;
        }
    }

    private void bindBlurValue(int value) {
        mTextViewBlurValue.setText(String.valueOf(value));
    }

    private MaterialNumberPicker getNumberPicker(int type) {
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return new MaterialNumberPicker.Builder(mContext)
                .minValue(1)
                .maxValue(Integer.MAX_VALUE)
                .defaultValue(type == 1 ? WidgetConfig.getJuziInterval(aContext) : WidgetConfig.getTodayInHistroyInterval(aContext))
                .separatorColor(typedValue.data)
                .textColor(typedValue.data)
                .textSize(20)
                .enableFocusability(true)
                .wrapSelectorWheel(true)
                .build();
    }

    private MaterialNumberPicker getNumberPicker() {
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return new MaterialNumberPicker.Builder(mContext)
                .minValue(1)
                .maxValue(192)
                .defaultValue(WidgetConfig.getWidgetBlurValue(mContext))
                .separatorColor(typedValue.data)
                .textColor(typedValue.data)
                .textSize(20)
                .enableFocusability(true)
                .wrapSelectorWheel(true)
                .build();
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
        bindJuziInterval(WidgetConfig.getJuziInterval(aContext));
        bindTodayInHistoryInterval(WidgetConfig.getTodayInHistroyInterval(aContext));
        bindBlurValue(WidgetConfig.getWidgetBlurValue(mContext));
        switch (WidgetConfig.getWidgetBannerSource(mContext)) {
            case SourceType.APIC:
                mRadioButtonApic.setChecked(true);
                break;
            case SourceType.BING:
                mRadioButtonBing.setChecked(true);
                break;
            case SourceType.GANK:
                mRadioButtonGank.setChecked(true);
                break;
            case SourceType.MOEIMG:
                mRadioButtonMoeimg.setChecked(true);
                break;
            case SourceType.WALLHAVEN:
                mRadioButtonWallHaven.setChecked(true);
                break;
            case SourceType.SIMPLEDESKTOPS:
                mRadioButtonSimpleDesktops.setChecked(true);
                break;
            case SourceType.YURIIMG:
                mRadioButtonYuriimg.setChecked(true);
                break;
        }
        mRadioGroupBannerSource.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radio_apic:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.APIC);
                        break;
                    case R.id.radio_moeimg:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.MOEIMG);
                        break;
                    case R.id.radio_bing:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.BING);
                        break;
                    case R.id.radio_gank:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.GANK);
                        break;
                    case R.id.radio_wallhaven:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.WALLHAVEN);
                        break;
                    case R.id.radio_simple_desktops:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.SIMPLEDESKTOPS);
                        break;
                    case R.id.radio_yuriimg:
                        WidgetConfig.saveWidgetBannerSource(mContext, SourceType.YURIIMG);
                        break;
                }
            }
        });
        if (WidgetConfig.getWidgetBannerIsBlur(mContext)) {
            mSwitchCompatWidgetIsBlur.setChecked(true);
        }
        if (WidgetConfig.getWidgetWallpaperIsBlur(mContext)) {
            mSwitchCompatWallpaperIsBlur.setChecked(true);
        }
        if (WidgetConfig.getWidgetWallpaperIsSet(mContext)) {
            mSwitchCompatIsSetWallpaper.setChecked(true);
        }
        if (WidgetConfig.getWidgetBannerIsSave(mContext)) {
            mSwitchCompatWidgetIsSave.setChecked(true);
        }
        mSwitchCompatIsSetWallpaper.setOnCheckedChangeListener(this);
        mSwitchCompatWallpaperIsBlur.setOnCheckedChangeListener(this);
        mSwitchCompatWidgetIsBlur.setOnCheckedChangeListener(this);
        mSwitchCompatWidgetIsSave.setOnCheckedChangeListener(this);
    }

    private void bindTodayInHistoryInterval(int todayInHistroyInterval) {
        mTextViewIntervalTodayInHistory.setText(intervalDeal(todayInHistroyInterval));
    }

    private void bindJuziInterval(int juziInterval) {
        mTextViewIntervalJuzi.setText(intervalDeal(juziInterval));
    }

    private String intervalDeal(int minutes) {
        int hour = 60;
        int day = hour * 24;
        if (minutes / day >= 1) {
            int d = minutes / day;
            int d_rem = minutes % day;
            if (d_rem == 0) return d + "天";
            if (d_rem / hour >= 1) {
                int h = d_rem / hour;
                int h_rem = minutes % hour;
                if (h_rem == 0) {
                    return d + "天" + h + "小时";
                } else {
                    return d + "天" + h + "小时" + h_rem + "分钟";
                }
            } else {
                return d + "天" + d_rem + "分钟";
            }
        }
        if (minutes / hour >= 1) {
            int h = minutes / hour;
            int h_rem = minutes % hour;
            if (h_rem == 0) {
                return h + "小时";
            } else {
                return h + "小时" + h_rem + "分钟";
            }
        }
        return minutes + "分钟";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting_widget;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_compat_is_set_wallpaper:
                WidgetConfig.saveWidgetWallpaperIsSet(mContext, b);
                break;
            case R.id.switch_compat_wallpaper_is_blur:
                WidgetConfig.saveWidgetWallpaperIsBlur(mContext, b);
                break;
            case R.id.switch_compat_widget_is_blur:
                WidgetConfig.saveWidgetBannerIsBlur(mContext, b);
                break;
            case R.id.switch_compat_widget_is_save:
                WidgetConfig.saveWidgetBannerIsSave(mContext, b);
                break;
        }
    }
}
