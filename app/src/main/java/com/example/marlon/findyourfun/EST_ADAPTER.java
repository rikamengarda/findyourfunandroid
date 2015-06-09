package com.example.marlon.findyourfun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Marlon on 08/06/2015.
 */
public abstract class EST_ADAPTER extends BaseAdapter {
    private List<Est> estabelecimento;
    private LayoutInflater inflater;

    public int getCount(){
        return estabelecimento.size();
    }

    public Object getItem(int position){
        return estabelecimento.get(position);
    }

    public long getItemId(int arg0){
        return 0;
    }


    public View getView(final int position, View arg1, ViewGroup arg2){
        View v = inflater.inflate(R.layout.activity_item_lista, null);
        ((TextView)(v.findViewById(R.id.txtNome))).setText(estabelecimento.get(position).nome);
        ((ImageButton)(v.findViewById(R.id.btnEditar))).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                edita(estabelecimento.get(position));
            }
        });
        ((ImageButton) (v.findViewById(R.id.btnExcluir))).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleta(estabelecimento.get(position));
            }
        });
        return v;
    }

    public abstract void edita(Est estabelecimento);
    public abstract void deleta(Est estabelecimento);
}
