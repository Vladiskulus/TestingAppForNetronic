package com.iambulance.testingappfornetronic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iambulance.testingappfornetronic.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NetworkTask nt;
    public List<MyModel> rvList;
    public List<MyModel> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        nt = new NetworkTask();
        nt.execute();
    }

    class NetworkTask extends AsyncTask<String, String, String> {
        MyModel rvModel = new MyModel();
        MyModel infoModel = new MyModel();

        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("https://randomuser.me/api/");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = url.openStream();
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                    byte[] buffer = new byte[4096];
//                    StringBuilder sb = new StringBuilder("");
                    int data = inputStreamReader.read();
//                    while (is.read(buffer) != -1) {
//                        sb.append(new String(buffer));
//                    }
                    while (data != -1) {
                        current += (char) data;
                        data = inputStreamReader.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray results = obj.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject user = results.getJSONObject(i);
                    JSONObject nameObj = user.getJSONObject("name");//fixme
                    String name = nameObj.getString("title") + ". " + nameObj.getString("first") + " " + nameObj.getString("last");
                    String email = user.getString("email");
                    String gender = user.getString("gender");
                    String image_url = user.getJSONObject("picture").getString("medium");
                    String birthdate = user.getJSONObject("dob").getString("date");
                    String age = user.getJSONObject("dob").getString("age");
                    rvModel = new MyModel(name, image_url);
                    infoModel = new MyModel(name, email, gender, birthdate, age, image_url);
                    rvList.add(rvModel);
                    info.add(infoModel);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            initRecyclerView(rvList);
        }
    }
    private void initRecyclerView(List<MyModel> list){
        RVAdapter adapter = new RVAdapter(list, this);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);
    }
}