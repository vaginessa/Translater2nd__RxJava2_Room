package com.example.roma.translater2ndrxroom.transl;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.roma.translater2ndrxroom.R;
import com.example.roma.translater2ndrxroom.transl.FavouriteF.FavouriteF;
import com.example.roma.translater2ndrxroom.transl.HistoryF.History;
import com.example.roma.translater2ndrxroom.transl.Translate.TranslateF;
import com.example.roma.translater2ndrxroom.util.ActivityUtils;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    MainActivityContract.Presenter presenter;


    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNav();
        initFragmentContainer();

        TranslateF fragment = (TranslateF) getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new TranslateF(), R.id.container);
        }
        presenter = new MainActivityPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initFragmentContainer() {

    }


    private void initBottomNav() {
        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.history:
                        presenter.chooseHistoryFragment();
                        break;
                    case R.id.favorite:
                        presenter.chooseFavoriteFragment();
                        break;
                    case R.id.translater:
                        presenter.chooseTranslateFragment();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTranslateFragment() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new TranslateF(), R.id.container);
    }

    @Override
    public void showHistoryFragment() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new History(), R.id.container);
    }

    @Override
    public void showFavoriteFragment() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new FavouriteF(), R.id.container);
    }
}
