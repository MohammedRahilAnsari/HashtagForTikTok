package com.trycatch.hashtagfortiktok.Activities;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.trycatch.hashtagfortiktok.Adapter.ViewPagerAdapter;
import com.trycatch.hashtagfortiktok.Database.FavDatabase;
import com.trycatch.hashtagfortiktok.R;
import com.trycatch.hashtagfortiktok.fragment.Fragment_Home;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FavDatabase FavDatabase;
    private DrawerLayout myDrawer;
    private ActionBarDrawerToggle mytogle;
    private NavigationView nv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FavDatabase = Room.databaseBuilder(getApplicationContext(), FavDatabase.class, "myfavdb").allowMainThreadQueries().build();

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new Fragment_Home(), "Home");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        nv = findViewById(R.id.vNavigation);
        myDrawer = findViewById(R.id.drawer_layout);
        mytogle = new ActionBarDrawerToggle(MainActivity.this, myDrawer, R.string.Open, R.string.Close);

        myDrawer.addDrawerListener(mytogle);
        mytogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_darkmode:
                        Toast.makeText(MainActivity.this, "COMING SOON", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_rate:
                        Toast.makeText(MainActivity.this, "RATE US ON PLAYSTORE", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_settings:
                        Toast.makeText(MainActivity.this, "SETTINGS", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this, "PROJECT BY TRYCATCH CLASSES\nDEVELOPED BY MOHAMMED RAHIL ANSARI", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
            return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mytogle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}