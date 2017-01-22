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

    private ViewGroup coachBaseScrollView;
    private View coachBaseView;
    private View coachTextView;

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
        this.coachTextView = coachTextView;
        addView(coachTextView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!changed) {
            return;
        }

        ViewGroup childScrollLayout = (ViewGroup) coachBaseScrollView.getChildAt(0);

        // Child ScrollLayout의 Class Instance Type을 알 수 없음으로 instanceof로 일단 체크하여 레이아웃의 크기를 변경해준다.
        // TODO. 다른 방법이 있는지 알아볼 필요가 있다.
        if (childScrollLayout instanceof FrameLayout) {
            setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, childScrollLayout.getHeight()));
        } else if (childScrollLayout instanceof RelativeLayout) {
            setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, childScrollLayout.getHeight()));
        }

        float rearrangePosX;
        float rearrangePosY;

        if (align == Align.LEFT) {
            rearrangePosX = coachBaseView.getX();
            rearrangePosY = coachBaseView.getY() - coachTextView.getHeight();

        } else if (align == Align.CENTER) {
            rearrangePosX = coachBaseView.getX() - ((coachTextView.getWidth() / 2) - (coachBaseView.getWidth() / 2));
            rearrangePosY = coachBaseView.getY() - coachTextView.getHeight();

        } else {
            rearrangePosX = coachBaseView.getX() - coachTextView.getWidth() + coachBaseView.getWidth();
            rearrangePosY = coachBaseView.getY() - coachTextView.getHeight();

        }

        coachTextView.setX(rearrangePosX);
        coachTextView.setY(rearrangePosY);

        Log.d("TEST", "[onLayout] coachBaseView: " + coachBaseView.getX() + ", " + coachBaseView.getY() + ", coachTextView: " + coachTextView);
    }

    public void setRootViewGroup(ViewGroup coachBaseScrollView) {
        this.coachBaseScrollView = coachBaseScrollView;
    }

    public void setTargetView(View targetView) {
        this.coachBaseView = targetView;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

}
