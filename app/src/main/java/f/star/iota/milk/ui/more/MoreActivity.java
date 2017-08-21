package f.star.iota.milk.ui.more;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import butterknife.BindView;
import butterknife.OnClick;
import f.star.iota.milk.Net;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.config.OtherConfig;
import f.star.iota.milk.util.MessageBar;
import moe.feng.alipay.zerosdk.AlipayZeroSdk;

public class MoreActivity extends BaseActivity {
    private final long[] mHints = new long[5];
    @BindView(R.id.switch_compat_r)
    SwitchCompat mSwitchCompatR;

    @OnClick({R.id.linear_layout_donation_alipay, R.id.linear_layout_donation_qq, R.id.linear_layout_donation_wechat, R.id.text_view_hitokoto_loli, R.id.text_view_hitokoto_ad, R.id.text_view_yiju, R.id.linear_layout_grade, R.id.text_view_juzi, R.id.linear_layout_r})
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (view.getId()) {
            case R.id.linear_layout_donation_qq:
                intent.setData(Uri.parse(mContext.getString(R.string.qq_pay_code)));
                break;
            case R.id.linear_layout_donation_wechat:
                intent.setData(Uri.parse(mContext.getString(R.string.wechat_pay_code)));
                break;
            case R.id.text_view_juzi:
                intent.setData(Uri.parse(Net.HITOKOTO_BILIBILIJJ_BASE));
                break;
            case R.id.text_view_yiju:
                intent.setData(Uri.parse(Net.YIJU_BASE));
                break;
            case R.id.text_view_hitokoto_ad:
                intent.setData(Uri.parse(Net.HITOKOTO_IMJAD_BASE));
                break;
            case R.id.text_view_hitokoto_loli:
                intent.setData(Uri.parse(Net.HITOKOTO_LOLI_BASE));
                break;
            case R.id.linear_layout_grade:
                intent.setData(Uri.parse("market://details?id=" + mContext.getPackageName()));
                break;
            case R.id.linear_layout_r:
                System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
                mHints[mHints.length - 1] = SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - mHints[0] <= 800) {
                    MessageBar.create(mContext, ((int) (Math.random() * 2)) == 1 ? "令人窒息的操作" : "还有这种操作？");
                    if (mSwitchCompatR.getVisibility() == View.GONE) {
                        mSwitchCompatR.setVisibility(View.VISIBLE);
                    }
                }
                return;
            case R.id.linear_layout_donation_alipay:
                if (AlipayZeroSdk.hasInstalledAlipayClient(mContext)) {
                    AlipayZeroSdk.startAlipayClient(this, getResources().getString(R.string.alipay_code));
                } else {
                    MessageBar.create(mContext, "你可能没有安装支付宝");
                }
                return;
        }
        if (intent.getData() != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void init() {
        setTitle(null);
        if (OtherConfig.getR(aContext)) {
            mSwitchCompatR.setChecked(true);
        } else {
            mSwitchCompatR.setChecked(false);
        }
        mSwitchCompatR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OtherConfig.saveR(aContext, true);
                    MessageBar.create(mContext, "好好学习，天天向上");
                } else {
                    OtherConfig.saveR(aContext, false);
                    MessageBar.create(mContext, "飙车了");
                }
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_more;
    }

}
