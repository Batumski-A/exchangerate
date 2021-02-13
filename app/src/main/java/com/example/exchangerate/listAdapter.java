package com.example.exchangerate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;

public class listAdapter extends ArrayAdapter<exchange> {
    private Context context;
    int mResourse;

    public listAdapter(Context context, int resource, ArrayList<exchange> objects) {
        super(context, resource,objects);
        this.context = context;
        mResourse = resource;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResourse,parent,false);
        String Currency = getItem(position).getCurrency();
        String CurencyFullName = getItem(position).getCurrencyFullName();
        String Price = getItem(position).getPrice();
        String Distance = getItem(position).getDistance();
        String Upanddown = getItem(position).getImage();

        TextView currency = (TextView)convertView.findViewById(R.id.lCurrency);
        TextView currencyFullName = (TextView)convertView.findViewById(R.id.lCurrencyFullName);
        TextView price = (TextView)convertView.findViewById(R.id.lPrice);
        TextView distance = (TextView)convertView.findViewById(R.id.lDistance);
        ImageView upanddown = (ImageView)convertView.findViewById(R.id.lUpAndDown);
        if(Upanddown.contains("red")){
            upanddown.setImageResource(R.drawable.red);
        }else{
            upanddown.setImageResource(R.drawable.green);
        }
        currency.setText(Currency);
        currencyFullName.setText(CurencyFullName);
        price.setText(Price);
        distance.setText(Distance);
//        upanddown.draw()
        return convertView;


    }
}
