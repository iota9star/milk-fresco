package f.star.iota.milk.ui.miaowu.miao;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class MiaoWuPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("丝袜");
        titles.add("性感");
        titles.add("内衣");
        titles.add("大胸");
        titles.add("巨乳");
        titles.add("日本");
        titles.add("欧美");
        titles.add("清纯");
        titles.add("爆乳");
        titles.add("翘臀");
        titles.add("裸体");
        titles.add("长腿");
        titles.add("比基尼");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_SIWA));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_XGMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_NYMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_DXMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_JRMV));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_RBMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_OMMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_QCMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_BRMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_QTMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_LTMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_CTMN));
        fragments.add(MiaoWuFragment.newInstance(Net.MIAOWU_BJNMN));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_SCROLLABLE;
    }
}
