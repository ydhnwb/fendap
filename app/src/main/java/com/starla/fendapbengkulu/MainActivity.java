package com.starla.fendapbengkulu;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.starla.fendapbengkulu.fragments.FragmentAbout;
import com.starla.fendapbengkulu.fragments.FragmentHome;
import com.starla.fendapbengkulu.fragments.FragmentRuang;
import com.starla.fendapbengkulu.fragments.FragmentTour;

public class MainActivity extends AppCompatActivity {

    private static int navStatus = -1;
    private Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(savedInstanceState == null){
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(navStatus != 0){
                        fragment = new FragmentHome();
                        navStatus = 0;
                    }
                    break;
                case R.id.navigation_tour:
                    if(navStatus != 1){
                        fragment = new FragmentTour();
                        navStatus = 1;
                    }
                    break;
                case R.id.navigation_ruangkito:
                    if(navStatus != 2){
                        fragment = new FragmentRuang();
                        navStatus = 2;
                    }
                    break;
                case R.id.navigation_account:
                    if(navStatus != 3){
                        fragment = new FragmentAbout();
                        navStatus = 3;
                    }
                    break;
                default:
                    fragment = new FragmentHome();
                    navStatus = 0;
                    break;
            }
            if(fragment == null){
                fragment = new FragmentHome();
                navStatus = 0;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            return true;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //}
    }

}
