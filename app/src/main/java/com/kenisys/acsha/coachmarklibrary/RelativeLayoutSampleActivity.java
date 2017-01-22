package com.kenisys.acsha.coachmarklibrary;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RelativeLayoutSampleActivity extends AppCompatActivity {

    private View targetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout_sample);

        targetView = findViewById(R.id.target_view);

        // 정상 동작
        Cococh cococh = new Cococh(this);
        cococh.setRootViewGroup(getRootViewGroup());
        cococh.setTargetView(targetView);
        cococh.setCoachMarkView(getCoachTextView());
        cococh.setAlign(CocochLayout.Align.CENTER);

        cococh.build();
        cococh.show();
    }

    private View getCoachTextView() {
        TextView coachMarkView = new TextView(this);
        coachMarkView.setText("Hello? This is Cococh library sample");
        coachMarkView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            coachMarkView.setBackground(getResources().getDrawable(R.drawable.bubble, null));
        } else {
            coachMarkView.setBackground(getResources().getDrawable(R.drawable.bubble));
        }

        return coachMarkView;
    }

    private ViewGroup getRootViewGroup() {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        return viewGroup;
    }


}
