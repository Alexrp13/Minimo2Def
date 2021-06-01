package com.example.minimo2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minimo2.io.MyApiAdapter;
import com.example.minimo2.responses.Badge;
import com.example.minimo2.responses.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView ;
    ListAdapter ListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.USUARIO);
        ImageView usrImage = (ImageView)findViewById(R.id.userimage);
        TextView iduser = (TextView)findViewById(R.id.textView);
        TextView usrname = (TextView)findViewById(R.id.textView2);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        ListAdapter = new ListAdapter(getApplicationContext());


        // Capture the layout's TextView and set the string as its text
        Call<UserResponse> call = MyApiAdapter.getApiService().getUserInfo(message);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse res = response.body();
                if(res != null){
                    Glide.with(getApplicationContext()).load(res.getAvatar().into(usrImage));
                    iduser.setText(""+res.getUsername());
                    usrname.setText(""+res.getName());

                    Call<List<Badge>> Badges = MyApiAdapter.getApiService().getBadges();
                    Badges.enqueue(new Callback<List<Badge>>() {
                        @Override
                        public void onResponse(Call<List<Badge>> call, Response<List<Badge>> response) {
                            List<Badge> BadgeList = response.body();
                            loadData(BadgeList);
                            setAdapter();
                        }

                        @Override
                        public void onFailure(Call<List<Badge>> call, Throwable t) {

                        }
                    });

                }
                else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void loadData(List<Badge> list){ ListAdapter.setBadgeList(list); }
    public void setAdapter(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(manager);
        this.recyclerView.setAdapter(this.ListAdapter);
    }
}

