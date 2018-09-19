package com.krishnasomu.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.krishnasomu.myfirstapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//import com.firebase.client.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://booming-cosine-188305-1e6db.firebaseio.com/");
        DatabaseReference chatRef = database.getReference("mydb/chats");
        ValueEventListener chatListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                TextView tv = (TextView) findViewById(R.id.textView2);
                String strChatContent = "";
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    strChatContent = strChatContent + System.getProperty("line.separator") + child.getValue(String.class);
                }
                tv.setText(strChatContent);
                //= dataSnapshot.getValue(Post.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        chatRef.addValueEventListener(chatListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void insertIntoDB(View view) {
        // Do something in response to button click
        EditText editText = (EditText) findViewById(R.id.editText);
        Log.e("a:","before database object creation");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://booming-cosine-188305-1e6db.firebaseio.com/");
        Log.e("a:","before ref object creation");
        DatabaseReference myRef = database.getReference("mydb/visitors");
        DatabaseReference chatRef = database.getReference("mydb/chats");
        Log.e("a:","before JSON object creation");
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", "sample1");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try{

            Log.e("a:","before creating visitor object");
            //Visitor visitor = new Visitor(editText.getText().toString());
            Map<String, Object> chatsMap = new HashMap();
            Log.e("a:","before setValue");
            //chatRef.updateChildren(chatsMap);
            DatabaseReference dref = chatRef.push();
            chatsMap.put("chat",editText.getText().toString());
            dref.setValue(editText.getText().toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.e("a:","after setValue");
        editText.setText("");
        editText.requestFocus();
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
