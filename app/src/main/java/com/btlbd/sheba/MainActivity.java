package com.btlbd.sheba;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.btlbd.sheba.fragments.CreditFragment;
import com.btlbd.sheba.fragments.HelpFragment;
import com.btlbd.sheba.fragments.HomeFragment;
import com.btlbd.sheba.fragments.OrderFragment;
import com.btlbd.sheba.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mFrameLayout;

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private CreditFragment creditFragment;
    private ProfileFragment profileFragment;
    private HelpFragment helpFragment;
    private Spinner places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.botnavId);
        mFrameLayout = findViewById(R.id.framId);
        places = findViewById(R.id.placeId);

        mToolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        creditFragment = new CreditFragment();
        profileFragment = new ProfileFragment();
        helpFragment = new HelpFragment();

        DrawerLayout drawer = findViewById(R.id.drawerlayId);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //set the spinners adapter to the previously created one.
        String[] items = new String[]{"Gulshan", "Uttora","Banani", "Mohakhali", "Mirpur"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        places.setAdapter(adapter);

        NavigationView navigationView = findViewById(R.id.navviewId);

        setFragment(homeFragment);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.order:
                        setFragment(orderFragment);
                        return true;

                    case R.id.credit:
                        setFragment(creditFragment);
                        return true;

                    case R.id.profile:
                        setFragment(profileFragment);
                        return true;

                    case R.id.help:
                        setFragment(helpFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.notification_menu,menu);
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.notification_menu, menu);
//        return true;
//    }
}
