package f.star.iota.milk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.R;
import f.star.iota.milk.config.OtherConfig;
import f.star.iota.milk.util.SnackbarUtils;
import jp.wasabeef.recyclerview.animators.LandingAnimator;


public abstract class ScrollImageFragment<P extends PVContract.Presenter, A extends BaseAdapter> extends BaseFragment implements PVContract.View {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private boolean isRunning;
    private boolean isLoadMore;
    private int currentPage;

    private int mInitialPage;

    private A mAdapter;

    private String mBaseUrl;
    private P mPresenter;
    private StaggeredGridLayoutManager mLayoutManager;

    private int mSpanCount;
    private String mSuffix;
    private FloatingActionButton mFab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mBaseUrl = bundle.getString("base_url");
        mInitialPage = bundle.getInt("initial_page", 1);
        mSuffix = bundle.getString("page_suffix", "");
    }

    @Override
    protected void init() {
        create();
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
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new LandingAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void changeRecyclerViewSpanCount() {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount >= OtherConfig.getSpanCountConfig(aContext)) {
            spanCount = 1;
        } else {
            spanCount++;
        }
        OtherConfig.saveCurrentSpanCount(aContext, spanCount);
        mLayoutManager.setSpanCount(spanCount);
    }

    protected boolean isHideFab() {
        return true;
    }

    private void create() {
        mFab = ButterKnife.findById(getActivity(), R.id.floating_action_button);
        if (!isHideFab()) {
            mFab.setVisibility(View.VISIBLE);
            FloatingToolbar floatingToolbar = ButterKnife.findById(getActivity(), R.id.floating_toolbar);
            floatingToolbar.attachFab(mFab);
            floatingToolbar.attachRecyclerView(mRecyclerView);
        } else {
            mFab.setVisibility(View.GONE);
        }
        isLoadMore = false;
        isRunning = false;
        currentPage = mInitialPage;
        mSpanCount = OtherConfig.getCurrentSpanCount(aContext);
        mPresenter = getPresenter();
        mAdapter = getAdapter();
    }

    private void initRefreshLayout() {
        mRefreshLayout.autoRefresh();
        mRefreshLayout.setLoadmoreFinished(false);
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (isRunning) return;
                isRunning = true;
                isLoadMore = false;
                if (mAdapter != null) {
                    mAdapter.clear();
                }
                if (mPresenter != null) {
                    mPresenter.get(mBaseUrl + mInitialPage + mSuffix);
                }
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                if (isRunning) return;
                isRunning = true;
                isLoadMore = true;
                if (mPresenter != null) {
                    mPresenter.get(mBaseUrl + currentPage + mSuffix);
                }
            }
        });
    }


    @Override
    public void success(Object result) {
        try {
            isRunning = false;
            final List beans = (List) result;
            if (isLoadMore) {
                currentPage++;
                mRefreshLayout.finishLoadmore(true);
            } else {
                currentPage = mInitialPage + 1;
                mRefreshLayout.finishRefresh(true);
            }
            if (beans == null || beans.size() == 0) {
                SnackbarUtils.create(mContext, "本次获得正确数据 0 条，请继续");
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
            if (isLoadMore) {
                currentPage++;
                mRefreshLayout.finishLoadmore(false);
            } else {
                currentPage = mInitialPage + 1;
                mRefreshLayout.finishRefresh(false);
            }
            SnackbarUtils.create(mContext, error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        if (mFab != null) {
            mFab.setVisibility(View.GONE);
        }
    }
}