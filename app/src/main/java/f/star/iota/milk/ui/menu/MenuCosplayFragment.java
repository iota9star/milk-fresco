package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Url;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.ui.bcy.ranking.BCYRankingPagerFragment;
import f.star.iota.milk.ui.bcy.selected.BCYSelectedFragment;
import f.star.iota.milk.ui.booru.BooruFragment;
import f.star.iota.milk.ui.chinagirlol.china.ChinaGirlOLFragment;
import f.star.iota.milk.ui.cosplayla.cosplay.CosplayLaFragment;
import f.star.iota.milk.ui.gamersky.gamer.GamerSkyFragment;
import f.star.iota.milk.ui.magmoe.mag.MagPagerFragment;
import f.star.iota.milk.ui.moe005tv.moe.MOE005TVPagerFragment;
import f.star.iota.milk.ui.www005tv.www.WWW005TVFragment;

public class MenuCosplayFragment extends MenuFragment {

    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {

            case Menus.MENU_3DBOORU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BooruFragment.newInstance(Url._3DBOORU);
                activity.setTitle(Menus.MENU_3DBOORU);
                break;

            case Menus.MENU_BCY_COS_SELECTED_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BCYSelectedFragment.newInstance(Url.BCY_COS_SELECTED);
                activity.setTitle(Menus.MENU_BCY_SELECTED);
                break;

            case Menus.MENU_BCY_COS_RANKING_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BCYRankingPagerFragment.newInstance(BCYRankingPagerFragment.COS);
                activity.setTitle(Menus.MENU_BCY_RANKING);
                break;

            case Menus.MENU_MAG_MOE_COS_STAR_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new MagPagerFragment();
                activity.setTitle(Menus.MENU_MAG_MOE);
                break;
            case Menus.MENU_WWW_005_TV_COS_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = WWW005TVFragment.newInstance(Url.WWW_005_TV_COS);
                activity.setTitle(Menus.MENU_WWW_005_TV);
                break;
            case Menus.MENU_COSPLAY_LA_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = CosplayLaFragment.newInstance(Url.COSPLAY_LA);
                activity.setTitle(Menus.MENU_COSPLAY_LA);
                break;
            case Menus.MENU_CHINAGIRLOL_ID_COS:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = ChinaGirlOLFragment.newInstance(Url.CHINAGIRLOL_COS);
                activity.setTitle(Menus.MENU_CHINAGIRLOL);
                break;
            case Menus.MENU_MOE005TV_COS_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = MOE005TVPagerFragment.newInstance(MOE005TVPagerFragment.COS);
                activity.setTitle(Menus.MENU_MOE005TV);
                break;
            case Menus.MENU_ACG_GAMERSKY_COS_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = GamerSkyFragment.newInstance(Url.ACG_GAMERSKY_COS);
                activity.setTitle(Menus.MENU_ACG_GAMERSKY);
                break;
        }
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_3DBOORU_ID, Menus.MENU_3DBOORU,
                Url._3DBOORU_BASE, "http://behoimi.org/images/lg.png", Url._3DBOORU_LOGIN));
        menu.add(new MenuBean(Menus.MENU_MAG_MOE_COS_STAR_ID, Menus.MENU_MAG_MOE,
                Url.MAG_MOE_BASE, "http://mag.moe/wp-content/themes/magmoe/img/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_WWW_005_TV_COS_ID, Menus.MENU_WWW_005_TV,
                Url.WWW_005_TV_BASE, "http://www.005.tv/templets/muban/style/images/bannerbg.jpg", null));
        menu.add(new MenuBean(Menus.MENU_COSPLAY_LA_ID, Menus.MENU_COSPLAY_LA,
                Url.COSPLAY_LA_BASE, "http://cosplay.la/content/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_BCY_COS_SELECTED_ID, Menus.MENU_BCY_SELECTED,
                Url.BCY_BASE, "https://pubin.bcyimg.com/Image/sub-nav/logo-home-9e8a0985d6.png", null));
        menu.add(new MenuBean(Menus.MENU_BCY_COS_RANKING_ID, Menus.MENU_BCY_RANKING,
                Url.BCY_BASE, "https://pubin.bcyimg.com/Image/sub-nav/logo-home-9e8a0985d6.png", null));
        menu.add(new MenuBean(Menus.MENU_CHINAGIRLOL_ID_COS, Menus.MENU_CHINAGIRLOL,
                Url.CHINAGIRLOL_BASE, "http://www.chinagirlol.cc/template/bbf_cg/src/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_MOE005TV_COS_ID, Menus.MENU_MOE005TV,
                Url.MOE005TV_BASE, "http://www.005.tv/templets/muban/moe_style/image/moe_logo.png", null));
        menu.add(new MenuBean(Menus.MENU_ACG_GAMERSKY_COS_ID, Menus.MENU_ACG_GAMERSKY,
                Url.ACG_GAMERSKY_BASE, "http://image.gamersky.com/webimg13/acg/new/logo.png", null));
        return menu;
    }
}
