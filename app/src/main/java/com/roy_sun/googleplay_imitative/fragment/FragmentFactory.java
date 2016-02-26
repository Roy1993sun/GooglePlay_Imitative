package com.roy_sun.googleplay_imitative.fragment;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by Roy_Sun on 2016/2/10 0010.
 */
public class FragmentFactory {

//    private static Map<Integer, Fragment> mCatch;
    private static SparseArrayCompat<LoadDataFragment> mCatch = new SparseArrayCompat();

    public static LoadDataFragment getFragment(int position) {

        LoadDataFragment fragment = (LoadDataFragment) mCatch.get(position);

        if (fragment != null) {
            return fragment;
        }

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new AppFragment();
                break;
            case 2:
                fragment = new GameFragment();
                break;
            case 3:
                fragment = new HomeFragment();
                break;
            case 4:
                fragment = new HomeFragment();
                break;
            case 5:
                fragment = new HomeFragment();
                break;
            case 6:
                fragment = new HomeFragment();
                break;

        }

         mCatch.put(position, fragment);
        return fragment;
    }

}
