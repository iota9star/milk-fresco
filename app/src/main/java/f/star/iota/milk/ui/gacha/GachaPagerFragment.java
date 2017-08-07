package f.star.iota.milk.ui.gacha;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class GachaPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("日榜");
        titles.add("周榜");
        titles.add("月榜");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GachaFragment.newInstance(Net.GACHA_DAY));
        fragments.add(GachaFragment.newInstance(Net.GACHA_WEEK));
        fragments.add(GachaFragment.newInstance(Net.GACHA_MONTH));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_FIXED;
    }
}
