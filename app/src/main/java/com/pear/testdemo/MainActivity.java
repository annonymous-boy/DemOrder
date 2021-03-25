package com.pear.testdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pear.testdemo.adapter.RestaurantAdapter;
import com.pear.testdemo.bean.Item;
import com.pear.testdemo.bean.RestBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private ArrayList<RestBean> arrayList;
    private ArrayList<Item> items;
    RecyclerView recyclerView;
    EditText ed_search;
    RestaurantAdapter restaurantAdapter;
    String URL = "https://backend.pearpartner.com/order/user/previous_orders/Qu2cRybfWGMaki7eJtk2O0oxE3y2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ed_search = findViewById(R.id.et_search_details);
        ed_search.addTextChangedListener(this);
        recyclerView = findViewById(R.id.recyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        if(isNetworkConnected()){
            getData();
        }else{
            Toast.makeText(MainActivity.this,"Please check your network connection.", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    arrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("hjjhhjfhj"," 1 "+response);
                    for (int i = 0; i< jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = object.getJSONArray("batch");
                        for (int j = 0; j < jsonArray1.length(); j++){
                            JSONObject data = jsonArray1.getJSONObject(j);
                            items = new ArrayList<>();
                            JSONArray itemsData = data.getJSONArray("items");
                            for(int k = 0; k < itemsData.length(); k++){
                                JSONObject itemsdata = itemsData.getJSONObject(k);
//                                Log.d("hjjhhjfhj"," 4 "+itemsdata.getString("name"));
                                items.add(new Item(itemsdata.getString("name"),itemsdata.getString("quantity")));
                            }
                        }
                        arrayList.add(new RestBean(object.getString("_id"),object.getString("timestamp"),object.getString("restaurant_name"),object.getString("restaurant_image"),
                                object.getString("restaurant_location"),object.getString("grand_total"),items));
                    }
                    restaurantAdapter = new RestaurantAdapter(MainActivity.this,arrayList);
                    recyclerView.setAdapter(restaurantAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hjjhhjfhj",error.toString());
            }
        });
        queue.add(request);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        restaurantAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}