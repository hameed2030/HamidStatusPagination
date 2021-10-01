package com.abuleath.whatsapp_status.navBarClass;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.abuleath.whatsapp_status.GlobalVariable;
import com.abuleath.whatsapp_status.MainActivity;
import com.abuleath.whatsapp_status.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.navigation.NavigationView;

public class Categories extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button love_category_btn , mor_category_btn , sad_category_btn , lag_category_btn , isl_category_btn
          ,Home_category_btn , fri_category_btn , ram_category_btn , eid_category_btn , fam_category_btn
          ,eve_category_btn , poe_category_btn;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //admob
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.InterstitialAd));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }});
        /////

        Home_category_btn =  findViewById(R.id.Home_category_btn);
        Home_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(1);
            }
        });

        love_category_btn =  findViewById(R.id.love_category_btn);
        love_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(2);
            }
        });

        mor_category_btn =  findViewById(R.id.mor_category_btn);
        mor_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(3);
            }
        });

        sad_category_btn =  findViewById(R.id.eng_category_btn);
        sad_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(4);

            }
        });

        lag_category_btn =  findViewById(R.id.lag_category_btn);
        lag_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(5);

            }
        });

        isl_category_btn =  findViewById(R.id.isl_category_btn);
        isl_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(6);

            }
        });

        fri_category_btn =  findViewById(R.id.fri_category_btn);
        fri_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(7);

            }
        });

        ram_category_btn =  findViewById(R.id.ram_category_btn);
        ram_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(8);

            }
        });

        eid_category_btn =  findViewById(R.id.eid_category_btn);
        eid_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(9);

            }
        });

        fam_category_btn =  findViewById(R.id.fam_category_btn);
        fam_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(10);

            }
        });

        eve_category_btn =  findViewById(R.id.eve_category_btn);
        eve_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(11);

            }
        });

        poe_category_btn =  findViewById(R.id.poe_category_btn);
        poe_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction(12);

            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent h = new Intent(Categories.this, MainActivity.class);
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
                Intent f = new Intent(Categories.this, MainActivity.class);
                startActivity(f);
                finish();
                break;
            case R.id.nav_categories:
                break;

            case R.id.nav_favorites:
                Intent i = new Intent(Categories.this, Favorites.class);
                startActivity(i);
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

    public void Transaction(int catId)
    {
        ((GlobalVariable) this.getApplication()).setglobalCatId(catId);
        Intent f = new Intent(Categories.this, MainActivity.class);
        startActivity(f);
        finish();
    }
}
