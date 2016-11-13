package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.adapter.PagerAdapter;

public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Noticias"));
        tabLayout.addTab(tabLayout.newTab().setText("Ranking"));
        tabLayout.addTab(tabLayout.newTab().setText("Galería"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
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
                finish();
                break;
            case R.id.action_close_session:
                ((Login_Application)getApplicationContext()).closeSession();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
