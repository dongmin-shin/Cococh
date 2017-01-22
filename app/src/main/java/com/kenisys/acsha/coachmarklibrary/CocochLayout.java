package com.kenisys.acsha.coachmarklibrary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by DongMinShin on 2017. 1. 15..
 */

public class CocochLayout extends RelativeLayout {

    enum Align {
        LEFT,
        CENTER,
        RIGHT
    }

    private Align align = Align.CENTER;

    private ViewGroup rootViewGroup;
    private View targetView;
    private View coachMarkView;

    public CocochLayout(Context context) {
        super(context);
        init();
    }

    public CocochLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CocochLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CocochLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void addCoachTextView(View coachTextView) {
        this.coachMarkView = coachTextView;
        addView(coachTextView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!changed) {
            return;
        }

        ViewGroup attachTargetViewGroup = rootViewGroup;
        if (rootViewGroup instanceof ScrollView) {
            rootViewGroup = (ViewGroup) this.rootViewGroup.getChildAt(0);
        }

        // Child ScrollLayout의 Class Instance Type을 알 수 없음으로 instanceof로 일단 체크하여 레이아웃의 크기를 변경해준다.
        // TODO. 다른 방법이 있는지 알아볼 필요가 있다.
        if (attachTargetViewGroup instanceof FrameLayout) {
            setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, attachTargetViewGroup.getHeight()));
        } else if (attachTargetViewGroup instanceof RelativeLayout) {
            setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, attachTargetViewGroup.getHeight()));
        }

        float rearrangePosX;
        float rearrangePosY;

        if (align == Align.LEFT) {
            rearrangePosX = targetView.getX();
            rearrangePosY = targetView.getY() - coachMarkView.getHeight();

        } else if (align == Align.CENTER) {
            rearrangePosX = targetView.getX() - ((coachMarkView.getWidth() / 2) - (targetView.getWidth() / 2));
            rearrangePosY = targetView.getY() - coachMarkView.getHeight();

        } else {
            rearrangePosX = targetView.getX() - coachMarkView.getWidth() + targetView.getWidth();
            rearrangePosY = targetView.getY() - coachMarkView.getHeight();

        }

        Log.d("TEST", "rearrangePosX: " + rearrangePosX + ", rearrangePosY: " + rearrangePosY);

        coachMarkView.setX(rearrangePosX);
        coachMarkView.setY(rearrangePosY);

        Log.d("TEST", "[onLayout] targetView: " + targetView.getX() + ", " + targetView.getY() + ", coachMarkView: " + coachMarkView);
    }

    public void setRootViewGroup(ViewGroup coachBaseScrollView) {
        this.rootViewGroup = coachBaseScrollView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

}
