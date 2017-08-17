package f.star.iota.milk.ui.menu;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.base.SGSpacingItemDecoration;
import f.star.iota.milk.config.OtherConfig;

public abstract class MenuFragment extends BaseFragment {
    private final MenuAdapter.OnMenuItemClickListener mOnMenuItemClickListener = new MenuAdapter.OnMenuItemClickListener() {
        @Override
        public void onClick(MenuBean menu) {
            handleOnMenuItemOnClick(menu);
        }
    };
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private SGSpacingItemDecoration mItemDecoration;
    private StaggeredGridLayoutManager mLayoutManager;
    private int mSpanCount;

    protected abstract void handleOnMenuItemOnClick(MenuBean menu);

    @Override
    protected void init() {
        create();
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }

    private void initRecyclerView() {
        mLayoutManager = new StaggeredGridLayoutManager(mSpanCount, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mItemDecoration = new SGSpacingItemDecoration(mSpanCount, mContext.getResources().getDimensionPixelSize(R.dimen.v4dp));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mItemDecoration);
        MenuAdapter adapter = new MenuAdapter();
        adapter.add(getMenuList());
        adapter.setOnMenuItemClickListener(mOnMenuItemClickListener);
        mRecyclerView.setAdapter(adapter);
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
        mItemDecoration.setSpanCount(spanCount);
    }

    private void create() {
        mSpanCount = OtherConfig.getCurrentSpanCount(aContext);
    }

    protected abstract List<MenuBean> getMenuList();
}