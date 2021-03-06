package com.example.marlon.findyourfun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marlon on 11/06/2015.
 */
public class EstabelecimentoAdapter extends BaseAdapter {
    private List<Est> estabelecimento;
    private LayoutInflater inflater;
    private Context context;
    ImageView image;
    public EstabelecimentoAdapter (Context context, List<Est> estabelecimento){
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
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("i01", R.drawable.i01);
        map.put("i02", R.drawable.i02);
        map.put("i03", R.drawable.i03);
        map.put("i04", R.drawable.i04);
        map.put("i05", R.drawable.i05);
        map.put("i06", R.drawable.i06);
        map.put("i07", R.drawable.i07);
        map.put("i08", R.drawable.i08);
        map.put("i09", R.drawable.i09);
        map.put("i10", R.drawable.i10);

        View v = inflater.inflate(R.layout.activity_item_lista, null);
        ((TextView)(v.findViewById(R.id.txtNomeBarList))).setText(estabelecimento.get(position).nome);
        ((TextView)(v.findViewById(R.id.txtPrecoLista))).setText(String.valueOf("R$ " + estabelecimento.get(position).preco));
        ((ImageView)(v.findViewById(R.id.imgList))).setImageResource(map.get(estabelecimento.get(position).imgBar));
        ((TextView)(v.findViewById(R.id.txtDistLista))).setText(String.valueOf(estabelecimento.get(position).dist + "kms"));
        return v;
    }

  }

