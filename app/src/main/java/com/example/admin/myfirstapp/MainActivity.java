package com.example.admin.myfirstapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void insertIntoDB(View view) {
        // Do something in response to button click
        Log.e("a:","before database object creation");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://booming-cosine-188305-1e6db.firebaseio.com/");
        Log.e("a:","before ref object creation");
        DatabaseReference myRef = database.getReference("mydb/visitors");
        Log.e("a:","before JSON object creation");
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", "sample");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try{
            Log.e("a:","before creating visitor object");
            Visitor visitor = new Visitor("sample");
            Log.e("a:","before setValue");
            myRef.setValue(visitor);
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.e("a:","after setValue");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
