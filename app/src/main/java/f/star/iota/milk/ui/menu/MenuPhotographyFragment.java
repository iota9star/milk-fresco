package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Net;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.ui.bing.BingFragment;
import f.star.iota.milk.ui.simpledesktops.SimpleDesktopsFragment;

public class MenuPhotographyFragment extends MenuFragment {
    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {
            case Menus.MENU_BING_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BingFragment.newInstance(Net.BING);
                activity.setTitle(Menus.MENU_BING);
                break;
            case Menus.MENU_SIMPLEDESKTOPS_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = SimpleDesktopsFragment.newInstance(Net.SIMPLEDESKTOPS);
                activity.setTitle(Menus.MENU_SIMPLEDESKTOPS);
                break;
        }
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_BING_ID, Menus.MENU_BING,
                Net.BING_BASE, "http://cn.bing.com/s/a/hp_zh_cn.png", null));
        menu.add(new MenuBean(Menus.MENU_SIMPLEDESKTOPS_ID, Menus.MENU_SIMPLEDESKTOPS,
                Net.SIMPLEDESKTOPS + 1, "http://static.simpledesktops.com/uploads/desktops/2015/06/26/Overlap.png.295x184_q100.png", null));
        return menu;
    }
}
