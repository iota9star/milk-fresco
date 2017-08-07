package f.star.iota.milk.ui.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.OnClick;
import f.star.iota.milk.R;
import f.star.iota.milk.SourceType;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.util.ConfigUtils;

public class WidgetActivity extends BaseActivity {
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

    @OnClick({R.id.linear_layout_juzi, R.id.linear_layout_today_in_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_juzi:
                final MaterialNumberPicker juzi = getNumberPicker(1);
                new AlertDialog.Builder(this)
                        .setTitle("刷新间隔（分钟）")
                        .setView(juzi)
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int value = juzi.getValue();
                                ConfigUtils.saveJuziInterval(aContext, value);
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
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int value = today.getValue();
                                ConfigUtils.saveTodayInHistroyInterval(aContext, value);
                                bindTodayInHistoryInterval(value);
                            }
                        })
                        .show();
                break;
        }
    }

    private MaterialNumberPicker getNumberPicker(int type) {
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return new MaterialNumberPicker.Builder(mContext)
                .minValue(1)
                .maxValue(Integer.MAX_VALUE)
                .defaultValue(type == 1 ? ConfigUtils.getJuziInterval(aContext) : ConfigUtils.getTodayInHistroyInterval(aContext))
                .separatorColor(typedValue.data)
                .textColor(typedValue.data)
                .textSize(20)
                .enableFocusability(true)
                .wrapSelectorWheel(true)
                .build();
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
        bindJuziInterval(ConfigUtils.getJuziInterval(aContext));
        bindTodayInHistoryInterval(ConfigUtils.getTodayInHistroyInterval(aContext));
        switch (ConfigUtils.getWidgetBannerSource(mContext)) {
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
        }
        mRadioGroupBannerSource.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radio_apic:
                        ConfigUtils.saveWidgetBannerSource(mContext, SourceType.APIC);
                        break;
                    case R.id.radio_moeimg:
                        ConfigUtils.saveWidgetBannerSource(mContext, SourceType.MOEIMG);
                        break;
                    case R.id.radio_bing:
                        ConfigUtils.saveWidgetBannerSource(mContext, SourceType.BING);
                        break;
                    case R.id.radio_gank:
                        ConfigUtils.saveWidgetBannerSource(mContext, SourceType.GANK);
                        break;
                    case R.id.radio_wallhaven:
                        ConfigUtils.saveWidgetBannerSource(mContext, SourceType.WALLHAVEN);
                        break;
                }
            }
        });
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
        Log.i("value", "intervalDeal: " + minutes / day + ":" + minutes / hour + ":" + minutes);
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

}
