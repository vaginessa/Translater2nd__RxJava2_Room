package com.example.roma.translater2ndrxroom.transl;


import com.example.roma.translater2ndrxroom.util.ActivityUtils;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    MainActivityContract.View translateView;

    public MainActivityPresenter(MainActivityContract.View translateView) {
        this.translateView = translateView;
        translateView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void chooseTranslateFragment() {
        translateView.showTranslateFragment();
    }

    @Override
    public void chooseHistoryFragment() {
        translateView.showHistoryFragment();
    }

    @Override
    public void chooseFavoriteFragment() {
        translateView.showFavoriteFragment();
    }
}
