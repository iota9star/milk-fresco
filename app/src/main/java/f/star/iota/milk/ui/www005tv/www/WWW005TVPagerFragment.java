package f.star.iota.milk.ui.www005tv.www;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Url;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class WWW005TVPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("二次元美图");
        titles.add("每周P站本子图");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(WWW005TVFragment.newInstance(Url.WWW_005_TV_ACG));
        fragments.add(WWW005TVFragment.newInstance(Url.WWW_005_TV_P));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_FIXED;
    }
}
