package com.damienjacques.cafesuspendu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.os.Bundle;

public class FragmentButtonMain extends Fragment
{
    View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        if (mainView != null)
        {
            ViewGroup parent = (ViewGroup)container.getParent();
            parent.removeView(container);
        }
        else
        {
            mainView = inflater.inflate(R.layout.activity_fragment_main, container, false);
        }

        return mainView;
    }
}
