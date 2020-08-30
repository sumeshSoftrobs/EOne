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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eone.mainLayouts.Todo.ToDoFragment;
import com.example.eone.mainLayouts.home.HomeFragment;
import com.example.eone.mainLayouts.materials.StudyMaterialsFragment;
import com.example.eone.mainLayouts.notice.NoticesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    private TextView userName, userEmail;

    private NavigationView navigationView;
    Bundle bundle = new Bundle();
    String name, email;
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new NoticesFragment();
    final Fragment fragment3 = new StudyMaterialsFragment();

    final Fragment fragment6 = new ToDoFragment();
    ImageView editImg, camImg;
    Fragment active = fragment1;

    final FragmentManager fm = getSupportFragmentManager();

    String id = null;
    String message = null;
    String token = null;
    String accountType = null;
    String status = null;
    Bitmap bitmap;
    String img_url = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("E Store");
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer_layout);
        /////////////  Access Tocken  ////////////////

        readData();
        View headerView = navigationView.getHeaderView(0);

        //CardView navImageView = (CardView) headerView.findViewById(R.id.profileImage);


        /////////////////////////////////////////////
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


       fm.beginTransaction().

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
                    case R.id.notice:
                        fm.beginTransaction().replace(R.id.main_container, fragment2).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment2).commit();
                        // active = fragment2;
                        //item.setIcon(R.drawable.search_active);
                        // bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        return true;
                    case R.id.studyMaterials:

                        fm.beginTransaction().replace(R.id.main_container, fragment3).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment3).commit();
                        //  active = fragment3;
                        //item.setIcon(R.drawable.category_active);
                        // bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true;

                    case R.id.toDoList:
                        fm.beginTransaction().replace(R.id.main_container, fragment6).addToBackStack(BACK_STACK_ROOT_TAG).show(fragment6).commit();
                        //active = fragment6;
                        //item.setIcon(R.drawable.person2_active);
                        return true;

                }
                return false;
            }
        });


    }
    private void readData() {


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //navigationView.getMenu().getItem(0).setChecked(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.helpDesk:
                        Toast.makeText(getApplicationContext(),"HelpLine Number",Toast.LENGTH_LONG).show();
                      //  overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.myProfile:
                         Toast.makeText(getApplicationContext(),"profile",Toast.LENGTH_LONG).show();
                       // overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.calenders:

                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case R.id.signOut:
                       /* boolean isFilePresent = isFilePresent(getApplicationContext(), "AccessTocken.txt");
                        if (isFilePresent) {
                            delete(getApplicationContext(),"AccessTocken.txt");

                        } else {
                            accountType = "1";
                            // Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
                            //startActivity(i);

                            Toast.makeText(getApplicationContext(),"please Login / Register",Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(getApplicationContext(),"Signed Out",Toast.LENGTH_LONG).show();

                        */

                        break;
                }
                return false;
            }
        });


        ///////////////Action Bar Title////////////////////////

        ///////////////Action Bar Title////////////////////////




    }

}