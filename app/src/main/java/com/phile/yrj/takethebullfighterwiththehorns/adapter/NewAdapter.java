package com.phile.yrj.takethebullfighterwiththehorns.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phile.yrj.takethebullfighterwiththehorns.Login_Application;
import com.phile.yrj.takethebullfighterwiththehorns.R;
import com.phile.yrj.takethebullfighterwiththehorns.model.New;

import java.io.InputStream;

/**
 * Created by yeray697 on 7/11/16.
 */

public class NewAdapter extends ArrayAdapter<New> {


    public NewAdapter(Context context) {
        super(context, R.layout.item_new_list_row,((Login_Application)context.getApplicationContext()).getNews());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ProductHolder productHolder;
        if (item == null) {
            //1.Create an inflater object that it is initialized to the Context LayoutInflater
            //LayoutInflater inflater = LayoutInflater.from(context);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //2. Inflate the view. Create memory in View Object that contains XML widgets
            item = inflater.inflate(R.layout.item_new_list_row, null);
            //3. Setting widgets
            productHolder = new ProductHolder();
            productHolder.img = (ImageView) item.findViewById(R.id.imgNew_Row);
            productHolder.tvName = (TextView) item.findViewById(R.id.tvTitleNew_Row);
            productHolder.tvDate = (TextView) item.findViewById(R.id.tvDateNew_Row);
            item.setTag(productHolder);
        }else
            productHolder = (ProductHolder) item.getTag();
        //Setting data adapter to widgets
        new DownloadImageTask(productHolder.img).execute(getItem(position).getImgUrl());
        productHolder.tvName.setText(getItem(position).getTitle());
        productHolder.tvDate.setText(getItem(position).getFormatedDate());

        return item;
    }
    /**
     * Internal classh that contains XML file widgets
     */
    class ProductHolder{
        TextView tvName;
        TextView tvDate;
        ImageView img;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
