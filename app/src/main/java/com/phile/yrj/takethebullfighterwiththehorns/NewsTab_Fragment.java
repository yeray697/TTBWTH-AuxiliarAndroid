package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phile.yrj.takethebullfighterwiththehorns.adapter.NewsAdapter;
import com.phile.yrj.takethebullfighterwiththehorns.model.New;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public class NewsTab_Fragment extends ListFragment {
    NewsAdapter adapter;
    boolean order = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_tab, container,false);
        adapter = new NewsAdapter(getActivity().getApplicationContext());
        updateList();
        setHasOptionsMenu(true);
        return v;
    }

    private void updateList(){
        setListAdapter(adapter);
        sortNews();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        New newAux = ((New) getListView().getItemAtPosition(position));
        Intent intent = new Intent(getActivity().getApplicationContext(), New_Activity.class);
        intent.putExtra("new", newAux);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_sort_by_date:
                sortNews();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortNews() {
        if (order){
            adapter.sort(New.ASC_DATE_COMPARATOR);
        } else  {
            adapter.sort(New.DESC_DATE_COMPARATOR);
        }
        order = !order;
        adapter.notifyDataSetChanged();
    }
}
