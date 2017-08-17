package f.star.iota.milk.ui.theme;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.nightfarmer.themer.Themer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseActivity;
import f.star.iota.milk.config.ThemeConfig;
import f.star.iota.milk.util.SnackbarUtils;

public class ThemeActivity extends BaseActivity {

    private static boolean isExpand = true;
    @BindView(R.id.ken_burns_view_banner)
    KenBurnsView mKenBurnsViewBanner;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.button_set)
    Button mButtonSet;
    @BindView(R.id.edit_text_url)
    EditText mEditTextUrl;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    private CloseableReference<CloseableImage> mCloseableImageCloseableReference;

    @OnClick(R.id.button_set)
    public void onClick() {
        String url = mEditTextUrl.getText().toString().trim().replace(" ", "");
        if (url.length() > 5) {
            ThemeConfig.saveBanner(aContext, url);
            SnackbarUtils.create(this, "已保存地址");
            loadBanner(url);
        } else {
            SnackbarUtils.create(this, "地址太短了吧");
        }
    }

    private void loadBanner(String url) {
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
                                    mKenBurnsViewBanner.setImageBitmap(bitmap);
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
    }

    @Override
    protected void init() {
        if (!isExpand) {
            mAppBarLayout.setExpanded(false);
        }
        create();
        initAppBarLayoutEvent();
        initRecyclerView();
        loadBanner(ThemeConfig.getBanner(aContext));
    }

    private void initAppBarLayoutEvent() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                isExpand = appBarLayout.getTotalScrollRange() + verticalOffset != 0;
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ThemeAdapter adapter = new ThemeAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.add(initTheme());
        adapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int theme) {
                ThemeConfig.saveTheme(aContext, theme);
                Themer.INSTANCE.setThemeSoft(ThemeActivity.this, theme, null);
            }
        });
    }

    private List<ThemeBean> initTheme() {
        List<ThemeBean> themes = new ArrayList<>();
        themes.add(new ThemeBean(R.style.ThemeRed, R.color.ThemeRed, "红色/Red", false));
        themes.add(new ThemeBean(R.style.ThemePink, R.color.ThemePink, "粉色/Pink", false));
        themes.add(new ThemeBean(R.style.ThemePurple, R.color.ThemePurple, "紫色/Purple", false));
        themes.add(new ThemeBean(R.style.ThemeDeepPurple, R.color.ThemeDeepPurple, "深紫/Deep Purple", false));
        themes.add(new ThemeBean(R.style.ThemeIndigo, R.color.ThemeIndigo, "靛蓝/Indigo", false));
        themes.add(new ThemeBean(R.style.ThemeBlue, R.color.ThemeBlue, "蓝色/Blue", false));
        themes.add(new ThemeBean(R.style.ThemeLightBlue, R.color.ThemeLightBlue, "亮蓝/Light Blue", false));
        themes.add(new ThemeBean(R.style.ThemeCyan, R.color.ThemeCyan, "青色/Cyan", false));
        themes.add(new ThemeBean(R.style.ThemeTeal, R.color.ThemeTeal, "鸭绿/Teal", false));
        themes.add(new ThemeBean(R.style.ThemeGreen, R.color.ThemeGreen, "绿色/Green", false));
        themes.add(new ThemeBean(R.style.ThemeLightGreen, R.color.ThemeLightGreen, "亮绿/Light Green", false));
        themes.add(new ThemeBean(R.style.ThemeLime, R.color.ThemeLime, "酸橙/Lime", false));
        themes.add(new ThemeBean(R.style.ThemeYellow, R.color.ThemeYellow, "黄色/Yellow", false));
        themes.add(new ThemeBean(R.style.ThemeAmber, R.color.ThemeAmber, "琥珀/Amber", false));
        themes.add(new ThemeBean(R.style.ThemeOrange, R.color.ThemeOrange, "橙色/Orange", false));
        themes.add(new ThemeBean(R.style.ThemeDeepOrange, R.color.ThemeDeepOrange, "暗橙/DeepOrange", false));
        themes.add(new ThemeBean(R.style.ThemeBrown, R.color.ThemeBrown, "棕色/Brown", false));
        themes.add(new ThemeBean(R.style.ThemeGrey, R.color.ThemeGrey, "灰色/Grey", false));
        themes.add(new ThemeBean(R.style.ThemeBlueGrey, R.color.ThemeBlueGrey, "蓝灰/Blue Grey", false));
        themes.add(new ThemeBean(R.style.ThemeBiliBili, R.color.ThemeBiliBili, "哔哩哔哩/BiliBili", false));
        themes.add(new ThemeBean(R.style.ThemeBlack, R.color.ThemeBlack, "黑色/Black", false));
        themes.add(new ThemeBean(R.style.ThemeDarkBlack, R.color.ThemeDarkBlack, "夜间/Night", false));

        for (ThemeBean theme : themes) {
            if (theme.getTheme() == ThemeConfig.getTheme(aContext)) {
                theme.setSelected(true);
            }
        }
        return themes;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCloseableImageCloseableReference != null) {
            CloseableReference.closeSafely(mCloseableImageCloseableReference);
            mCloseableImageCloseableReference = null;
        }
    }

    private void create() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_theme;
    }
}
