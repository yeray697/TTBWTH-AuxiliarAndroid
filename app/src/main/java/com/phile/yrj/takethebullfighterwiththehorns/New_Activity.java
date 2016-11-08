package com.phile.yrj.takethebullfighterwiththehorns;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.phile.yrj.takethebullfighterwiththehorns.interfaces.INewMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.Comment;
import com.phile.yrj.takethebullfighterwiththehorns.model.New;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.NewPresenter;

import java.util.ArrayList;

public class New_Activity extends AppCompatActivity implements INewMvp.View{
    INewMvp.Presenter presenter;
    TextView tvTitle, tvBody, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        presenter = new NewPresenter(this);
        Bundle bundle = getIntent().getExtras();
        tvTitle = (TextView) findViewById(R.id.tvTitle_New);
        tvBody = (TextView) findViewById(R.id.tvBody_New);
        tvDate = (TextView) findViewById(R.id.tvDate_new);
        ArrayList<Comment> comments;
        New newSelecter = getIntent().getParcelableExtra("new");
        tvTitle.setText(newSelecter.getTitle());
        tvBody.setText(newSelecter.getBody());
        tvDate.setText(newSelecter.getFormatedDate());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
