package in.co.shahilislam.parkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyspotsActivity extends AppCompatActivity {

    public static String q,p;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    public static List<ListPlace> listItems;

    public static final String TAG_ARRAY="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myspots);

        viewloc();


        }

    public void viewloc() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,AppConfig.URL_GETLOC , new Response.Listener<String>() {

            @Override
            public void onResponse(String response){

                Toast.makeText(MyspotsActivity.this, response, Toast.LENGTH_LONG).show();

               // String[] fetch = response.split(":");

                if(response.contains("Retrived")) {

                    recyclerView = (RecyclerView)findViewById(R.id.places_list_recycler_view);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyspotsActivity.this));

                    listItems = new ArrayList<ListPlace>();
                        ListPlace listItem = new ListPlace();
                        listItem.setName( response );
                       // listItem.setAddress(" " + f[2]);
                        listItems.add(listItem);



                    adapter = new MyAdapter(listItems, MyspotsActivity.this);
                    recyclerView.setAdapter(adapter);

                }

            }

        },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyspotsActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("loc", TAG_ARRAY);
                //  params.put("password", password);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyspotsActivity.this);
        requestQueue.add(stringRequest);




    }

}
