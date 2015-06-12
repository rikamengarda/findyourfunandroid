package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Teste extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
            Button teste = (Button) findViewById(R.id.btnTeste);

        teste.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(getApplicationContext(), lista.class);
                startActivity(it);
            };
        });
    }


}
