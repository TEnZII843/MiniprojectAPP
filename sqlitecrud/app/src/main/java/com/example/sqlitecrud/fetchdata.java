package com.example.sqlitecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class fetchdata extends AppCompatActivity
{
    dbmanager db;
    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);
        setUpToolbar();

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Handle the camera import action (for now display a toast).
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_today:
                        // Handle the gallery action (for now display a toast).
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(getApplicationContext(),fetchdata.class);
                        startActivity(intent1);
                        return true;
                    case R.id.nav_contact:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:17755316"));
                        startActivity(intent2);
                        return true;
                    case R.id.nav_about:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent3 = new Intent(getApplicationContext(),About.class);
                        startActivity(intent3);
                        return true;
                    case R.id.nav_exit:
                        drawer.closeDrawer(GravityCompat.START);
                        finish();
                        System.exit(0);
                        return true;
                    default:
                }
                return false;
            }
        });


        Cursor cursor=new dbmanager(this).readalldata();
        dataholder=new ArrayList<>();


        while(cursor.moveToNext())
        {
            model obj=new model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            dataholder.add(obj);
        }

        myadapter adapter=new myadapter(getApplicationContext(),dataholder);
        recyclerView.setAdapter(adapter);

    }
    public void setUpToolbar() {
        drawer = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                Intent obj = new Intent(fetchdata.this,fetchdata.class);
                startActivity(obj);
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddTask(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void update(View view) {
        Intent obj1 = new Intent(fetchdata.this,update.class);
        startActivity(obj1);
    }
}