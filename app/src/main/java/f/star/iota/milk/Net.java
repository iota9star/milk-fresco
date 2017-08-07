package f.star.iota.milk;

public interface Net {
    String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.1.2716.5 Safari/537.36";

    String HITOKOTO_BILIBILIJJ = "http://hitokoto.bilibilijj.com/?status=get";
    String HITOKOTO_BILIBILIJJ_BASE = "http://www.jijidown.com/";
    String HITOKOTO_IMJAD = "https://api.imjad.cn/hitokoto/?encode=json&charset=utf-8";
    String HITOKOTO_IMJAD_BASE = "https://imjad.cn/";
    String HITOKOTO_LOLI = "https://api.satori.moe/hitokoto.php";
    String HITOKOTO_LOLI_BASE = "https://satori.moe";
    String YIJU = "http://yiju.ml/api/word.php";
    String YIJU_BASE = "http://yiju.ml/";

    String TODAY_IN_HISTORY = "http://kabe.im/";

    String YANDE = "https://yande.re/post.json?limit=36&page=";
    String YANDE_BASE = "https://yande.re";
    String YANDE_LOGIN = "https://yande.re/user/login";

    String KONACHAN = "https://konachan.com/post.json?limit=36&page=";
    String KONACHAN_LOGIN = "https://konachan.com/user/login";
    String KONACHAN_BASE = "https://konachan.com";

    String LOLIBOORU = "https://lolibooru.moe/post/index.json?limit=36&page=";
    String LOLIBOORU_LOGIN = "https://lolibooru.moe/post";
    String LOLIBOORU_BASE = "https://lolibooru.moe";

    String DANBOORU = "https://danbooru.donmai.us/posts.json?limit=36&page=";
    String DANBOORU_LOGIN = "https://danbooru.donmai.us/session/new";
    String DANBOORU_BASE = "https://danbooru.donmai.us";

    String SAFEBOORU = "https://safebooru.donmai.us/posts.json?limit=36&page=";
    String SAFEBOORU_LOGIN = "https://safebooru.donmai.us/session/new";
    String SAFEBOORU_BASE = "https://safebooru.donmai.us";

    String E926 = "https://e926.net/post/index.json?limit=36&page=";
    String E926_LOGIN = "http://e926.net/user/login";
    String E926_BASE = "https://e926.net";

    String E621 = "https://e621.net/post/index.json?limit=36&page=";
    String E621_LOGIN = "https://e621.net/user/login";
    String E621_BASE = "https://e621.net";

    String ANIME_PICTURES = "https://anime-pictures.net/pictures/view_posts/";
    String ANIME_PICTURES_LOGIN = "https://anime-pictures.net/";
    String ANIME_PICTURES_BASE = "https://anime-pictures.net";

    String WALLHAVEN = "https://alpha.wallhaven.cc/latest?page=";
    String WALLHAVEN_LOGIN = "https://alpha.wallhaven.cc/auth/login";
    String WALLHAVEN_BASE = "https://alpha.wallhaven.cc";

    String GANK = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/24/";
    String GANK_BASE = "http://gank.io";

    String BING = "http://cn.bing.com/HPImageArchive.aspx?format=js&n=24";
    String BING_BASE = "http://cn.bing.com";

    String TNGOU_BASE = "http://tnfs.tngou.net/image";
    String TNGOU_CLASSIFY = "http://www.tngou.net/tnfs/api/classify";
    String TNGOU_LIST = "http://www.tngou.net/tnfs/api/list?rows=24&id=";

    String GACHA_BASE = "http://gacha.163.com/ranking/pic/day/current";
    String GACHA_DAY = "http://gacha.163.com/ranking/pic/day/current";
    String GACHA_WEEK = "http://gacha.163.com/ranking/pic/week/current";
    String GACHA_MONTH = "http://gacha.163.com/ranking/pic/month/current";

    String BCY_BASE = "https://bcy.net";
    String BCY_ILLUST_SELECTED = "https://bcy.net/illust/discover?&p=";
    String BCY_COS_SELECTED = "https://bcy.net/coser/discover?&p=";
    String BCY_ILLUST_RANK_TODAY = "http://bcy.net/illust/toppost100?type=lastday";
    String BCY_ILLUST_RANK_WEEK = "http://bcy.net/illust/toppost100";
    String BCY_ILLUST_RANK_NEW_PEOPLE = "http://bcy.net/illust/toppost100?type=newPeople";
    String BCY_ILLUST_RANK_ART_WORK = "http://bcy.net/illust/toppost100?type=artwork";
    String BCY_COS_RANK_TODAY = "http://bcy.net/coser/toppost100?type=lastday";
    String BCY_COS_RANK_WEEK = "http://bcy.net/coser/toppost100";
    String BCY_COS_RANK_NEW_PEOPLE = "http://bcy.net/coser/toppost100?type=newPeople";
    String BCY_COS_RANK_ART_WORK = "http://bcy.net/coser/toppost100?type=artwork";

    String MANGA_DRAWING_BASE = "http://mangadrawing.net";
    String MANGA_DRAWING_LOGIN = "http://mangadrawing.net/user/login?destination=home";
    String MANGA_DRAWING_ALL = "http://mangadrawing.net/gallery?page=";
    String MANGA_DRAWING_IMAGES = "http://mangadrawing.net/gallery/images?page=";
    String MANGA_DRAWING_ARTWORKS = "http://mangadrawing.net/gallery/artworks?page=";
    String MANGA_DRAWING_FAVORITES = "http://mangadrawing.net/gallery/favorites?page=";
    String MANGA_DRAWING_DOWNLOADS = "http://mangadrawing.net/gallery/downloads?page=";
    String MANGA_DRAWING_POPULAR = "http://mangadrawing.net/gallery/views?page=";
    String MANGA_DRAWING_HENTAI_ALL = "http://hentai.mangadrawing.net/gallery?page=";
    String MANGA_DRAWING_HENTAI_IMAGES = "http://hentai.mangadrawing.net/gallery/images?page=";
    String MANGA_DRAWING_HENTAI_UNRECOGNIZED = "http://hentai.mangadrawing.net/gallery/unrecognized?page=";
    String MANGA_DRAWING_HENTAI_FAVORITES = "http://hentai.mangadrawing.net/gallery/favorites?page=";
    String MANGA_DRAWING_HENTAI_DOWNLOADS = "http://hentai.mangadrawing.net/gallery/downloads?page=";
    String MANGA_DRAWING_HENTAI_POPULAR = "http://hentai.mangadrawing.net/gallery/views?page=";

    String MAG_MOE_BASE = "http://mag.moe";
    String MAG_MOE_MOE = "http://mag.moe/category/images/page/";
    String MAG_MOE_COSPLAY = "http://mag.moe/category/cosplay/page/";
    String MAG_MOE_STAR = "http://mag.moe/category/celebrity/page/";

    String APIC_BASE = "http://www.apic.in";
    String APIC_LOGIN = "http://www.apic.in";
    String APIC_ALL = "http://www.apic.in/page/";
    String APIC_ANIME = "http://www.apic.in/anime/page/";
    String APIC_ZHIFU = "http://www.apic.in/zhifu/page/";
    String APIC_HENTAI = "http://www.apic.in/hentai/page/";
    String APIC_YUSANJIA = "http://www.apic.in/yusanjia/page/";
    String APIC_ZATUJI = "http://www.apic.in/zatuji/page/";
    String APIC_FULI = "http://www.apic.in/fuli/page/";

    String ZEROCHAN_BASE = "http://www.zerochan.net";
    String ZEROCHAN = "http://www.zerochan.net/?p=";
    String ZEROCHAN_LOGIN = "http://www.zerochan.net/login?ref=%2F";

    String E_SHUUSHUU = "http://e-shuushuu.net/?page=";
    String E_SHUUSHUU_LOGIN = "http://e-shuushuu.net/";
    String E_SHUUSHUU_BASE = "http://e-shuushuu.net";

    String MINITOKYO = "http://gallery.minitokyo.net/wallpapers?order=id&tid=&elite=0&highlight=0&dim=&display=thumbnails&page=";
    String MINITOKYO_BASE = "http://gallery.minitokyo.net";

    String WWW_005_TV_BASE = "http://www.005.tv";
    String WWW_005_TV_P = "http://www.005.tv/Cosplay/meizhouPzhanbenzitu/list_633_";
    String WWW_005_TV_ACG = "http://www.005.tv/Cosplay/erciyuanmeitu/list_632_";
    String WWW_005_TV_COS = "http://www.005.tv/Cosplay/Cosplay/list_631_";

    String JDLINGYU_ALL = "http://www.jdlingyu.moe/page/";
    String JDLINGYU_ZHUANTI = "http://www.jdlingyu.moe/%E4%B8%93%E9%A2%98/page/";
    String JDLINGYU_TEDIAN = "http://www.jdlingyu.moe/%E7%89%B9%E7%82%B9/page/";
    String JDLINGYU_NONGCAO = "http://www.jdlingyu.moe/%E5%BC%84%E6%BD%AE/page/";
    String JDLINGYU_COSPLAY = "http://www.jdlingyu.moe/cosplay/page/";
    String JDLINGYU_MZITU = "http://www.jdlingyu.wang/mzitu/page/";
    String JDLINGYU_HENTAI = "http://www.jdlingyu.wang/hentai/page/";
    String JDLINGYU_ACG = "http://www.jdlingyu.moe/acg/page/";
    String JDLINGYU_BASE = "http://www.jdlingyu.moe";

    String LESMAO = "http://www.lesmao.com/portal.php?page=";
    String LESMAO_BASE = "http://www.lesmao.com";

    String XIUMM = "http://www.xiumm.org/albums/page-";
    String XIUMM_BASE = "http://www.xiumm.org";

    String WWW94TAOTUCOM = "http://www.94taotu.com/albums/page-";
    String WWW94TAOTUCOM_BASE = "http://www.94taotu.com";

    String MZITU = "http://www.mzitu.com/page/";
    String MZITU_BASE = "http://www.mzitu.com";

    String MMONLY = "http://www.mmonly.cc/mmtp/list_9_";
    String MMONLY_BASE = "http://www.mmonly.cc";

    String ROSIYY = "http://www.rosiyy.com/index-";
    String ROSIYY_BASE = "http://www.rosiyy.com";

    String WWW192TTCOM_MT = "http://www.192tt.com/listinfo-1-";
    String WWW192TTCOM_GQ = "http://www.192tt.com/listinfo-34-";
    String WWW192TTCOM_BASE = "http://www.192tt.com";

    String XIUREN = "http://www.xiuren.org/page-";
    String XIUREN_BASE = "http://www.xiuren.org";

    String LINGYU = "http://www.lingyu.me/page/";
    String LINGYU_BASE = "http://www.lingyu.me";

    String MOEIMG = "http://moeimg.net/category/%E9%9D%9E%E3%82%A8%E3%83%AD%E3%83%BB%E5%BE%AE%E3%82%A8%E3%83%AD%E7%94%BB%E5%83%8F/page/";
    String MOEIMG_H = "http://moeimg.net/page/";
    String MOEIMG_BASE = "http://moeimg.net";

    String NIJIERO_CH = "https://nijiero-ch.com/page/";
    String NIJIERO_CH_BASE = "https://nijiero-ch.com";

    String COSPLAY_LA = "http://cosplay.la/photo/index/2-0-";
    String COSPLAY_LA_BASE = "http://cosplay.la";

    String CHINAGIRLOL_BASE = "http://www.chinagirlol.cc/";
    String CHINAGIRLOL_MN = "http://www.chinagirlol.cc/forum.php?mod=forumdisplay&fid=112&orderby=lastpost&orderby=lastpost&filter=lastpost&page=";
    String CHINAGIRLOL_MT = "http://www.chinagirlol.cc/forum.php?mod=forumdisplay&fid=66&orderby=lastpost&filter=lastpost&orderby=lastpost&page=";
    String CHINAGIRLOL_COS = "http://www.chinagirlol.cc/forum.php?mod=forumdisplay&fid=99&orderby=lastpost&filter=lastpost&orderby=lastpost&page=";

    String MOE005TV_MT = "http://moe.005.tv/moeimg/tb/list_3_";
    String MOE005TV_DNBZ = "http://moe.005.tv/moeimg/bz/list_4_";
    String MOE005TV_SJBZ = "http://moe.005.tv/moeimg/sjbz/list_601_";
    String MOE005TV_COSER = "http://moe.005.tv/cosplay/cosjs/list_600_";
    String MOE005TV_COS = "http://moe.005.tv/cosplay/cosxztj/list_598_";
    String MOE005TV_BASE = "http://moe.005.tv";

    String ACG_GAMERSKY_BASE = "http://acg.gamersky.com";
    String ACG_GAMERSKY_ZX = "http://db2.gamersky.com/LabelJsonpAjax.aspx?jsondata={\"type\":\"updatenodelabel\",\"isCache\":true,\"cacheTime\":60,\"nodeId\":\"20449\",\"isNodeId\":\"true\",\"page\":";
    String ACG_GAMERSKY_PC = "http://db2.gamersky.com/LabelJsonpAjax.aspx?jsondata={\"type\":\"updatenodelabel\",\"isCache\":true,\"cacheTime\":60,\"nodeId\":\"20450\",\"isNodeId\":\"true\",\"page\":";
    String ACG_GAMERSKY_SJ = "http://db2.gamersky.com/LabelJsonpAjax.aspx?jsondata={\"type\":\"updatenodelabel\",\"isCache\":true,\"cacheTime\":60,\"nodeId\":\"20451\",\"isNodeId\":\"true\",\"page\":";
    String ACG_GAMERSKY_MT = "http://db2.gamersky.com/LabelJsonpAjax.aspx?jsondata={\"type\":\"updatenodelabel\",\"isCache\":true,\"cacheTime\":60,\"nodeId\":\"20395\",\"isNodeId\":\"true\",\"page\":";
    String ACG_GAMERSKY_COS = "http://db2.gamersky.com/LabelJsonpAjax.aspx?jsondata={\"type\":\"updatenodelabel\",\"isCache\":true,\"cacheTime\":60,\"nodeId\":\"20397\",\"isNodeId\":\"true\",\"page\":";

    String PANGCI = "https://www.pangci.cc/works/index_";
    String PANGCI_BASE = "https://www.pangci.cc";

    String YURIIMG_NEW = "http://yuriimg.com/post/";
    String YURIIMG_RANDOM = "http://yuriimg.com/post/index/mode/random/p/";
    String YURIIMG_BASE = "http://yuriimg.com";
    String YURIIMG_LOGIN = "http://yuriimg.com/account/login";


    String MM131_BASE = "http://www.mm131.com";
    String MM131_XG = "http://www.mm131.com/xinggan/list_6_";
    String MM131_QC = "http://www.mm131.com/qingchun/list_1_";
    String MM131_XH = "http://www.mm131.com/xiaohua/list_2_";
    String MM131_CM = "http://www.mm131.com/chemo/list_3_";
    String MM131_QP = "http://www.mm131.com/qipao/list_4_";
    String MM131_XQ = "http://www.mm131.com/mingxing/list_5_";

    String MIAOWU_BASE = "http://www.miaowu.cc";
    String MIAOWU_SIWA = "http://www.miaowu.cc/meinv/siwa/index_";
    String MIAOWU_XGMN = "http://www.miaowu.cc/meinv/shuaige/index_";
    String MIAOWU_NYMN = "http://www.miaowu.cc/meinv/guonei/index_";
    String MIAOWU_DXMN = "http://www.miaowu.cc/meinv/rihan/index_";
    String MIAOWU_JRMV = "http://www.miaowu.cc/meinv/gangtai/index_";
    String MIAOWU_RBMN = "http://www.miaowu.cc/meinv/meinv/index_";
    String MIAOWU_OMMN = "http://www.miaowu.cc/meinv/oumei/index_";
    String MIAOWU_QCMN = "http://www.miaowu.cc/meinv/qingchun/index_";
    String MIAOWU_BRMN = "http://www.miaowu.cc/meinv/baoru/index_";
    String MIAOWU_QTMN = "http://www.miaowu.cc/meinv/qiaotun/index_";
    String MIAOWU_LTMN = "http://www.miaowu.cc/meinv/luoti/index_";
    String MIAOWU_CTMN = "http://www.miaowu.cc/meinv/changtui/index_";
    String MIAOWU_BJNMN = "http://www.miaowu.cc/meinv/bijini/index_";

    String MEIMEIZI = "http://www.meimeizi.com/page/";

    String YOUWU_BASE = "http://www.youwu.cc";
    String YOUWU_GUONEI = "http://www.youwu.cc/guonei/list_";
    String YOUWU_GANGTAI = "http://www.youwu.cc/gangtai/list_";

    String TAOTUTT = "http://taotutt.com/mztt/page_";
    String TAOTUTT_BASE = "http://taotutt.com";

    String UMEI = "http://www.umei.cc/meinvtupian/";

    String GRAVUREGIRLZ = "http://www.gravuregirlz.com/page/";
    String GRAVUREGIRLZ_BASE = "http://www.gravuregirlz.com";

}
