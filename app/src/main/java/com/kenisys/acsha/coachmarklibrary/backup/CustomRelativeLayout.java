package com.kenisys.acsha.coachmarklibrary.backup;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kenisys.acsha.coachmarklibrary.R;

/**
 * Created by DongMinShin on 2017. 1. 15..
 */

public class CustomRelativeLayout extends RelativeLayout {

    enum Align {
        LEFT,
        CENTER,
        RIGHT
    }

    private Align align = Align.CENTER;

    private ScrollView coachBaseScrollView;
    private View coachBaseView;
    private TextView coachTextView;

    public CustomRelativeLayout(Context context) {
        super(context);
        init();
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
            setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, childScrollLayout.getHeight()));
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

    public void setRootViewGroup(ScrollView coachBaseScrollView) {
        this.coachBaseScrollView = coachBaseScrollView;
    }

    public void setTargetView(View targetView) {
        this.coachBaseView = targetView;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public void addCoachTextView() {
        setBackgroundColor(Color.parseColor("#33ffbbcc"));

        coachTextView = new TextView(getContext());
        coachTextView.setText("Hello World addview123");
        coachTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            coachTextView.setBackground(getResources().getDrawable(R.drawable.bubble, null));
        } else {
            coachTextView.setBackground(getResources().getDrawable(R.drawable.bubble));
        }

        addView(coachTextView);
    }
}
