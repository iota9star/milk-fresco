package f.star.iota.milk.ui.gravuregirlz.girlz;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class GirlZFragment extends ScrollImageFragment<GirlZPresenter, GirlZAdapter> {

    public static GirlZFragment newInstance(String url) {
        GirlZFragment fragment = new GirlZFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected GirlZPresenter getPresenter() {
        return new GirlZPresenter(this);
    }

    @Override
    protected GirlZAdapter getAdapter() {
        return new GirlZAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }

}
