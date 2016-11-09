package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phile.yrj.takethebullfighterwiththehorns.adapter.NewsAdapter;
import com.phile.yrj.takethebullfighterwiththehorns.model.New;

/**
 * Created by yeray697 on 7/11/16.
 */

public class NewsTab_Fragment extends ListFragment {
    NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_tab, container,false);
        adapter = new NewsAdapter(getActivity().getApplicationContext());
        updateList();
        return v;
    }

    private void updateList(){
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        New newAux = ((New) getListView().getItemAtPosition(position));
        Intent intent = new Intent(getActivity().getApplicationContext(), New_Activity.class);
        intent.putExtra("new", newAux);
        startActivity(intent);
    }
}
