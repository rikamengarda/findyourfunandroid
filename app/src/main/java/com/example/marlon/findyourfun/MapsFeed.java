package com.example.marlon.findyourfun;

import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsFeed extends FragmentActivity {
    private GoogleMap mMap;

    private BancoConfig dbC;

    private List<Est> estabelecimento = new ArrayList<Est>();
    private List<Configuracoes> configs = new ArrayList<Configuracoes>();

    //GetEstabelecimentosMap obj_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //obj_request = new GetEstabelecimentosMap(this);
        //obj_request.execute((Void) null);

        dbC = new BancoConfig(this);
        lerDados();
        setUpMapIfNeeded(estabelecimento);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lerDados();
        setUpMapIfNeeded(estabelecimento);
    }

    public void lerDados() {
        //db.abrir();

        estabelecimento.clear();

        String teste = "[{\"id\":1,\"nome\":\"Picknick Bar\",\"endereco\":\"Av. Manoel Ribas 5966. Santa Felicidade\",\"telefone\":\"(41) 3273-4279\",\"site\":\"\",\"descricao\":\"Double de chopp até as 20h\",\"horario\":\"17h Às 20h\",\"destilado\":1,\"comida\":1,\"imagem\":\"i01\",\"preco\":\"7.00\",\"url\":\"http://localhost:3000/estabelecimentos/1.json\"},{\"id\":2,\"nome\":\"JPL Burguer\",\"endereco\":\"Av. Vicente Machado 833. Batel\",\"telefone\":\"(41) 3024-2910\",\"site\":\"\",\"descricao\":\"Compre dois chopps 500ml e ganhe uma porção\\r\\n\",\"horario\":\"17h às 21h\",\"destilado\":0,\"comida\":1,\"imagem\":\"i02\",\"preco\":\"7.00\",\"url\":\"http://localhost:3000/estabelecimentos/2.json\"},{\"id\":3,\"nome\":\"Comenda Bar\",\"endereco\":\"R. Comendador Araújo 484. Centro\",\"telefone\":\"(41) 3044-4357\",\"site\":\"\",\"descricao\":\"Chopp brahma pela metade do preço até 21h\\r\\n\",\"horario\":\"18h às 21h\",\"destilado\":0,\"comida\":1,\"imagem\":\"i03\",\"preco\":\"10.00\",\"url\":\"http://localhost:3000/estabelecimentos/3.json\"},{\"id\":4,\"nome\":\"Quintal do Guima\",\"endereco\":\"Av. Vicente Machado. 687. Batel\",\"telefone\":\"(41) 3077-3618\",\"site\":\"\",\"descricao\":\"Venha com mais 4 amigos e ganhe uma rodada de chopp\\r\\n\",\"horario\":\"17h às 19h\",\"destilado\":0,\"comida\":1,\"imagem\":\"i04\",\"preco\":\"7.50\",\"url\":\"http://localhost:3000/estabelecimentos/4.json\"},{\"id\":5,\"nome\":\"Barba Negra Hamburgueria\",\"endereco\":\"Avenida Vicente Machado 642. Batel\",\"telefone\":\"(41) 3333-4323\",\"site\":\"\",\"descricao\":\"Destilados brasileiros pela metade do preço ou double chopp - até 20h\\r\\n\",\"horario\":\"18h às 20h\",\"destilado\":1,\"comida\":1,\"imagem\":\"i05\",\"preco\":\"8.00\",\"url\":\"http://localhost:3000/estabelecimentos/5.json\"},{\"id\":6,\"nome\":\"Clube do Malte\",\"endereco\":\"Rua Desembargador Motta 2200. Centro\",\"telefone\":\"(41) 3014-3414\",\"site\":\"\",\"descricao\":\"Chopps artesanais pela metade do preço até as 20h\\r\\n\",\"horario\":\"17h às 20h\",\"destilado\":0,\"comida\":1,\"imagem\":\"i06\",\"preco\":\"10.00\",\"url\":\"http://localhost:3000/estabelecimentos/6.json\"},{\"id\":7,\"nome\":\"Picanha Brava\",\"endereco\":\"Av. Iguaçu 3108. Rebouças\",\"telefone\":\"(41) 3244-1919\",\"site\":\"\",\"descricao\":\"Confira nossos combinados de happy hour \\r\\n\",\"horario\":\"18h às 21h\",\"destilado\":1,\"comida\":1,\"imagem\":\"i08\",\"preco\":\"7.00\",\"url\":\"http://localhost:3000/estabelecimentos/7.json\"},{\"id\":8,\"nome\":\"Mustang Sally\",\"endereco\":\"R. Cel. Dulcídio 517. Batel\",\"telefone\":\"(41) 3018-8118\",\"site\":\"\",\"descricao\":\"Double de caipirinha e chopp até as 20h\\r\\n\",\"horario\":\"17h às 20h\",\"destilado\":1,\"comida\":1,\"imagem\":\"i09\",\"preco\":\"8.00\",\"url\":\"http://localhost:3000/estabelecimentos/8.json\"},{\"id\":9,\"nome\":\"Cervejaria da Vila\",\"endereco\":\"R. Mateus Leme 2631. Bom Retiro\",\"telefone\":\"(41) 3015-4620\",\"site\":\"\",\"descricao\":\"Compre uma cerveja e leve a outra pela metade do preço\\r\\n\",\"horario\":\"18h às 20h\",\"destilado\":0,\"comida\":1,\"imagem\":\"i07\",\"preco\":\"9.00\",\"url\":\"http://localhost:3000/estabelecimentos/9.json\"}]";

        estabelecimento = getEst(teste);

        //Cursor cursor = db.retornaTodosEst();

        /*cursor.moveToFirst();
            do {
                    Est a = new Est();
                    a.id = cursor.getInt(cursor.getColumnIndex(db.KEY_ID));
                    a.nome = cursor.getString(cursor.getColumnIndex(db.KEY_EST));
                    a.endereco = cursor.getString(cursor.getColumnIndex(db.KEY_END));
                    a.descricao = cursor.getString(cursor.getColumnIndex(db.KEY_DESC));
                    a.telefone = cursor.getString(cursor.getColumnIndex(db.KEY_TEL));
                    a.horario = cursor.getString(cursor.getColumnIndex(db.KEY_HORARIO));
                    a.site = cursor.getString(cursor.getColumnIndex(db.KEY_SITE));
                    a.facebook = cursor.getString(cursor.getColumnIndex(db.KEY_FB));
                    a.instagram = cursor.getString(cursor.getColumnIndex(db.KEY_INST));
                    a.twitter = cursor.getString(cursor.getColumnIndex(db.KEY_TT));
                    a.cerveja = cursor.getInt(cursor.getColumnIndex(db.KEY_CERVA));
                    a.destilado = cursor.getInt(cursor.getColumnIndex(db.KEY_DEST));
                    a.comida = cursor.getInt(cursor.getColumnIndex(db.KEY_COMIDA));
                    a.preco = cursor.getString(cursor.getColumnIndex(db.KEY_PRECO));
                    a.imgBar = cursor.getString(cursor.getColumnIndex(db.KEY_IMG));

                    a.dist = retorna_distancia(c.alc, a.endereco);

                    if(c.alc >= a.dist){
                        if(verifica_tipo(a.comida, a.destilado, c.comida, c.destilado)){
                            estabelecimento.add(a);
                        }
                    }

            } while (cursor.moveToNext());
        */
        if(estabelecimento.size() > 0){

        }else{
            Context context = getApplicationContext();
            CharSequence text = "Nenhum estabelecimento encontrado para suas preferêncais!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        //db.fechar();

    }

    private List<Est> getEst(String jsonString) {

        List<Est> estabs = new ArrayList<Est>();
        int tipo;
        int dist;

        try {
            JSONArray estabsLists = new JSONArray(jsonString);
            JSONObject trendList = estabsLists.getJSONObject(0);
            //JSONArray trendsArray = trendList.getJSONArray("estabs");

            JSONObject trend;

            dbC.abrir();
            configs.clear();
            Cursor cursorC = dbC.retornaTodosConfig();

            Configuracoes c = new Configuracoes();
            cursorC.moveToFirst();
            c.alc = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_ALC));
            c.comida = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_COMIDA));
            c.destilado = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_DEST));

            for (int i = 0; i < estabsLists.length(); i++) {
                trend = new JSONObject(estabsLists.getString(i));

                Est objetoTrend = new Est();
                objetoTrend.nome = trend.getString("nome");
                objetoTrend.endereco = trend.getString("endereco");
                objetoTrend.descricao = trend.getString("descricao");
                objetoTrend.telefone = trend.getString("telefone");
                objetoTrend.horario = trend.getString("horario");
                objetoTrend.site = trend.getString("site");
                objetoTrend.destilado = trend.getInt("destilado");
                objetoTrend.comida = trend.getInt("comida");
                objetoTrend.preco = trend.getString("preco");
                objetoTrend.imgBar = trend.getString("imagem");
                objetoTrend.dist = retorna_distancia(c.alc, objetoTrend.endereco);

                if(c.alc >= objetoTrend.dist){
                    if(verifica_tipo(objetoTrend.comida, objetoTrend.destilado, c.comida, c.destilado)){
                        estabs.add(objetoTrend);
                    }
                }
            }
        } catch (JSONException e) {

        }
        dbC.fechar();
        return estabs;
    }

    private int retorna_distancia(int dist, String end){
        float [] distancia = new float [2];
        float calc;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);


        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        double latitude = myLocation.getLatitude();
        double longitude = myLocation.getLongitude();

        try {
            Geocoder coder = new Geocoder(this);
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(end, 1);
            for(Address add : adresses) {
                double destlongitude = add.getLongitude();
                double destlatitude = add.getLatitude();
                myLocation.distanceBetween(latitude, longitude, destlatitude,destlongitude , distancia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        calc = distancia[0]/1000;

        return (int)calc;

    }

    private boolean verifica_tipo(int comida, int dest, int prefComida, int prefDest){
        if(prefComida == 1 && prefDest == 1){
            if(comida == prefComida) {
                if (dest == prefDest) {
                    return true;
                }
            }
            return false;
        }else if(prefComida == 0 && prefDest == 0){
            return true;
        }else{
            if(comida == prefComida) {
                return true;
            }
            if(dest == prefDest){
                return true;
            }
        }
        return false;
    }

    private void setUpMapIfNeeded(List<Est> estabelecimento) {
        mMap = null;
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(estabelecimento);
            }
        }
    }

    private void setUpMap(List<Est> estabelecimento) {
        // Enable MyLocation Layer of Google Map
        mMap.clear();
        mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        double latitude = myLocation.getLatitude();
        double longitude = myLocation.getLongitude();
        //Location dest = new Location(provider);

        for(int i = 0; i < estabelecimento.size() ; i++){
            Geocoder coder = new Geocoder(this);
            try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(estabelecimento.get(i).endereco, 1);
                for(Address add : adresses){
                    double destlongitude = add.getLongitude();
                    double destlatitude = add.getLatitude();
                    mMap.addMarker(new MarkerOptions().position(new LatLng(destlatitude, destlongitude)).title(estabelecimento.get(i).nome).snippet(estabelecimento.get(i).telefone));

                    // set map type
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Show the current location in Google Map
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

    }
}
