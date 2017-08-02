package f.star.iota.milk.ui.menu;


import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Url;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.ui.chinagirlol.china.ChinaGirlOLPagerFragment;
import f.star.iota.milk.ui.gank.GankFragment;
import f.star.iota.milk.ui.jdlingyu.jd.JDLINGYUPagerFragment;
import f.star.iota.milk.ui.lesmao.les.LesmaoFragment;
import f.star.iota.milk.ui.mmonly.mm.MMONLYFragment;
import f.star.iota.milk.ui.mzitu.mzi.MZITUFragment;
import f.star.iota.milk.ui.rosiyy.rosi.ROSIYYFragment;
import f.star.iota.milk.ui.tngou.TGPagerFragment;
import f.star.iota.milk.ui.www192ttcom.www.WWW192TTCOMPagerFragment;
import f.star.iota.milk.ui.www94taotucom.www.WWW94TAOTUCOMFragment;
import f.star.iota.milk.ui.xiumm.xiu.XIUMMFragment;
import f.star.iota.milk.ui.xiuren.xiu.XiuRenFragment;
import f.star.iota.milk.util.ConfigUtils;

public class MenuMeiziFragment extends MenuFragment {
    @Override
    protected void handleOnMenuItemOnClick(MenuBean menu) {
        BaseActivity activity = (BaseActivity) mContext;
        BaseFragment currentFragment = null;
        switch (menu.getId()) {
            case Menus.MENU_GANK_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = GankFragment.newInstance(Url.GANK);
                activity.setTitle(Menus.MENU_GANK);
                break;
            case Menus.MENU_TNGOU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new TGPagerFragment();
                activity.setTitle(Menus.MENU_TNGOU);
                break;
            case Menus.MENU_JDLINGYU_MEIZHI_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new JDLINGYUPagerFragment();
                activity.setTitle(Menus.MENU_JDLINGYU);
                break;

            case Menus.MENU_LESMAO_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = LesmaoFragment.newInstance(Url.LESMAO);
                activity.setTitle(Menus.MENU_LESMAO);
                break;
            case Menus.MENU_XIUMM_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = XIUMMFragment.newInstance(Url.XIUMM);
                activity.setTitle(Menus.MENU_XIUMM);
                break;
            case Menus.MENU_94TAOTU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = WWW94TAOTUCOMFragment.newInstance(Url.WWW94TAOTUCOM);
                activity.setTitle(Menus.MENU_94TAOTU);
                break;
            case Menus.MENU_MZITU_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = MZITUFragment.newInstance(Url.MZITU);
                activity.setTitle(Menus.MENU_MZITU);
                break;
            case Menus.MENU_MMONLY_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = MMONLYFragment.newInstance(Url.MMONLY);
                activity.setTitle(Menus.MENU_MMONLY);
                break;
            case Menus.MENU_ROSIYY_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = ROSIYYFragment.newInstance(Url.ROSIYY);
                activity.setTitle(Menus.MENU_ROSIYY);
                break;
            case Menus.MENU_192TT_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new WWW192TTCOMPagerFragment();
                activity.setTitle(Menus.MENU_192TT);
                break;
            case Menus.MENU_XIUREN_ID:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = XiuRenFragment.newInstance(Url.XIUREN);
                activity.setTitle(Menus.MENU_XIUREN);
                break;
            case Menus.MENU_CHINAGIRLOL_ID_MZ:
                activity.removeFragmentContainerChildrenViews();
                currentFragment = new ChinaGirlOLPagerFragment();
                activity.setTitle(Menus.MENU_CHINAGIRLOL);
                break;
        }
        activity.showFragment(currentFragment);
    }

    @Override
    protected List<MenuBean> getMenuList() {
        List<MenuBean> menu = new ArrayList<>();
        menu.add(new MenuBean(Menus.MENU_GANK_ID, Menus.MENU_GANK,
                Url.GANK_BASE, "http://gank.io/static/favicon.ico", null));
        menu.add(new MenuBean(Menus.MENU_TNGOU_ID, Menus.MENU_TNGOU,
                Url.TNGOU_BASE, "http://www.tngou.net/tnfs/common/amazeui/i/favicon.png", null));
        menu.add(new MenuBean(Menus.MENU_JDLINGYU_MEIZHI_ID, Menus.MENU_JDLINGYU,
                Url.JDLINGYU_BASE, "http://www.jdlingyu.moe/wp-content/uploads/2017/01/2017-01-07_20-57-14.png", null));
        menu.add(new MenuBean(Menus.MENU_LESMAO_ID, Menus.MENU_LESMAO,
                Url.LESMAO_BASE, "http://www.lesmao.com/static/image/common/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_XIUMM_ID, Menus.MENU_XIUMM,
                Url.XIUMM_BASE, "http://www.xiumm.org/themes/sense/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_94TAOTU_ID, Menus.MENU_94TAOTU,
                Url.WWW94TAOTUCOM_BASE, "http://www.94taotu.com/themes/sense/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_MZITU_ID, Menus.MENU_MZITU,
                Url.MZITU_BASE, "http://i.meizitu.net/pfiles/img/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_MMONLY_ID, Menus.MENU_MMONLY,
                Url.MMONLY_BASE, "http://www.mmonly.cc/skins/images/mmonly1.png", null));
        menu.add(new MenuBean(Menus.MENU_ROSIYY_ID, Menus.MENU_ROSIYY,
                Url.ROSIYY_BASE, "http://www.rosiyy.com/usr/themes/mm/images/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_192TT_ID, Menus.MENU_192TT,
                Url.WWW192TTCOM_BASE, "http://www.192tt.com/style/logo/logo.png", null));
        menu.add(new MenuBean(Menus.MENU_CHINAGIRLOL_ID_MZ, Menus.MENU_CHINAGIRLOL,
                Url.CHINAGIRLOL_BASE, "http://www.chinagirlol.cc/template/bbf_cg/src/logo.png", null));
        if (!ConfigUtils.getR(aContext)) {
            menu.add(new MenuBean(Menus.MENU_XIUREN_ID, Menus.MENU_XIUREN,
                    Url.XIUREN_BASE, "http://www.xiuren.org/logo.png", null));
        }
        return menu;
    }
}
