package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Net;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.config.OtherConfig;
import f.star.iota.milk.ui.animepictures.anime.AnimePictureFragment;
import f.star.iota.milk.ui.apic.a.ApicPagerFragment;
import f.star.iota.milk.ui.bcy.ranking.BCYRankingPagerFragment;
import f.star.iota.milk.ui.bcy.selected.BCYSelectedFragment;
import f.star.iota.milk.ui.booru.BooruFragment;
import f.star.iota.milk.ui.donmai.DonmaiFragment;
import f.star.iota.milk.ui.eshuushuu.ESHUUSHUUFragment;
import f.star.iota.milk.ui.gacha.GachaPagerFragment;
import f.star.iota.milk.ui.gamersky.gamer.GamerSkyPagerFragment;
import f.star.iota.milk.ui.jdlingyu.jd.JDLINGYUFragment;
import f.star.iota.milk.ui.lingyu.ling.LingYuFragment;
import f.star.iota.milk.ui.magmoe.mag.MagFragment;
import f.star.iota.milk.ui.mangadrawing.MangaDrawingHentaiPagerFragment;
import f.star.iota.milk.ui.mangadrawing.MangaDrawingPagerFragment;
import f.star.iota.milk.ui.minitokyo.MINITOKYOFragment;
import f.star.iota.milk.ui.moe005tv.moe.MOE005TVPagerFragment;
import f.star.iota.milk.ui.moeimg.moe.MoeimgFragment;
import f.star.iota.milk.ui.nijieroch.nijiero.NijieroCHFragment;
import f.star.iota.milk.ui.pangci.PANGCIFragment;
import f.star.iota.milk.ui.wallhaven.wall.WallHavenFragment;
import f.star.iota.milk.ui.www005tv.www.WWW005TVPagerFragment;
import f.star.iota.milk.ui.yuriimg.YuriImgPagerFragment;
import f.star.iota.milk.ui.zerochan.ZerochanFragment;

public class MenuIllustrationFragment extends MenuFragment {
    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {
            case Menus.MENU_YANDE_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BooruFragment.newInstance(Net.YANDE);
                activity.setTitle(Menus.MENU_YANDE);
                break;
            case Menus.MENU_KONACHAN_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BooruFragment.newInstance(Net.KONACHAN);
                activity.setTitle(Menus.MENU_KONACHAN);
                break;
            case Menus.MENU_LOLIBOORU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BooruFragment.newInstance(Net.LOLIBOORU);
                activity.setTitle(Menus.MENU_LOLIBOORU);
                break;
            case Menus.MENU_DANBOORU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = DonmaiFragment.newInstance(Net.DANBOORU);
                activity.setTitle(Menus.MENU_DANBOORU);
                break;
            case Menus.MENU_SAFEBOORU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = DonmaiFragment.newInstance(Net.SAFEBOORU);
                activity.setTitle(Menus.MENU_SAFEBOORU);
                break;
            case Menus.MENU_E621_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BooruFragment.newInstance(Net.E621);
                activity.setTitle(Menus.MENU_E621);
                break;
            case Menus.MENU_E926_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BooruFragment.newInstance(Net.E926);
                activity.setTitle(Menus.MENU_E926);
                break;
            case Menus.MENU_WALLHAVEN_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = WallHavenFragment.newInstance(Net.WALLHAVEN);
                activity.setTitle(Menus.MENU_WALLHAVEN);
                break;
            case Menus.MENU_GACHA_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new GachaPagerFragment();
                activity.setTitle(Menus.MENU_GACHA);
                break;
            case Menus.MENU_BCY_ILLUST_SELECTED_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BCYSelectedFragment.newInstance(Net.BCY_ILLUST_SELECTED);
                activity.setTitle(Menus.MENU_BCY_SELECTED);
                break;
            case Menus.MENU_BCY_ILLUST_RANKING_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = BCYRankingPagerFragment.newInstance(BCYRankingPagerFragment.ILLUST);
                activity.setTitle(Menus.MENU_BCY_RANKING);
                break;
            case Menus.MENU_MANGA_DRAWING_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new MangaDrawingPagerFragment();
                activity.setTitle(Menus.MENU_MANGA_DRAWING);
                break;
            case Menus.MENU_MANGA_DRAWING_HENTAI_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new MangaDrawingHentaiPagerFragment();
                activity.setTitle(Menus.MENU_MANGA_DRAWING);
                break;
            case Menus.MENU_MAG_MOE_MOE_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = MagFragment.newInstance(Net.MAG_MOE_MOE);
                activity.setTitle(Menus.MENU_MAG_MOE);
                break;
            case Menus.MENU_APIC_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new ApicPagerFragment();
                activity.setTitle(Menus.MENU_APIC);
                break;
            case Menus.MENU_ZEROCHAN_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = ZerochanFragment.newInstance(Net.ZEROCHAN);
                activity.setTitle(Menus.MENU_ZEROCHAN);
                break;
            case Menus.MENU_E_SHUUSHUU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = ESHUUSHUUFragment.newInstance(Net.E_SHUUSHUU);
                activity.setTitle(Menus.MENU_E_SHUUSHUU);
                break;
            case Menus.MENU_MINITOKYO_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = MINITOKYOFragment.newInstance(Net.MINITOKYO);
                activity.setTitle(Menus.MENU_MINITOKYO);
                break;
            case Menus.MENU_WWW_005_TV_ACG_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new WWW005TVPagerFragment();
                activity.setTitle(Menus.MENU_WWW_005_TV);
                break;

            case Menus.MENU_JDLINGYU_ACG_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = JDLINGYUFragment.newInstance(Net.JDLINGYU_ACG);
                activity.setTitle(Menus.MENU_JDLINGYU);
                break;
            case Menus.MENU_LINGYU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = LingYuFragment.newInstance(Net.LINGYU);
                activity.setTitle(Menus.MENU_LINGYU);
                break;
            case Menus.MENU_MOEIMG_ID:
                activity.removeFragmentContainerChildrenViews();
                if (!OtherConfig.getR(aContext)) {
                    currentFragment = MoeimgFragment.newInstance(Net.MOEIMG_H);
                } else {
                    currentFragment = MoeimgFragment.newInstance(Net.MOEIMG);
                }
                activity.setTitle(Menus.MENU_MOEIMG);
                break;
            case Menus.MENU_NIJIERO_CH_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = NijieroCHFragment.newInstance(Net.NIJIERO_CH);
                activity.setTitle(Menus.MENU_NIJIERO_CH);
                break;

            case Menus.MENU_MOE005TV_ACG_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = MOE005TVPagerFragment.newInstance(MOE005TVPagerFragment.ACG);
                activity.setTitle(Menus.MENU_MOE005TV);
                break;
            case Menus.MENU_ACG_GAMERSKY_ACG_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new GamerSkyPagerFragment();
                activity.setTitle(Menus.MENU_ACG_GAMERSKY);
                break;
            case Menus.MENU_PANGCI_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = PANGCIFragment.newInstance(Net.PANGCI);
                activity.setTitle(Menus.MENU_PANGCI);
                break;
            case Menus.MENU_YURIIMG_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new YuriImgPagerFragment();
                activity.setTitle(Menus.MENU_YURIIMG);
                break;
            case Menus.MENU_ANIME_PICTURES_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = AnimePictureFragment.newInstance(Net.ANIME_PICTURES);
                activity.setTitle(Menus.MENU_ANIME_PICTURES);
                break;
        }
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_YANDE_ID,
                Menus.MENU_YANDE,
                Net.YANDE_BASE,
                "https://assets.yande.re/assets/logo_small-418e8d5ec0229f274edebe4af43b01aa29ed83b715991ba14bb41ba06b5b57b5.png",
                Net.YANDE_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_KONACHAN_ID,
                Menus.MENU_KONACHAN,
                Net.KONACHAN_BASE,
                "https://konachan.com/images/logo.png",
                Net.KONACHAN_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_LOLIBOORU_ID,
                Menus.MENU_LOLIBOORU,
                Net.LOLIBOORU_BASE,
                "https://lolibooru.moe/images/logo.png",
                Net.LOLIBOORU_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_DANBOORU_ID,
                Menus.MENU_DANBOORU,
                Net.DANBOORU_BASE,
                "https://danbooru.donmai.us/data/preview/cda80f4df5c3c7f6501145671b4ab0f2.jpg",
                Net.DANBOORU_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_SAFEBOORU_ID,
                Menus.MENU_SAFEBOORU,
                Net.SAFEBOORU_BASE,
                "https://safebooru.donmai.us/data/preview/dd1d5af69cd4b97790bd86b9e1b42459.jpg",
                Net.SAFEBOORU_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_E621_ID,
                Menus.MENU_E621,
                Net.E621_BASE,
                "https://e621.net/images/mascot_bg/evalionfix.jpg",
                Net.E621_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_E926_ID,
                Menus.MENU_E926,
                Net.E926_BASE,
                "http://e926.net/images/mascot_bg/peacock.png",
                Net.E926_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_MOEIMG_ID,
                Menus.MENU_MOEIMG,
                Net.MOEIMG_BASE,
                "http://img.moeimg.net/wp-content/uploads/img/moeimg_pc.gif",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_WALLHAVEN_ID,
                Menus.MENU_WALLHAVEN,
                Net.WALLHAVEN_BASE,
                "https://alpha.wallhaven.cc/images/layout/logo.png",
                Net.WALLHAVEN_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_GACHA_ID,
                Menus.MENU_GACHA,
                Net.GACHA_BASE,
                "http://gacha.cdn.126.net/src/image/all/logo.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_BCY_ILLUST_SELECTED_ID,
                Menus.MENU_BCY_SELECTED,
                Net.BCY_BASE,
                "https://pubin.bcyimg.com/Image/sub-nav/logo-home-9e8a0985d6.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_BCY_ILLUST_RANKING_ID,
                Menus.MENU_BCY_RANKING,
                Net.BCY_BASE,
                "https://pubin.bcyimg.com/Image/sub-nav/logo-home-9e8a0985d6.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_MANGA_DRAWING_ID,
                Menus.MENU_MANGA_DRAWING,
                Net.MANGA_DRAWING_BASE,
                "http://static.mangadrawing.net/themes/shinpatsu/md.jpg",
                Net.MANGA_DRAWING_LOGIN
        ));
        if (!OtherConfig.getR(aContext)) {
            menu.add(new MenuBean(Menus.MENU_MANGA_DRAWING_HENTAI_ID,
                    Menus.MENU_MANGA_DRAWING_HENTAI,
                    Net.MANGA_DRAWING_BASE,
                    "http://static.mangadrawing.net/themes/shinpatsu/md.jpg",
                    Net.MANGA_DRAWING_LOGIN
            ));
            menu.add(new MenuBean(Menus.MENU_NIJIERO_CH_ID,
                    Menus.MENU_NIJIERO_CH,
                    Net.NIJIERO_CH_BASE,
                    "https://nijiero-ch.com/wp-content/themes/erosite-theme/images/pc/img_title.png",
                    null
            ));
        }
        menu.add(new MenuBean(Menus.MENU_MAG_MOE_MOE_ID,
                Menus.MENU_MAG_MOE,
                Net.MAG_MOE_BASE,
                "http://mag.moe/wp-content/themes/magmoe/img/logo.png",
                null
        ));
        menu.add(new MenuBean(
                Menus.MENU_APIC_ID,
                Menus.MENU_APIC,
                Net.APIC_BASE,
                "http://img.gov.com.de/2015/04/apic-in-%E6%A3%92%E6%A3%92%E7%B3%96-3-565x800.jpg",
                Net.APIC_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_ZEROCHAN_ID,
                Menus.MENU_ZEROCHAN,
                Net.ZEROCHAN_BASE,
                "http://s1.zerochan.net/header-1.jpg",
                Net.ZEROCHAN_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_E_SHUUSHUU_ID,
                Menus.MENU_E_SHUUSHUU,
                Net.E_SHUUSHUU_BASE,
                "http://e-shuushuu.net/common/image/banner/hikari-chan5/middle.jpg",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_MINITOKYO_ID,
                Menus.MENU_MINITOKYO,
                Net.MINITOKYO_BASE,
                "http://static2.minitokyo.net/view/46/07/707896.jpg",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_WWW_005_TV_ACG_ID,
                Menus.MENU_WWW_005_TV,
                Net.WWW_005_TV_BASE,
                "http://www.005.tv/templets/muban/style/images/bannerbg.jpg",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_MOE005TV_ACG_ID,
                Menus.MENU_MOE005TV,
                Net.MOE005TV_BASE,
                "http://www.005.tv/templets/muban/moe_style/image/moe_logo.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_JDLINGYU_ACG_ID,
                Menus.MENU_JDLINGYU,
                Net.JDLINGYU_BASE,
                "http://www.jdlingyu.moe/wp-content/uploads/2017/01/2017-01-07_20-57-14.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_LINGYU_ID,
                Menus.MENU_LINGYU,
                Net.LINGYU_BASE,
                "http://tp.lingyu.me/bz/uploads/2016/07/www.lingyu.me_20160728125540677.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_ACG_GAMERSKY_ACG_ID,
                Menus.MENU_ACG_GAMERSKY,
                Net.ACG_GAMERSKY_BASE,
                "http://image.gamersky.com/webimg13/acg/new/logo.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_PANGCI_ID,
                Menus.MENU_PANGCI,
                Net.PANGCI_BASE,
                "https://www.pangci.cc/skin2015/logo.png",
                null
        ));
        menu.add(new MenuBean(Menus.MENU_YURIIMG_ID,
                Menus.MENU_YURIIMG,
                Net.YURIIMG_BASE,
                "http://yuri.logacg.com/1707/1e7ca5bbe5e2ee9346372bc76bbc5f96.png!single.webp",
                Net.YURIIMG_LOGIN
        ));
        menu.add(new MenuBean(Menus.MENU_ANIME_PICTURES_ID,
                Menus.MENU_ANIME_PICTURES,
                Net.ANIME_PICTURES_BASE,
                "https://anime-pictures.net/static/styles/first/images/back_patern.png",
                Net.ANIME_PICTURES_LOGIN
        ));
        return menu;
    }
}
