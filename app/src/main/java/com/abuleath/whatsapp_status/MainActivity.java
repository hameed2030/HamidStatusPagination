package com.abuleath.whatsapp_status;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.abuleath.whatsapp_status.Adapters.PagerAdapter;
import com.abuleath.whatsapp_status.Model.DBHelper;
import com.abuleath.whatsapp_status.navBarClass.Categories;
import com.abuleath.whatsapp_status.navBarClass.Favorites;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    Toolbar toolbar = null;
    PagerAdapter mpageradapter;
    ViewPager mviewPager;
    /////////////////////// data base
    DBHelper mydb;
    /////// admob
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///// admob
        MobileAds.initialize(this, getString(R.string.AdmobID));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ///////////////
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        /////////////////////// data base
        mydb = new DBHelper(this);
        // Page Adapter and Tabs
        /////////////
        mpageradapter = new PagerAdapter(getSupportFragmentManager());
        mviewPager =  findViewById(R.id.view_pager);
        setUpViewPager(mviewPager);

        TabLayout tablayout =  findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(mviewPager);

        for (int i = 0; i < tablayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tablayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(25, 0, 25, 0);
            tab.requestLayout();

        }
    }

    private void setUpViewPager(ViewPager viewPager)
    {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(1)) , getString(R.string.category_Home));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(2)) , getString(R.string.category_love));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(3)) , getString(R.string.category_morning));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(4)) , getString(R.string.category_sad));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(5)) , getString(R.string.category_laugh));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(6)) , getString(R.string.category_Islamic));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(7)) , getString(R.string.category_friday));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(8)) , getString(R.string.category_Ramadan));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(9)) , getString(R.string.category_eid));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(10)) , getString(R.string.category_family));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(11)) , getString(R.string.category_evening));
        adapter.addFragment(new StatusFragment(mydb.retriveStatusByCategory(12)) , getString(R.string.category_Poems));

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(((GlobalVariable) this.getApplication()).getglobalCatId()-1);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
                break;

            case R.id.nav_categories:
                Intent i = new Intent(MainActivity.this, Categories.class);
                startActivity(i);
                finish();
                break;

            case R.id.nav_favorites:
                Intent f = new Intent(MainActivity.this, Favorites.class);
                startActivity(f);
                finish();
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
