package com.example.marlon.findyourfun;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TabHost;


public class MainActivity extends TabActivity {

    TabHost mTabHost;
     float lastX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("", getResources().getDrawable(R.drawable.star)).setContent(new Intent(this, cadastro.class)));
        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator("", getResources().getDrawable(R.drawable.magnifier)).setContent(new Intent(this, config.class)));
        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator("", getResources().getDrawable(R.drawable.pin)).setContent(new Intent(this  ,estabelecimento.class )));
        mTabHost.addTab(mTabHost.newTabSpec("4").setIndicator("", getResources().getDrawable(R.drawable.gear)).setContent(new Intent(this, config.class)));
        mTabHost.setCurrentTab(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN: {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX) {

                    switchTabs(true);
                }

                // if right to left swipe on screen
                if (lastX > currentX) {
                    switchTabs(false);
                }

                break;
            }
        }
        return false;
    }

    public void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (mTabHost.getCurrentTab() == 0)
                mTabHost.setCurrentTab(mTabHost.getTabWidget().getTabCount() - 1);
            else
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
        } else
        // move right
        {
            if (mTabHost.getCurrentTab() != (mTabHost.getTabWidget()
                    .getTabCount() - 1))
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
            else
                mTabHost.setCurrentTab(0);
        }
    }
}
