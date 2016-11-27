package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.adapter.CommentsAdapterRecycler;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.INewMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.Comment;
import com.phile.yrj.takethebullfighterwiththehorns.model.New;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.NewPresenter;

import java.util.ArrayList;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public class New_Activity extends AppCompatActivity implements INewMvp.View{
    INewMvp.Presenter presenter;
    TextView tvTitle, tvBody, tvDate;
    RecyclerView rvComments;
    EditText etComment;
    Button btSendComment;
    CommentsAdapterRecycler adapter;
    New newSelected;
    RelativeLayout rlComment;

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
        rvComments = (RecyclerView) findViewById(R.id.rvComments_New);
        ArrayList<Comment> comments;
        btSendComment = (Button) findViewById(R.id.btSendComment_New);
        etComment = (EditText) findViewById(R.id.etComment_New);
        rlComment = (RelativeLayout) findViewById(R.id.rlComment_New);
        newSelected = getIntent().getParcelableExtra("new");
        tvTitle.setText(newSelected.getTitle());
        tvBody.setText(newSelected.getBody());
        tvDate.setText(newSelected.getFormatedDate());
        adapter = new CommentsAdapterRecycler(this,newSelected.getId());
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setAdapter(adapter);
        btSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etComment.getText())) {
                    if (presenter.publishComment(etComment.getText().toString(), newSelected.getId())) {

                        adapter = new CommentsAdapterRecycler(New_Activity.this, newSelected.getId());
                        rvComments.setAdapter(adapter);
                        etComment.setText("");
                        //Closing keyboard
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        });
        if (((Login_Application)this.getApplicationContext()).getUser() == null){
            rlComment.setVisibility(View.GONE);
        } else {
            rlComment.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void setMessageError(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }
}
