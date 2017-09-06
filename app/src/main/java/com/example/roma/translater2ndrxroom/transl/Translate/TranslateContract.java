package com.example.roma.translater2ndrxroom.transl.Translate;

import com.example.roma.translater2ndrxroom.BasePresenter;
import com.example.roma.translater2ndrxroom.BaseView;


public interface TranslateContract {
    interface View extends BaseView<Presenter> {

        void showAnimSwitch(boolean lang);

        void showClearButton();

        void hideClearButton();

        void clearSearch();

        void unFocusSearchEdit();

        void insertWord(String word);

        void setWordIn(String word);

        void setWordOut(String word);

        void showProgress();

        void hideProgress();

        void showWords();

        void hideWords();

        void showFavourite();

        void hideFavourite();

        void setFavourite();

        void outsetFavourite();

    }

    interface Presenter extends BasePresenter {

        void switcherClick();

        void deleteSearch();

        void searchTranslate(String word);

        void setFavorite();
    }
}
