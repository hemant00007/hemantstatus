package com.example.video_status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.video_status.Adapter.VideoAdapter;
import com.example.video_status.Model.VideoModel;
import com.example.video_status.Retrofit.Retrofit_Endpoint;
import com.example.video_status.Retrofit.Retrofit_client;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ShimmerFrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_view();

        api_call();
    }



    private void init_view() {

        recyclerView = findViewById(R.id.recyclerview);
        layout = findViewById(R.id.shimmerFrame);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        toolbar = findViewById(R.id.toolbar);

    }

    @Override
    public void onResume() {
        super.onResume();
        layout.startShimmer();
    }

    @Override
    protected void onPause() {
        layout.stopShimmer();
        super.onPause();
    }

    private void api_call() {

        Retrofit_Endpoint endpoint = Retrofit_client.getRetrofit().create(Retrofit_Endpoint.class);
        Call<List<VideoModel>> call = endpoint.getvideolist();
        call.enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {
                List<VideoModel> videoModels = response.body();
                recyclerView.setAdapter(new VideoAdapter(videoModels,getApplicationContext()));
                layout.stopShimmer();
                layout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

//    @Override
//    public void onItemClick(int position) {
//
//    }
}
