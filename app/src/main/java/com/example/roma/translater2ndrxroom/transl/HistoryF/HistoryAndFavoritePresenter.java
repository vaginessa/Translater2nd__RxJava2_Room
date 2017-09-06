package com.example.roma.translater2ndrxroom.transl.HistoryF;

import android.util.Log;

import com.example.roma.translater2ndrxroom.data.TranslateItem;
import com.example.roma.translater2ndrxroom.data.source.Repository;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;


public class HistoryAndFavoritePresenter implements HistoryAndFavoriteContract.Presenter {

    private HistoryAndFavoriteContract.View view;
    private CompositeDisposable disposable = new CompositeDisposable();

    private Repository repository;

    List<TranslateItem> listDelete;

    public HistoryAndFavoritePresenter(HistoryAndFavoriteContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }


    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }

    @Override
    public void deleteItemsClear() {

    }

    @Override
    public void loadDataAll() {
        disposable.add(repository.getAllItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TranslateItem>>() {
                    @Override
                    public void accept(@NonNull List<TranslateItem> translateItems) throws Exception {
                        showData(translateItems);
                    }
                }));

    }

    @Override
    public void loadDataFavourite() {
        disposable.add(repository.getAllItems()
                .subscribeOn(Schedulers.io())
                .flatMapObservable(new Function<List<TranslateItem>, ObservableSource<TranslateItem>>() {
                    @Override
                    public ObservableSource<TranslateItem> apply(@NonNull List<TranslateItem> translateItems) throws Exception {
                        return Observable.fromIterable(translateItems);
                    }
                })
                .filter(new Predicate<TranslateItem>() {
                    @Override
                    public boolean test(@NonNull TranslateItem translateItem) throws Exception {
                        return translateItem.isFavorite();
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TranslateItem>>() {
                    @Override
                    public void accept(@NonNull List<TranslateItem> translateItems) throws Exception {
                        showData(translateItems);
                    }
                }));
    }

    @Override
    public void favoriteClick(final TranslateItem item) {
        item.changeFavourite();

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                //for db dont write deleteStatus
                if (item.isDelete()) {
                    item.setDelete(false);
                    repository.setUnsetFavoutite(item);
                    item.setDelete(true);
                } else
                    repository.setUnsetFavoutite(item);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.updateData();
                        Log.v("tesfaega", item.isFavorite() + "testing");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    @Override
    public void itemChoose(int id) {

    }

    @Override
    public void clcikTest() {

    }

    @Override
    public void searchEdit(final String word) {
        setStateClearSearchImage(word);

        // // TODO: androidschedulers ???
        disposable.add(Single.just(word)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s.toLowerCase();
                    }
                })
                .flatMap(new Function<String, SingleSource<List<TranslateItem>>>() {
                    @Override
                    public SingleSource<List<TranslateItem>> apply(@NonNull String s) throws Exception {
                        return repository.searchLocal(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TranslateItem>>() {
                    @Override
                    public void accept(@NonNull List<TranslateItem> translateItems) throws Exception {
                        showData(translateItems);
                    }
                }));
    }

    @Override
    public void searchEditFavourite(String word) {
        setStateClearSearchImage(word);

        disposable.add(Single.just(word).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return s.toLowerCase();
            }
        })
                .flatMap(new Function<String, SingleSource<List<TranslateItem>>>() {
                    @Override
                    public SingleSource<List<TranslateItem>> apply(@NonNull String s) throws Exception {
                        return repository.searchLocalFavourite(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TranslateItem>>() {
                    @Override
                    public void accept(@NonNull List<TranslateItem> translateItems) throws Exception {
                        showData(translateItems);
                    }
                }));
    }

    private void setStateClearSearchImage(String word) {
        if (word.length() != 0) {
            view.showClearSearchImage();
        } else view.hideClearSearchImage();
    }

    @Override
    public void clearSearch() {
        view.clearSearch();
    }


    @Override
    public void checkListDelete(List<TranslateItem> list) {
        this.listDelete = list;
        if (list.size() != 0)
            view.setEnabledGarbage(true);
        else
            view.setEnabledGarbage(false);
    }

    @Override
    public void clickDeleteButtons() {
        view.showDialogDelete();
    }

    @Override
    public void deleteItems() {
        disposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                repository.deleteItems(listDelete);
                view.updateDataWithoutDelete();
                view.setEnabledGarbage(false);
            }
        }).subscribe());

    }

    @Override
    public void showData(List<TranslateItem> list) {
        view.updateData(list);
    }
}
