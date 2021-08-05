package com.example.smalltalk16.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smalltalk16.Fragments.Chat_fragment;
import com.example.smalltalk16.Fragments.Invite_fragment;
import com.example.smalltalk16.Fragments.group_fragment;

public class Fragments_Adapter extends FragmentPagerAdapter {
    public Fragments_Adapter(@NonNull  FragmentManager fm) {
        super(fm);
    }

    public Fragments_Adapter(@NonNull  FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Chat_fragment();
            case 1: return new group_fragment();
            case 2: return new Invite_fragment();
            default: return new Chat_fragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = "CHATS";
        }
        if(position == 1){
            title = "GROUPS";
        }
        if(position == 2){
            title = "INVITES";
        }

        return title;
    }
}