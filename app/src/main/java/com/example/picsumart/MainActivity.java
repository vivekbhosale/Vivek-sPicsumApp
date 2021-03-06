package com.example.picsumart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.picsumart.model.Photo;
import com.example.picsumart.network.Api;
import com.example.picsumart.network.Api_Interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String base_imageurl = "https://picsum.photos/300/300?image=";

    GridView gridView;
    Custom_adapter custom_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.grid_view);

        Call<List<Photo>> call = Api.api_interface().getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photos = response.body();
                for (Photo p: photos) {

                    //create imageurl from id
                    String imageurl = base_imageurl + p.getId();

                    //set imageurl
                    p.setLinkurl(imageurl);
                }

                //add body to adapter
                custom_adapter = new Custom_adapter(response.body(), MainActivity.this);
                gridView.setAdapter(custom_adapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Error occurred",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class Custom_adapter extends BaseAdapter{

        public List<Photo> photoList;
        public Context context;

        //constructors
        public Custom_adapter(List<Photo> photoList, Context context) {
            this.photoList = photoList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //create layout reference variable
            View setview = LayoutInflater.from(context).inflate(R.layout.photo_data, null);

            TextView textView = setview.findViewById(R.id.textview);
            ImageView imageView = setview.findViewById(R.id.imageview);

            //set value to textview
            textView.setText(photoList.get(i).getAuthor());

            //load image form url using glide
            Glide.with(context).load(photoList.get(i).getLinkurl()).into(imageView);

            return setview;
        }
    }
}