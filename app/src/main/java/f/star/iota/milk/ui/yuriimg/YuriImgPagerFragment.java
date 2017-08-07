package f.star.iota.milk.ui.yuriimg;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class YuriImgPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("最新");
        titles.add("随机");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(YuriImgFragment.newInstance(Net.YURIIMG_NEW));
        fragments.add(YuriImgFragment.newInstance(Net.YURIIMG_RANDOM));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_FIXED;
    }
}
