package com.example.roma.translater2ndrxroom.transl.HistoryF;

import com.example.roma.translater2ndrxroom.BasePresenter;
import com.example.roma.translater2ndrxroom.BaseView;
import com.example.roma.translater2ndrxroom.data.TranslateItem;

import java.util.List;

/**
 * Created by Roma on 24.08.2017.
 */

public interface HistoryAndFavoriteContract {

    interface View extends BaseView<Presenter> {

        void showToast();

        void updateData();

        void updateDataWithoutDelete();

        void updateData(List<TranslateItem> list);

        void setEnabledGarbage(boolean state);

        void showClearSearchImage();

        void hideClearSearchImage();

        void clearSearch();

        void clearSearchUnFocusable();

        void showDialogDelete();

        void unFocusSearch();


    }

    interface Presenter extends BasePresenter {

        void subscribe();

        void unsubscribe();

        void deleteItemsClear();

        void loadDataAll();

        void loadDataFavourite();

        void showData(List<TranslateItem> list);

        void favoriteClick(TranslateItem item);

        void itemChoose(int id);

        void clcikTest();

        void searchEdit(String word);

        void searchEditFavourite(String word);

        void clearSearch();

        void checkListDelete(List<TranslateItem> list);

        void clickDeleteButtons();

        void deleteItems();


    }

    interface TranslateItemListener {

        void onTranslateItemClick(TranslateItem item);

        void onTranslateFavoriteClick(TranslateItem item);

        void sendListDelete(List<TranslateItem> listDelete);
    }
}
