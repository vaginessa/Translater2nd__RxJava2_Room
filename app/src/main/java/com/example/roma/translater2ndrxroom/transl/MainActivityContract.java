package com.example.roma.translater2ndrxroom.transl;

import com.example.roma.translater2ndrxroom.BasePresenter;
import com.example.roma.translater2ndrxroom.BaseView;

/**
 * Created by Roma on 24.08.2017.
 */

public interface MainActivityContract {
    interface View extends BaseView<Presenter> {

        void showTranslateFragment();

        void showHistoryFragment();

        void showFavoriteFragment();

    }

    interface Presenter extends BasePresenter {

        void chooseTranslateFragment();

        void chooseHistoryFragment();

        void chooseFavoriteFragment();
    }
}
