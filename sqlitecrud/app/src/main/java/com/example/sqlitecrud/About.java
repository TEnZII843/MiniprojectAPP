package com.example.sqlitecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class About extends AppCompatActivity {
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setUpToolbar();
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
}