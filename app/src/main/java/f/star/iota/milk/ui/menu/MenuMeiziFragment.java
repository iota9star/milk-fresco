package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Net;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.config.OtherConfig;
import f.star.iota.milk.ui.bcy.ranking.BCYRankingPagerFragment;
import f.star.iota.milk.ui.bcy.selected.BCYSelectedFragment;
import f.star.iota.milk.ui.chinagirlol.china.ChinaGirlOLPagerFragment;
import f.star.iota.milk.ui.cosplayla.cosplay.CosplayLaFragment;
import f.star.iota.milk.ui.gamersky.gamer.GamerSkyFragment;
import f.star.iota.milk.ui.gank.GankFragment;
import f.star.iota.milk.ui.girlatlas.girl.GirlAtlasPagerFragment;
import f.star.iota.milk.ui.gravuregirlz.gravure.GravureGirlZFragment;
import f.star.iota.milk.ui.jdlingyu.jd.JDLINGYUPagerFragment;
import f.star.iota.milk.ui.lesmao.les.LesmaoFragment;
import f.star.iota.milk.ui.magmoe.mag.MagPagerFragment;
import f.star.iota.milk.ui.meimeizi.mei.MeiMeiZiFragment;
import f.star.iota.milk.ui.miaowu.miao.MiaoWuPagerFragment;
import f.star.iota.milk.ui.mmjpg.mm.MmjpgFragment;
import f.star.iota.milk.ui.mmonly.mm.MMONLYFragment;
import f.star.iota.milk.ui.moe005tv.moe.MOE005TVPagerFragment;
import f.star.iota.milk.ui.mzitu.mzi.MZITUFragment;
import f.star.iota.milk.ui.rosiyy.rosi.ROSIYYFragment;
import f.star.iota.milk.ui.taotutt.tao.TAOTUTTFragment;
import f.star.iota.milk.ui.tngou.TGPagerFragment;
import f.star.iota.milk.ui.umei.u.UMEIFragment;
import f.star.iota.milk.ui.www005tv.www.WWW005TVFragment;
import f.star.iota.milk.ui.www192ttcom.www.WWW192TTCOMPagerFragment;
import f.star.iota.milk.ui.www94taotucom.www.WWW94TAOTUCOMFragment;
import f.star.iota.milk.ui.xiumm.xiu.XIUMMFragment;
import f.star.iota.milk.ui.xiuren.xiu.XiuRenFragment;
import f.star.iota.milk.ui.youwu.you.YouWuPagerFragment;
import f.star.iota.milk.ui.zdt8.zd.ZDT8Fragment;

public class MenuMeiziFragment extends MenuFragment {
    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {
            case Menus.MENU_BCY_COS_SELECTED_ID:
                currentFragment = BCYSelectedFragment.newInstance(Net.BCY_COS_SELECTED);
                activity.setTitle(Menus.MENU_BCY_SELECTED);
                break;

            case Menus.MENU_BCY_COS_RANKING_ID:
                currentFragment = BCYRankingPagerFragment.newInstance(BCYRankingPagerFragment.COS);
                activity.setTitle(Menus.MENU_BCY_RANKING);
                break;

            case Menus.MENU_MAG_MOE_COS_STAR_ID:
                currentFragment = new MagPagerFragment();
                activity.setTitle(Menus.MENU_MAG_MOE);
                break;
            case Menus.MENU_WWW_005_TV_COS_ID:
                currentFragment = WWW005TVFragment.newInstance(Net.WWW_005_TV_COS);
                activity.setTitle(Menus.MENU_WWW_005_TV);
                break;
            case Menus.MENU_COSPLAY_LA_ID:
                currentFragment = CosplayLaFragment.newInstance(Net.COSPLAY_LA);
                activity.setTitle(Menus.MENU_COSPLAY_LA);
                break;
            case Menus.MENU_MOE005TV_COS_ID:
                currentFragment = MOE005TVPagerFragment.newInstance(MOE005TVPagerFragment.COS);
                activity.setTitle(Menus.MENU_MOE005TV);
                break;
            case Menus.MENU_ACG_GAMERSKY_COS_ID:
                currentFragment = GamerSkyFragment.newInstance(Net.GAMERSKY_COS);
                activity.setTitle(Menus.MENU_ACG_GAMERSKY);
                break;
            case Menus.MENU_GANK_ID:
                currentFragment = GankFragment.newInstance(Net.GANK);
                activity.setTitle(Menus.MENU_GANK);
                break;
            case Menus.MENU_TNGOU_ID:
                currentFragment = new TGPagerFragment();
                activity.setTitle(Menus.MENU_TNGOU);
                break;
            case Menus.MENU_JDLINGYU_MEIZHI_ID:
                currentFragment = new JDLINGYUPagerFragment();
                activity.setTitle(Menus.MENU_JDLINGYU);
                break;

            case Menus.MENU_LESMAO_ID:
                currentFragment = LesmaoFragment.newInstance(Net.LESMAO);
                activity.setTitle(Menus.MENU_LESMAO);
                break;
            case Menus.MENU_XIUMM_ID:
                currentFragment = XIUMMFragment.newInstance(Net.XIUMM);
                activity.setTitle(Menus.MENU_XIUMM);
                break;
            case Menus.MENU_94TAOTU_ID:
                currentFragment = WWW94TAOTUCOMFragment.newInstance(Net.WWW94TAOTUCOM);
                activity.setTitle(Menus.MENU_94TAOTU);
                break;
            case Menus.MENU_MZITU_ID:
                currentFragment = MZITUFragment.newInstance(Net.MZITU);
                activity.setTitle(Menus.MENU_MZITU);
                break;
            case Menus.MENU_MMONLY_ID:
                currentFragment = MMONLYFragment.newInstance(Net.MMONLY);
                activity.setTitle(Menus.MENU_MMONLY);
                break;
            case Menus.MENU_ROSIYY_ID:
                currentFragment = ROSIYYFragment.newInstance(Net.ROSIYY);
                activity.setTitle(Menus.MENU_ROSIYY);
                break;
            case Menus.MENU_192TT_ID:
                currentFragment = new WWW192TTCOMPagerFragment();
                activity.setTitle(Menus.MENU_192TT);
                break;
            case Menus.MENU_XIUREN_ID:
                currentFragment = XiuRenFragment.newInstance(Net.XIUREN);
                activity.setTitle(Menus.MENU_XIUREN);
                break;
            case Menus.MENU_CHINAGIRLOL_ID:
                currentFragment = new ChinaGirlOLPagerFragment();
                activity.setTitle(Menus.MENU_CHINAGIRLOL);
                break;
            case Menus.MENU_MIAOWU_ID:
                currentFragment = new MiaoWuPagerFragment();
                activity.setTitle(Menus.MENU_MIAOWU);
                break;
            case Menus.MENU_MEIMEIZI_ID:
                currentFragment = MeiMeiZiFragment.newInstance(Net.MEIMEIZI);
                activity.setTitle(Menus.MENU_MEIMEIZI);
                break;
            case Menus.MENU_YOUWU_ID:
                currentFragment = new YouWuPagerFragment();
                activity.setTitle(Menus.MENU_YOUWU);
                break;
            case Menus.MENU_TAOTUTT_ID:
                currentFragment = TAOTUTTFragment.newInstance(Net.TAOTUTT);
                activity.setTitle(Menus.MENU_TAOTUTT);
                break;
            case Menus.MENU_UMEI_ID:
                currentFragment = UMEIFragment.newInstance(Net.UMEI);
                activity.setTitle(Menus.MENU_UMEI);
                break;
            case Menus.MENU_GRAVUREGIRLZ_ID:
                currentFragment = GravureGirlZFragment.newInstance(Net.GRAVUREGIRLZ);
                activity.setTitle(Menus.MENU_GRAVUREGIRLZ);
                break;
            case Menus.MENU_MMJPG_ID:
                currentFragment = MmjpgFragment.newInstance(Net.MMJPG);
                activity.setTitle(Menus.MENU_MMJPG);
                break;
            case Menus.MENU_GIRL_ATLAS_ID:
                currentFragment = new GirlAtlasPagerFragment();
                activity.setTitle(Menus.MENU_GIRL_ATLAS);
                break;
            case Menus.MENU_ZDT8_ID:
                currentFragment = ZDT8Fragment.newInstance(Net.ZDT8);
                activity.setTitle(Menus.MENU_ZDT8);
                break;
        }
        activity.removeFragmentContainerChildrenViews();
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_MAG_MOE_COS_STAR_ID, Menus.MENU_MAG_MOE,
                Net.MAG_MOE_BASE, "http://mag.moe/wp-content/themes/magmoe/img/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_WWW_005_TV_COS_ID, Menus.MENU_WWW_005_TV,
                Net.WWW_005_TV_BASE, "http://www.005.tv/templets/muban/style/images/bannerbg.jpg", null));
        menu.add(new MenuBean(Menus.MENU_COSPLAY_LA_ID, Menus.MENU_COSPLAY_LA,
                Net.COSPLAY_LA_BASE, "http://cosplay.la/content/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_BCY_COS_SELECTED_ID, Menus.MENU_BCY_SELECTED,
                Net.BCY_BASE, "https://pubin.bcyimg.com/Image/sub-nav/logo-home-9e8a0985d6.png", null));
        menu.add(new MenuBean(Menus.MENU_BCY_COS_RANKING_ID, Menus.MENU_BCY_RANKING,
                Net.BCY_BASE, "https://pubin.bcyimg.com/Image/sub-nav/logo-home-9e8a0985d6.png", null));
        menu.add(new MenuBean(Menus.MENU_MOE005TV_COS_ID, Menus.MENU_MOE005TV,
                Net.MOE005TV_BASE, "http://www.005.tv/templets/muban/moe_style/image/moe_logo.png", null));
        menu.add(new MenuBean(Menus.MENU_ACG_GAMERSKY_COS_ID, Menus.MENU_ACG_GAMERSKY,
                Net.GAMERSKY_BASE, "http://image.gamersky.com/webimg13/acg/new/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_GANK_ID, Menus.MENU_GANK,
                Net.GANK_BASE, "https://ws1.sinaimg.cn/large/610dc034ly1fiiiyfcjdoj20u00u0ju0.jpg", null));
        menu.add(new MenuBean(Menus.MENU_TNGOU_ID, Menus.MENU_TNGOU,
                Net.TNGOU_BASE, "https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg", null));
        menu.add(new MenuBean(Menus.MENU_JDLINGYU_MEIZHI_ID, Menus.MENU_JDLINGYU,
                Net.JDLINGYU_BASE, "http://www.jdlingyu.moe/wp-content/uploads/2017/01/2017-01-07_20-57-14.png", null));
        menu.add(new MenuBean(Menus.MENU_LESMAO_ID, Menus.MENU_LESMAO,
                Net.LESMAO_BASE, "http://www.lesmao.com/static/image/common/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_XIUMM_ID, Menus.MENU_XIUMM,
                Net.XIUMM_BASE, "http://www.xiumm8.com/data/t/0188/86/15039196529439_album.jpg", null));
        menu.add(new MenuBean(Menus.MENU_94TAOTU_ID, Menus.MENU_94TAOTU,
                Net.WWW94TAOTUCOM_BASE, "http://img.94taotu.com/data/t/0155/07/1503666691557_album.jpg", null));
        menu.add(new MenuBean(Menus.MENU_MZITU_ID, Menus.MENU_MZITU,
                Net.MZITU_BASE, "https://ws1.sinaimg.cn/large/610dc034gy1fi678xgq1ij20pa0vlgo4.jpg", null));
        menu.add(new MenuBean(Menus.MENU_MMONLY_ID, Menus.MENU_MMONLY,
                Net.MMONLY_BASE, "http://www.mmonly.cc/skins/images/mmonly1.png", null));
        menu.add(new MenuBean(Menus.MENU_ROSIYY_ID, Menus.MENU_ROSIYY,
                Net.ROSIYY_BASE, "http://www.rosiyy.com/usr/themes/mm/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_192TT_ID, Menus.MENU_192TT,
                Net.WWW192TTCOM_BASE, "http://www.192tt.com/style/logo/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_CHINAGIRLOL_ID, Menus.MENU_CHINAGIRLOL,
                Net.CHINAGIRLOL_BASE, "http://www.chinagirlol.cc/template/bbf_cg/src/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_MIAOWU_ID, Menus.MENU_MIAOWU,
                Net.MIAOWU_BASE, "http://www.miaowu.cc/picture/logo-3-1.png", null));
        menu.add(new MenuBean(Menus.MENU_MEIMEIZI_ID, Menus.MENU_MZITU,
                Net.MEIMEIZI + 1, "http://www.meimeizi.com/wp-content/themes/Loostrive/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_YOUWU_ID, Menus.MENU_YOUWU,
                Net.YOUWU_BASE, "https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg", null));
        menu.add(new MenuBean(Menus.MENU_TAOTUTT_ID, Menus.MENU_TAOTUTT,
                Net.TAOTUTT_BASE, "http://taotutt.com/wp-content/themes/cx-udy/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_UMEI_ID, Menus.MENU_UMEI,
                Net.UMEI, "http://www.umei.cc/images/logo4.png", null));
        menu.add(new MenuBean(Menus.MENU_MMJPG_ID, Menus.MENU_MMJPG,
                Net.MMJPG_BASE, "http://img.mmjpg.com/2015/295/1.jpg", null));
        menu.add(new MenuBean(Menus.MENU_GIRL_ATLAS_ID, Menus.MENU_GIRL_ATLAS,
                Net.GIRL_ATLAS_BASE, "https://girlatlasfile.b0.upaiyun.com/static/img/logo@2x.png", null));
        menu.add(new MenuBean(Menus.MENU_ZDT8_ID, Menus.MENU_ZDT8,
                Net.ZDT8_BASE, "http://www.zdt8.com/style/logo/logo.png", null));
        if (!OtherConfig.getR(aContext)) {
            menu.add(new MenuBean(Menus.MENU_XIUREN_ID, Menus.MENU_XIUREN,
                    Net.XIUREN_BASE, "http://www.xiuren.org/logo.png", null));
            menu.add(new MenuBean(Menus.MENU_GRAVUREGIRLZ_ID, Menus.MENU_GRAVUREGIRLZ,
                    Net.GRAVUREGIRLZ_BASE, "https://i0.wp.com/www.gravuregirlz.com/wp-content/uploads/2015/10/145422/gg.png", null));
        }
        return menu;
    }
}
