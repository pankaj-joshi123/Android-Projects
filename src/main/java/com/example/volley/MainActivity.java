package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private Button button2;

    private RequestQueue myRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.results);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
    }

    public void getJSONResults(View view) {

        myRequestQueue= Volley.newRequestQueue(this);   ///Create the request queue using Volley.getNewReqQueue
        String url= "https://api.myjson.com/bins/g8ys8";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("employees");
                    for (int i=0;i<=array.length();i++) {
                        JSONObject employee= array.getJSONObject(i);
                        String firstName=employee.getString("firstname");
                        int age=employee.getInt("age");
                        String email=employee.getString("mail");

                        textView.append(firstName+""+String.valueOf(age)+""+email+"\n\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        myRequestQueue.add(request);
    }


    public void getSimpleString(View view) {
        myRequestQueue= Volley.newRequestQueue(this);   ///Create the request queue using Volley.getNewReqQueue
        String url="http://www.google.com";

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText("Response is: "+ response.substring(0,500));
                        myRequestQueue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error Happend");
                error.printStackTrace();
                myRequestQueue.stop();
            }
        });
            myRequestQueue.add(stringRequest);
    }
}




/* */