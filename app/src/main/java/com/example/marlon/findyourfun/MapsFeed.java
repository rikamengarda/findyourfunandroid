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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsFeed extends FragmentActivity {
    private GoogleMap mMap;

    private BancoDeDados db;
    private BancoConfig dbC;

    private List<Est> estabelecimento = new ArrayList<Est>();
    private List<Configuracoes> configs = new ArrayList<Configuracoes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        db = new BancoDeDados(this);
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
        db.abrir();
        dbC.abrir();

        estabelecimento.clear();
        configs.clear();

        Cursor cursor = db.retornaTodosEst();
        Cursor cursorC = dbC.retornaTodosConfig();

        Configuracoes c = new Configuracoes();
        cursorC.moveToFirst();
        c.alc = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_ALC));
        c.comida = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_COMIDA));
        c.destilado = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_DEST));
        if (cursor.moveToFirst())
            do {
                Est a = new Est();
                a.id = cursor.getInt(cursor.getColumnIndex(db.KEY_ID));
                a.nome = cursor.getString(cursor.getColumnIndex(db.KEY_EST));
                a.endereco = cursor.getString(cursor.getColumnIndex(db.KEY_END));
                a.telefone = cursor.getString(cursor.getColumnIndex(db.KEY_TEL));
                a.destilado = cursor.getInt(cursor.getColumnIndex(db.KEY_DEST));
                a.comida = cursor.getInt(cursor.getColumnIndex(db.KEY_COMIDA));
                a.dist = retorna_distancia(c.alc, a.endereco);

                if(c.alc >= a.dist){
                    if(verifica_tipo(a.comida, a.destilado, c.comida, c.destilado)){
                        estabelecimento.add(a);
                    }
                }

            } while (cursor.moveToNext());

        if(estabelecimento.size() > 0){

        }else{
            Context context = getApplicationContext();
            CharSequence text = "Nenhum estabelecimento encontrado para suas preferêncais!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        db.fechar();
        dbC.fechar();
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
