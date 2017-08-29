package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Net;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.ui.alphacoders.AlphaCodersFragment;
import f.star.iota.milk.ui.bilibili.BilibiliFragment;
import f.star.iota.milk.ui.bing.BingFragment;
import f.star.iota.milk.ui.gamersky.gamer.GamerSkyFragment;
import f.star.iota.milk.ui.isujin.IsujinFragment;
import f.star.iota.milk.ui.justinmaller.justin.JustinMallerFragment;
import f.star.iota.milk.ui.kuvva.ku.KuvvaFragment;
import f.star.iota.milk.ui.magdeleine.MagdeleineFragment;
import f.star.iota.milk.ui.simpledesktops.SimpleDesktopsFragment;
import f.star.iota.milk.ui.wallhaven.wall.WallHavenFragment;

public class MenuWallpaperFragment extends MenuFragment {
    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {
            case Menus.MENU_BING_ID:
                currentFragment = BingFragment.newInstance(Net.BING);
                activity.setTitle(Menus.MENU_BING);
                break;
            case Menus.MENU_SIMPLEDESKTOPS_ID:
                currentFragment = SimpleDesktopsFragment.newInstance(Net.SIMPLEDESKTOPS);
                activity.setTitle(Menus.MENU_SIMPLEDESKTOPS);
                break;
            case Menus.MENU_BILIBILI_ID:
                currentFragment = BilibiliFragment.newInstance(Net.BILIBILI);
                activity.setTitle(Menus.MENU_BILIBILI);
                break;

            case Menus.MENU_KUVVA_ID:
                currentFragment = KuvvaFragment.newInstance(Net.KUVVA);
                activity.setTitle(Menus.MENU_KUVVA);
                break;
            case Menus.MENU_ISUJIN_ID:
                currentFragment = IsujinFragment.newInstance(Net.ISUJIN);
                activity.setTitle(Menus.MENU_ISUJIN);
                break;
            case Menus.MENU_JUSTINMALLER_ID:
                currentFragment = JustinMallerFragment.newInstance(Net.JUSTINMALLER);
                activity.setTitle(Menus.MENU_JUSTINMALLER);
                break;
            case Menus.MENU_WALLHAVEN_ID:
                currentFragment = WallHavenFragment.newInstance(Net.WALLHAVEN);
                activity.setTitle(Menus.MENU_WALLHAVEN);
                break;
            case Menus.MENU_MAGDELEINE_ID:
                currentFragment = MagdeleineFragment.newInstance(Net.MAGDELEINE);
                activity.setTitle(Menus.MENU_MAGDELEINE);
                break;
            case Menus.MENU_GAMERSKY_ID:
                currentFragment = GamerSkyFragment.newInstance(Net.GAMERSKY_BZ);
                activity.setTitle(Menus.MENU_GAMERSKY);
                break;
            case Menus.MENU_ALPHACODERS_ID:
                currentFragment = AlphaCodersFragment.newInstance(Net.ALPHACODERS);
                activity.setTitle(Menus.MENU_ALPHACODERS);
                break;
        }
        activity.removeFragmentContainerChildrenViews();
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_BING_ID,
                Menus.MENU_BING,
                Net.BING_BASE,
                "https://cn.bing.com/az/hprichbg/rb/BatEaredFox_ZH-CN12456670113_1920x1080.jpg",
                null));
        menu.add(new MenuBean(Menus.MENU_SIMPLEDESKTOPS_ID,
                Menus.MENU_SIMPLEDESKTOPS,
                Net.SIMPLEDESKTOPS + 1,
                "http://static.simpledesktops.com/uploads/desktops/2015/06/26/Overlap.png.295x184_q100.png",
                null));
        menu.add(new MenuBean(Menus.MENU_BILIBILI_ID,
                Menus.MENU_BILIBILI,
                Net.BILIBILI_BASE,
                "http://static.hdslb.com/drawyoo/wallpaper/images/logo.png",
                null
        ));

        menu.add(new MenuBean(Menus.MENU_KUVVA_ID,
                Menus.MENU_KUVVA,
                Net.KUVVA_BASE,
                "https://dvif77labeimb.cloudfront.net/assets/front_end/header/kuvvacon32-b0082c95bed936cc51fa29eb3fbca415.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_ISUJIN_ID,
                Menus.MENU_ISUJIN,
                Net.ISUJIN_BASE,
                "http://isujin.com/wp-content/themes/Diaspora/timthumb/timthumb.php?src=http://isujin.com/wp-content/uploads/2017/07/wallhaven-134449.jpg",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_JUSTINMALLER_ID,
                Menus.MENU_JUSTINMALLER,
                Net.JUSTINMALLER,
                "http://justinmaller.com/img/justin_maller.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_WALLHAVEN_ID,
                Menus.MENU_WALLHAVEN,
                Net.WALLHAVEN_BASE,
                "https://alpha.wallhaven.cc/images/layout/logo.png",
                Net.WALLHAVEN_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_MAGDELEINE_ID,
                Menus.MENU_MAGDELEINE,
                Net.MAGDELEINE_BASE,
                "https://cdn.magdeleine.co/wp-content/uploads/2017/08/chris-barbalis-211374-500x375.jpg",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_GAMERSKY_ID,
                Menus.MENU_GAMERSKY,
                Net.GAMERSKY_BASE,
                "http://image.gamersky.com/webimg15/logo.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_ALPHACODERS_ID,
                Menus.MENU_ALPHACODERS,
                Net.ALPHACODERS_BASE,
                "https://images4.alphacoders.com/867/thumb-350-867570.png",
                null
        ));
        return menu;
    }
}
