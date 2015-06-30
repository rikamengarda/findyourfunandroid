package com.example.marlon.findyourfun;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by marlon on 29/06/15.
 */
public class GetEstabelecimentosMap extends AsyncTask<Void,Void,Void> {
    private MapsFeed mContext2;
    String result;

    public GetEstabelecimentosMap(MapsFeed mContext)
    {
        this.mContext2 = mContext;
    }

    @Override
    protected void onPreExecute()
    {

    }

    protected Void doInBackground(Void...params)
    {
        try{

            HttpPost post = new HttpPost("http://10.0.2.2:3000/estabelecimentos.json");
            HttpClient client = new DefaultHttpClient();
            HttpResponse response;

            try {
                response = client.execute(post);
                HttpEntity entity = response.getEntity();

                if (entity != null) {

                    // A Simple JSON Response Read
                    InputStream instream = entity.getContent();
                    result = convertStreamToString(instream);
                    // now you have the string representation of the HTML request
                    instream.close();
                }
                // Headers
                org.apache.http.Header[] headers = response.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    System.out.println(headers[i]);
                }
            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
