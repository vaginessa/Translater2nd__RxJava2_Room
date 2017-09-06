package com.example.roma.translater2ndrxroom.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class ActivityUtils {

    public static void addFragmentToActivity (FragmentManager fragmentManager, Fragment fragment, int frameId) {
        fragmentManager
                .beginTransaction()
                .replace(frameId,fragment)
                .commit();
    }
}
