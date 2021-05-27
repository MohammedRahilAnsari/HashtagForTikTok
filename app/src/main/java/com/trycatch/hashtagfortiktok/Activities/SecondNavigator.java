package com.trycatch.hashtagfortiktok.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.trycatch.hashtagfortiktok.R;
import com.trycatch.hashtagfortiktok.fragment.Fragment_Home;
import com.trycatch.hashtagfortiktok.fragment.Fragment_Setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondNavigator extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_navigator);

        BottomNavigationView bottomNav = new BottomNavigationView( this);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Home()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new Fragment_Home();
            switch (item.getItemId()) {

                case R.id.tiktok_tag:
                    selectedFragment = new Fragment_Home();
                    break;

                case R.id.copy_tag:
                    Toast.makeText(context, "Copy That", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.share_tag:
                    selectedFragment = new Fragment_Setting();
                    break;

                default:
                    Toast.makeText(context, "Unexpected value: " + item.getItemId(), Toast.LENGTH_SHORT).show();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}