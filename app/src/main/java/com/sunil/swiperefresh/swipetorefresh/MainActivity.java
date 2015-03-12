package com.sunil.swiperefresh.swipetorefresh;


/**
 * Created by Sunil on 12-03-2015.
 */

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{


    SwipeRefreshLayout swipeLayout;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> arrayList =  new ArrayList();;
    String [] array = new String[]{"India","Australiya","America","South Africa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        listView= (ListView)findViewById(R.id.listview);

        adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, listData());
        listView.setAdapter(adapter);
}

    @Override
    public void onRefresh() {

        // Here you can execute the Async task in background and update ui onPostexecute method

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listData();
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        }, 5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public ArrayList<String> listData(){

        for (String items : array) {
            arrayList.add(items);
        }

        return arrayList;
    }
}
