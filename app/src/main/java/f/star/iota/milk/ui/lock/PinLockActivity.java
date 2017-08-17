package f.star.iota.milk.ui.lock;

import android.content.Intent;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.config.OtherConfig;
import f.star.iota.milk.ui.main.MainActivity;
import f.star.iota.milk.util.SnackbarUtils;

public class PinLockActivity extends BaseActivity {

    @BindView(R.id.pin_lock_view)
    PinLockView mPinLockView;
    @BindView(R.id.indicator_dots)
    IndicatorDots mIndicatorDots;

    private int mErrorTimes = 0;

    @Override
    protected void init() {
        initView();
        initEvent();
        initFingerprint();
    }

    private void initFingerprint() {
        if (!OtherConfig.isOpenFingerprint(aContext)) return;
        FingerprintIdentify fingerprintIdentify = new FingerprintIdentify(mContext);
        if (!fingerprintIdentify.isFingerprintEnable()) return;
        fingerprintIdentify.startIdentify(3, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                verifySuccess();
            }

            @Override
            public void onNotMatch(int i) {
                SnackbarUtils.create(mContext, "指纹识别失败，你还有" + i + "次机会");
            }

            @Override
            public void onFailed(boolean b) {

            }

            @Override
            public void onStartFailedByDeviceLocked() {
                finish();
            }
        });

    }

    private void verifySuccess() {
        mPinLockView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        }, 1600);
        SnackbarUtils.create(mContext, "验证成功，即将前往主页面");
    }

    private void initEvent() {
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                if (OtherConfig.getPin(aContext).equals(pin)) {
                    mErrorTimes = 0;
                    verifySuccess();
                } else {
                    mPinLockView.resetPinLockView();
                    mErrorTimes++;
                    if (mErrorTimes == 3) {
                        mErrorTimes = 0;
                        finish();
                    }
                    SnackbarUtils.create(mContext, "解锁失败，你还有" + (3 - mErrorTimes) + "次输入机会");
                }
            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {

            }
        });
    }

    private void initView() {
        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
        mPinLockView.attachIndicatorDots(mIndicatorDots);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pin_lock;
    }
}
