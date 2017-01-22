package com.kenisys.acsha.coachmarklibrary;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DongMinShin on 2017. 1. 22..
 */

public class Cococh {

    private static final String TAG = "Cococh";

    private final Context context;

    private ViewGroup rootViewGroup;
    private View targetView;
    private View coachMarkView;
    private CocochLayout.Align align;

    private CocochLayout cocochLayout;

    public Cococh(Context context) {
        this.context = context;
    }

    public void setRootViewGroup(ViewGroup rootViewGroup) {
        this.rootViewGroup = rootViewGroup;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public void setCoachMarkView(View coachMarkView) {
        this.coachMarkView = coachMarkView;
    }

    public void setAlign(CocochLayout.Align align) {
        this.align = align;
    }

    public void build() {
        if (rootViewGroup == null || targetView == null || coachMarkView == null) {
            Log.e(TAG, "rootViewGroup: " + rootViewGroup + ", targetView: " + targetView + ", coachMarkView: " + coachMarkView);
            return;
        }

        cocochLayout = new CocochLayout(context);
        cocochLayout.setRootViewGroup(rootViewGroup);
        cocochLayout.setTargetView(targetView);
        cocochLayout.setAlign(align);

        cocochLayout.addCoachTextView(coachMarkView);
    }

    public void show() {
        ViewGroup viewGroup = (ViewGroup) rootViewGroup.getChildAt(0);
        viewGroup.addView(cocochLayout);
    }


}
