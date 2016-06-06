package com.bmutinda.chucknorris.views.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPagerAdapter extends SmartFragmentStatePagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    List<String> fragmentTitles = new ArrayList<>();

    public CategoriesPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        try{
            return fragments.get(position);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        try{
            return fragmentTitles.get(position);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}