package com.example.eone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eone.mainLayouts.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new HomeFragment();
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    //final Fragment fragment2 = new SearchFragment();
    //final Fragment fragment3 = new CategoryFragment();
    //final Fragment fragment4 = new SettingsFragment();
    //final Fragment fragment6 = new UserProfileFragment();
    ImageView editImg, camImg;
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawer, toolbar,         /* DrawerLayout object */
                /* nav drawer icon to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description */
                R.string.navigation_drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // getActionBar().setTitle(mDrawerTitle);
            }
        };
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //navigationView.getMenu().getItem(0).setChecked(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myProfile:
                       // Intent i = new Intent(getApplicationContext(), MyOrdersActivity.class);
                        //i.putExtra("token", token);
                       // i.putExtra("id", id);
                       // startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                   /* case R.id.adminOptions:
                        Intent view = new Intent(getApplicationContext(), AdminActivity.class);
                        view.putExtra("token", token);
                        view.putExtra("id", id);
                        view.putExtra("status", accountType);
                        startActivity(view);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.profileSettings:
                        Intent p = new Intent(getApplicationContext(), ProfileSetting.class);
                        p.putExtra("token", token);
                        p.putExtra("id", id);
                        p.putExtra("status", accountType);
                        startActivity(p);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;

                    */
                }
                return false;
            }
        });

        /*toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

         */
       /* fm.beginTransaction().

                replace(R.id.main_container, fragment6, "5").

                hide(fragment6).

                commit();


        fm.beginTransaction().

                replace(R.id.main_container, fragment3, "3").

                hide(fragment3).

                commit();
        fm.beginTransaction().

                replace(R.id.main_container, fragment2, "2").

                hide(fragment2).

                commit();

        */
        fm.beginTransaction().

                replace(R.id.main_container, fragment1, "1").

                commit();



        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        fm.beginTransaction().replace(R.id.main_container, fragment1).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment1).commit();

                        //item.setIcon(R.drawable.home_active);
                        // active = fragment1;
                        //  bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        return true;
                  /*  case R.id.search:
                        fm.beginTransaction().replace(R.id.main_container, fragment2).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment2).commit();
                        // active = fragment2;
                        //item.setIcon(R.drawable.search_active);
                        // bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        return true;
                    case R.id.category:

                        fm.beginTransaction().replace(R.id.main_container, fragment3).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment3).commit();
                        //  active = fragment3;
                        //item.setIcon(R.drawable.category_active);
                        // bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true;

                    case R.id.setting:
                        fm.beginTransaction().replace(R.id.main_container, fragment6).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment6).commit();
                        //active = fragment6;
                        //item.setIcon(R.drawable.person2_active);
                        return true;
                        */
                }
                return false;
            }
        });


    }

}