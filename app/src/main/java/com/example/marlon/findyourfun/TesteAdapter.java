package com.example.marlon.findyourfun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Marlon on 14/06/2015.
 */
public abstract class TesteAdapter extends BaseAdapter {
    private List<Est> estabelecimento;
    private LayoutInflater inflater;

    public TesteAdapter (Context context, List<Est> estabelecimento){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.estabelecimento = estabelecimento;
    }

    public void novosDados(List<Est> estab){
        this.estabelecimento = estab;
        //notifyDataSetChanged();
    }

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
        View v = inflater.inflate(R.layout.activity_teste, null);
        ((TextView)(v.findViewById(R.id.txtNomeTeste))).setText(estabelecimento.get(position).nome);
        ((ImageButton) (v.findViewById(R.id.btnExcluir))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleta(estabelecimento.get(position));
            }

        });
        return v;
    }

    public abstract void deleta(Est estabelecimento);
}
