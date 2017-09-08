package com.example.roma.translater2ndrxroom.transl.Translate;


import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.roma.translater2ndrxroom.R;
import com.example.roma.translater2ndrxroom.util.Injection;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateF extends Fragment implements TranslateContract.View {

    private TranslateContract.Presenter presenter;

    TextView headerTextOut;

    TextView headerTextIn;

    TextView translateWordIn;

    TextView translateWordOut;

    private ImageView error;

    private ImageView favorite;

    private EditText searchEditText;

    private ImageButton clearSearch;

    private ProgressBar progressBar;


    public TranslateF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
//        initHeaderSwitch(view);

        presenter = new TranslatePresenter(Injection.provideRepository(getContext().getApplicationContext()), this, getContext());

        initToolbar(view);
        initSearchField(view);
        initTranslateField(view);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    private void initTranslateField(View view) {
        translateWordIn = (TextView) view.findViewById(R.id.translate_word_in);
        translateWordOut = (TextView) view.findViewById(R.id.translate_word_out);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        error = (ImageView) view.findViewById(R.id.error);
        favorite = (ImageView) view.findViewById(R.id.favorite_word_translate);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setFavorite();
            }
        });
    }

    private void initSearchField(View view) {
        clearSearch = (ImageButton) view.findViewById(R.id.clear_search_translateF);

        searchEditText = (EditText) view.findViewById(R.id.searchTranslate);
        searchEditText.addTextChangedListener(new TextWatcher() {
            CountDownTimer timer;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                presenter.searchTranslate(s.toString());
            }
        });

        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteSearch();
            }
        });
    }

    private void initToolbar(View view) {
        headerTextIn = (TextView) view.findViewById(R.id.header_text_in);
        headerTextOut = (TextView) view.findViewById(R.id.header_text_out);
        ImageButton imageSwitch = (ImageButton) view.findViewById(R.id.image_switch);

        imageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switcherClick();
            }
        });
    }

    @Override
    public void setPresenter(TranslateContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showAnimSwitch(boolean lang) {
        if (lang) {
            headerTextIn.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.animate().translationX(50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.setText(R.string.lang_ru);
                    headerTextIn.animate().translationX(0).alpha(1);
                }
            });
            headerTextOut.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.animate().translationX(-50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.setText(R.string.lang_en);
                    headerTextOut.animate().translationX(0).alpha(1);
                }
            });
        } else {
            headerTextIn.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.animate().translationX(50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextIn.setText(R.string.lang_en);
                    headerTextIn.animate().translationX(0).alpha(1);
                }
            });
            headerTextOut.animate().alpha(0).withStartAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.animate().translationX(-50);

                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    headerTextOut.setText(R.string.lang_ru);
                    headerTextOut.animate().translationX(0).alpha(1);
                }
            });
        }

    }

    @Override
    public void showClearButton() {
        clearSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideClearButton() {
        clearSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearSearch() {
        searchEditText.getText().clear();
    }

    @Override
    public void unFocusSearchEdit() {
        searchEditText.clearFocus();
        searchEditText.setEnabled(false);
        searchEditText.setEnabled(true);
    }

    @Override
    public void insertWord(String word) {
        translateWordOut.setText(word);
    }

    @Override
    public void setWordIn(String word) {
        translateWordIn.setText(word);
    }

    @Override
    public void setWordOut(String word) {
        translateWordOut.setText(word);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showWords() {
        translateWordIn.setVisibility(View.VISIBLE);
        translateWordOut.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWords() {
        translateWordIn.setVisibility(View.INVISIBLE);
        translateWordOut.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showFavourite() {
        favorite.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFavourite() {
        favorite.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setStateFavourite(boolean state) {
        if (state)
            favorite.setImageResource(R.drawable.ic_favorite);
        else favorite.setImageResource(R.drawable.ic_inactive_favorite);
    }

    @Override
    public void showErrorMessage() {
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        error.setVisibility(View.INVISIBLE);
    }


}