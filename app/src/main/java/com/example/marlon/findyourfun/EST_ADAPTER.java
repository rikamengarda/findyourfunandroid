package com.example.marlon.findyourfun;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    public void novosDados(List<Est> est){
        this.estabelecimento = est;
        //notifyDataSetChanged();
    }

    public Object getItem(int position){
        return estabelecimento.get(position);
    }

    public long getItemId(int arg0){
        return 0;
    }

    public View getView(final int position, View arg1, ViewGroup arg2){
        View v = inflater.inflate(R.layout.activity_item_lista, null);
        ((TextView)(v.findViewById(R.id.txtNomeBar))).setText(estabelecimento.get(position).nome);
        return v;
    }

}
