package com.abuleath.whatsapp_status.navBarClass;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abuleath.whatsapp_status.Adapters.StatusAdapter;
import com.abuleath.whatsapp_status.GlobalVariable;
import com.abuleath.whatsapp_status.MainActivity;
import com.abuleath.whatsapp_status.Model.DBHelper;
import com.abuleath.whatsapp_status.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class Favorites extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    StatusAdapter adapter;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydb = new DBHelper(this);

        ///// admob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        /////// admob
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ///////////////
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerview = findViewById(R.id.fav_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new StatusAdapter(this , mydb.retrieveFavorites());
        // show ProgressBar until data load
        recyclerview.setAdapter(adapter);

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent h = new Intent(Favorites.this, MainActivity.class);
            startActivity(h);
            finish();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id) {
            case R.id.nav_home:
                ((GlobalVariable) this.getApplication()).setglobalCatId(1);
                Intent f = new Intent(Favorites.this, MainActivity.class);
                startActivity(f);
                finish();
                break;

            case R.id.nav_categories:
                Intent i = new Intent(Favorites.this, Categories.class);
                startActivity(i);
                finish();
                break;

            case R.id.nav_favorites:
                break;

            case R.id.nav_privacy:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String privacyText = "سياسة الخصوصية";
                String message = getString(R.string.statuses_privacy_policy);
                TextView title = new TextView(this);
                // You Can Customise your Title here
                title.setText(privacyText);
                title.setBackgroundColor(Color.DKGRAY);
                title.setPadding(15, 15, 15, 15);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.WHITE);
                title.setTextSize(20);
                builder.setCustomTitle(title);
                builder.setMessage(message);
                builder.show();
                break;

            case R.id.nav_share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "افضل تطبيق حالات واتساب بدون نت\n" +
                        "https://play.google.com/store/apps/details?id=com.abuleath.whatsapp_status";
                String shareSub = "حالات واتساب كتابية";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "مشاركة عبر"));
                break;

            case R.id.nav_rate:
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.abuleath.whatsapp_status"));
                startActivity(viewIntent);
                break;
            case R.id.more_apps:
                Intent viewIntent2 = new Intent("android.intent.action.VIEW",
                                Uri.parse("https://play.google.com/store/apps/developer?id=snapgrem"));
                startActivity(viewIntent2);
                break;
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
    }
}
