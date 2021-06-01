package com.example.minimo2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimo2.io.MyApiAdapter;
import com.example.minimo2.responses.Badge;
import com.example.minimo2.responses.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter ListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = (RecyclerView) findViewById(R.id.RV);
        ListAdapter = new ListAdapter(getApplicationContext());

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}