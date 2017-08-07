package f.star.iota.milk.ui.youwu.you;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class YouWuPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("国内美女");
        titles.add("港台美女");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(YouWuFragment.newInstance(Net.YOUWU_GUONEI));
        fragments.add(YouWuFragment.newInstance(Net.YOUWU_GANGTAI));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_FIXED;
    }
}
