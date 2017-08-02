package f.star.iota.milk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.util.ConfigUtils;
import f.star.iota.milk.util.SnackbarUtils;
import jp.wasabeef.recyclerview.animators.LandingAnimator;


public abstract class FixedImageFragment<P extends PVContract.Presenter, A extends BaseAdapter> extends BaseFragment implements PVContract.View {


    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private boolean isRunning;

    private A mAdapter;
    private String mBaseUrl;
    private P mPresenter;
    private SGSpacingItemDecoration mItemDecoration;
    private StaggeredGridLayoutManager mLayoutManager;

    private int mSpanCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mBaseUrl = bundle.getString("base_url");
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        init();
        initRecyclerView();
        initRefreshLayout();
    }


    protected abstract P getPresenter();

    protected abstract A getAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view_with_refresh_layout;
    }

    private void initRecyclerView() {
        mLayoutManager = new StaggeredGridLayoutManager(mSpanCount, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mItemDecoration = new SGSpacingItemDecoration(mSpanCount, mContext.getResources().getDimensionPixelSize(R.dimen.v4dp));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new LandingAnimator());
        mRecyclerView.removeItemDecoration(mItemDecoration);
        mRecyclerView.addItemDecoration(mItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void changeRecyclerViewSpanCount() {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount >= ConfigUtils.getSpanCountConfig(aContext)) {
            spanCount = 1;
        } else {
            spanCount++;
        }
        ConfigUtils.saveCurrentSpanCount(aContext, spanCount);
        mLayoutManager.setSpanCount(spanCount);
        mItemDecoration.setSpanCount(spanCount);
    }

    private void init() {
        isRunning = false;
        mSpanCount = ConfigUtils.getCurrentSpanCount(aContext);
        mPresenter = getPresenter();
        mAdapter = getAdapter();
    }

    private void initRefreshLayout() {
        mRefreshLayout.autoRefresh();
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (isRunning) return;
                isRunning = true;
                if (mAdapter != null) {
                    mAdapter.clear();
                }
                if (mPresenter != null) {
                    mPresenter.get(mBaseUrl);
                }
            }
        });
    }


    @Override
    public void success(Object result) {
        try {
            isRunning = false;
            mRefreshLayout.finishRefresh(true);
            final List beans = (List) result;
            if (beans == null || beans.size() == 0) {
                SnackbarUtils.create(mContext, "本次获得数据0条，请刷新");
            } else {
                mRefreshLayout.postDelayed(new Runnable() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void run() {
                        mAdapter.add(beans);
                    }
                }, 360);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void error(String error) {
        try {
            isRunning = false;
            mRefreshLayout.finishRefresh(false);
            SnackbarUtils.create(mContext, error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }
}