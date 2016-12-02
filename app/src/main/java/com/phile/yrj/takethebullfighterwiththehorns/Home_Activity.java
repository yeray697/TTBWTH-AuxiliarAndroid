package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
import com.phile.yrj.takethebullfighterwiththehorns.adapter.CustomPagerAdapter;

public class Home_Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    CustomPagerAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_home);
        tabLayout.addTab(tabLayout.newTab().setText("Noticias"));
        tabLayout.addTab(tabLayout.newTab().setText("Ranking"));
        tabLayout.addTab(tabLayout.newTab().setText("Galería"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.viewpager_home);
        adapter = new CustomPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem login = menu.findItem(R.id.action_login);
        MenuItem signup = menu.findItem(R.id.action_close_session);
        boolean user_logged = false;
        if (((Login_Application)getApplicationContext()).getUser() != null)
            user_logged = true;
        login.setVisible(!user_logged);
        signup.setVisible(user_logged);

        MenuItem ordernews = menu.findItem(R.id.action_sort_by_date);
        boolean newsactived = false;
        if (tabLayout.getSelectedTabPosition() == 0)
            newsactived = true;
        ordernews.setVisible(newsactived);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_settings:
                intent = new Intent(Home_Activity.this,Settings_Activity.class);
                startActivity(intent);
                break;

            case R.id.action_support:
                Toast.makeText(this, "Sin implementar aún", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search_user:
                Toast.makeText(this, "Sin implementar aún", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about_me:
                intent = new Intent(Home_Activity.this,About_Activity.class);
                startActivity(intent);
                break;
            case R.id.action_login:
                intent = new Intent (Home_Activity.this,Login_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_close_session:
                ((Login_Application)getApplicationContext()).closeSession();
                intent = new Intent (Home_Activity.this,Login_Activity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (((Login_Application)getApplicationContext()).getUser() == null) {
            Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
            startActivity(intent);
            finish();
        } else
            super.onBackPressed();
    }
}
