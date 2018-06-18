package com.trivagomedia.app.Base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jitendra Soam on 1/7/16.
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
    }

    public void printLog(String s) {
        // display a message in Log File
        Log.d("LifeCycle:", s);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        printLog("onActivityCreated Called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        printLog("onCreateView Called");
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        printLog("onViewCreated Called");

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        printLog("onAttach Called");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLog("onCreate Called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        printLog("onDestroy Called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        printLog("onDestroyView Called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        printLog("onDetach Called");
    }

    @Override
    public void onPause() {
        super.onPause();
        printLog("onPause Called");
    }

    @Override
    public void onResume() {
        super.onResume();
        printLog("onResume Called");
    }

    @Override
    public void onStart() {
        super.onStart();
        printLog("onStart Called");
    }

    @Override
    public void onStop() {
        super.onStop();
        printLog("onStop Called");
    }

    public abstract int getFragmentLayout();
}
