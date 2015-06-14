package com.example.marlon.findyourfun;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity extends TabActivity {

    TabHost mTabHost;
     float lastX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("", getResources().getDrawable(R.drawable.star)).setContent(new Intent(this, lista.class)));
      //mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator("", getResources().getDrawable(R.drawable.magnifier)).setContent(new Intent(this, config.class)));
        //mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator("", getResources().getDrawable(R.drawable.pin)).setContent(new Intent(this, estabelecimento.class )));
        mTabHost.addTab(mTabHost.newTabSpec("4").setIndicator("", getResources().getDrawable(R.drawable.gear)).setContent(new Intent(this, config.class)));
        mTabHost.setCurrentTab(0);
    }


}
