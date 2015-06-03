package com.example.marlon.findyourfun;

import android.app.Activity;
import android.app.TabActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;


public class config extends Activity {

    private int kms;
    private TextView txtKms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        SeekBar skRange =(SeekBar)findViewById(R.id.skbAlcance);
        txtKms = (TextView)findViewById(R.id.txtValorKM);
        kms = skRange.getProgress();

        skRange.setOnSeekBarChangeListener(skRangeListener);
    }

    private SeekBar.OnSeekBarChangeListener skRangeListener = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
            kms = progress;
            updateKms();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar){

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar){

        }
    };

    private void updateKms(){
        txtKms.setText(String.valueOf(kms));
    }

}
