package f.star.iota.milk.ui.gravuregirlz.gravure;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class GravureGirlZFragment extends ScrollImageFragment<GravureGirlZPresenter, GravureGirlZAdapter> {


    public static GravureGirlZFragment newInstance(String url) {
        GravureGirlZFragment fragment = new GravureGirlZFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected GravureGirlZPresenter getPresenter() {
        return new GravureGirlZPresenter(this);
    }

    @Override
    protected GravureGirlZAdapter getAdapter() {
        return new GravureGirlZAdapter();
    }
}
