package f.star.iota.milk.ui.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.irozon.sneaker.Sneaker;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.Url;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.base.RVBean;
import f.star.iota.milk.ui.download.DownloadManagerActivity;
import f.star.iota.milk.ui.menu.MenuCosplayFragment;
import f.star.iota.milk.ui.menu.MenuIllustrationFragment;
import f.star.iota.milk.ui.menu.MenuMeiziFragment;
import f.star.iota.milk.ui.menu.MenuPhotographyFragment;
import f.star.iota.milk.ui.moeimg.moe.MoeimgFragment;
import f.star.iota.milk.ui.more.MoreActivity;
import f.star.iota.milk.ui.settings.SettingsActivity;
import f.star.iota.milk.ui.theme.ThemeActivity;
import f.star.iota.milk.util.ConfigUtils;
import f.star.iota.milk.util.SnackbarUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;


    private Drawer drawer;

    private MainActivityPresenter mPresenter;

    private boolean isRunning;
    private TextView mTextViewHitokoto;
    private TextView mTextViewHitokotoSrc;
    private BaseFragment currentFragment;
    private CloseableReference<CloseableImage> mCloseableImageCloseableReference;


    @Override
    protected void init(Bundle savedInstanceState) {
        init();
        initDrawer();
        initDrawerEvent();
        checkPermission();
    }

    private void checkPermission() {
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean granted) throws Exception {
                        if (!granted) {
                            SnackbarUtils.create(mContext, "您拒绝了写入文件的权限，下载可能会出现错误，是否立刻前往开启", "好的", new Sneaker.OnSneakerClickListener() {
                                @Override
                                public void onSneakerClick(View view) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private void init() {
        setSupportActionBar(mToolbar);
        mPresenter = new MainActivityPresenter(this);
        isRunning = false;
    }

    @Override
    protected void setFirstFragment() {
        if (!ConfigUtils.getR(aContext)) {
            currentFragment = MoeimgFragment.newInstance(Url.MOEIMG_H);
        } else {
            currentFragment = MoeimgFragment.newInstance(Url.MOEIMG);
        }
        showFragment(currentFragment);
        setTitle(Menus.MENU_MOEIMG);
    }

    private void initDrawer() {
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withHeader(R.layout.drawer_header_view)
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        if (isRunning) return;
                        isRunning = true;
                        mPresenter.get();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(Menus.MENU_ILLUSTRATION).withIdentifier(Menus.MENU_ILLUSTRATION_ID).withIcon(R.drawable.ic_menu_illustration).withSelectable(false),
                        new PrimaryDrawerItem().withName(Menus.MENU_MEIZI).withIdentifier(Menus.MENU_MEIZI_ID).withIcon(R.drawable.ic_menu_meizhi).withSelectable(false),
                        new PrimaryDrawerItem().withName(Menus.MENU_COSPLAY).withIdentifier(Menus.MENU_COSPLAY_ID).withIcon(R.drawable.ic_menu_cosplay).withSelectable(false),
                        new PrimaryDrawerItem().withName(Menus.MENU_PHOTOGRAPHY).withIdentifier(Menus.MENU_PHOTOGRAPHY_ID).withIcon(R.drawable.ic_menu_photography).withSelectable(false),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(Menus.MENU_THEME).withIdentifier(Menus.MENU_THEME_ID).withIcon(R.drawable.ic_menu_theme).withSelectable(false),
                        new PrimaryDrawerItem().withName(Menus.MENU_SETTINGS).withIdentifier(Menus.MENU_SETTINGS_ID).withIcon(R.drawable.ic_menu_settings).withSelectable(false),
                        new PrimaryDrawerItem().withName(Menus.MENU_ABOUT).withIdentifier(Menus.MENU_ABOUT_ID).withIcon(R.drawable.ic_menu_more).withSelectable(false)
                )
                .withSelectedItem(-1)
                .build();
        View header = drawer.getHeader();
        final KenBurnsView banner = header.findViewById(R.id.ken_burns_view_banner);
        String url = ConfigUtils.getBanner(mContext);
        try {
            if (url != null) {
                Uri uri = Uri.parse(url);
                if (uri != null) {
                    ImageRequest imageRequest = ImageRequestBuilder
                            .newBuilderWithSource(uri)
                            .build();
                    ImagePipeline imagePipeline = Fresco.getImagePipeline();
                    DataSource<CloseableReference<CloseableImage>>
                            dataSource = imagePipeline.fetchDecodedImage(imageRequest, null);
                    dataSource.subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                        @Override
                        protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                            mCloseableImageCloseableReference = dataSource.getResult();
                            if (mCloseableImageCloseableReference != null) {
                                CloseableImage image = mCloseableImageCloseableReference.get();
                                if (image instanceof CloseableBitmap) {
                                    CloseableBitmap closeableBitmap = (CloseableBitmap) image;
                                    Bitmap bitmap = closeableBitmap.getUnderlyingBitmap();
                                    if (bitmap == null) return;
                                    banner.setImageBitmap(bitmap);
                                }
                            }
                        }

                        @Override
                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {

                        }
                    }, UiThreadImmediateExecutorService.getInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTextViewHitokoto = header.findViewById(R.id.text_view_hitokoto_bilibilijj);
        mTextViewHitokotoSrc = header.findViewById(R.id.text_view_hitokoto_source);
    }

    private void initDrawerEvent() {
        drawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem dItem) {
                switch ((int) dItem.getIdentifier()) {
                    case Menus.MENU_THEME_ID:
                        startActivity(new Intent(mContext, ThemeActivity.class));
                        break;
                    case Menus.MENU_SETTINGS_ID:
                        startActivity(new Intent(mContext, SettingsActivity.class));
                        break;
                    case Menus.MENU_ABOUT_ID:
                        startActivity(new Intent(mContext, MoreActivity.class));
                        break;
                    case Menus.MENU_ILLUSTRATION_ID:
                        currentFragment = new MenuIllustrationFragment();
                        setTitle(Menus.MENU_ILLUSTRATION);
                        break;
                    case Menus.MENU_MEIZI_ID:
                        currentFragment = new MenuMeiziFragment();
                        setTitle(Menus.MENU_MEIZI);
                        break;
                    case Menus.MENU_COSPLAY_ID:
                        currentFragment = new MenuCosplayFragment();
                        setTitle(Menus.MENU_COSPLAY);
                        break;
                    case Menus.MENU_PHOTOGRAPHY_ID:
                        currentFragment = new MenuPhotographyFragment();
                        setTitle(Menus.MENU_PHOTOGRAPHY);
                        break;
                }
                showFragment(currentFragment);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawer != null) {
            drawer.closeDrawer();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download_manager:
                startActivity(new Intent(mContext, DownloadManagerActivity.class));
                break;
            case R.id.action_change_span_count:
                EventBus.getDefault().postSticky(new RVBean(true, false));
                break;
            case R.id.action_touch_to_top:
                EventBus.getDefault().postSticky(new RVBean(false, true));
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        if (mCloseableImageCloseableReference != null) {
            CloseableReference.closeSafely(mCloseableImageCloseableReference);
            mCloseableImageCloseableReference = null;
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.frame_layout_fragment_container;
    }

    @Override
    public void getSuccess(JuZiBean juzi) {
        bindHitokoto(juzi);
        isRunning = false;
    }

    private void bindHitokoto(JuZiBean bean) {
        mTextViewHitokoto.setText(bean.getHitokoto());
        if (mTextViewHitokoto.getLineCount() > 1) {
            mTextViewHitokoto.setText(String.format("\u3000\u3000%s", bean.getHitokoto()));
        }
        mTextViewHitokotoSrc.setText(bean.getSource());
    }

    @Override
    public void onBackPressed() {
        exit();
    }
}
