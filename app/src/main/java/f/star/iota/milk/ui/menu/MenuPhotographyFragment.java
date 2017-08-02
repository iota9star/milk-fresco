package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Url;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.ui.bing.BingFragment;

public class MenuPhotographyFragment extends MenuFragment {
    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {
            case Menus.MENU_BING_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BingFragment.newInstance(Url.BING);
                activity.setTitle(Menus.MENU_BING);
                break;
        }
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_BING_ID, Menus.MENU_BING,
                Url.BING_BASE, "http://cn.bing.com/s/a/hp_zh_cn.png", null));
        return menu;
    }
}
