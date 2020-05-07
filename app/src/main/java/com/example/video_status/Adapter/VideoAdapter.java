package com.example.video_status.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.video_status.Model.VideoModel;
import com.example.video_status.R;
import com.example.video_status.RecyclerViewClickListner;
import com.example.video_status.Video_player;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;

import retrofit2.Callback;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.video_viewholder> {

    List<VideoModel> videoModels;
    private Context context;
 //   private RecyclerViewClickListner recyclerViewClickListner;

    public VideoAdapter(List<VideoModel> videoModels, Context context) {
        this.videoModels = videoModels;
        this.context = context;
       /* this.recyclerViewClickListner = recyclerViewClickListner;*/
    }

    @NonNull
    @Override
    public video_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_itemview,parent,false);


        return new video_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull video_viewholder holder, final int position) {

        holder.video_title.setText(videoModels.get(position).getTitle());

        Glide.with(context).load(videoModels.get(position).getImageUrl()).into(holder.avtar_image);

        holder.play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Video_player.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("video_url",videoModels.get(position).getVideoUrl().toString());
                context.startActivity(intent);
                playvideo();
            }
        });

    }

    private void playvideo() {


    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public class video_viewholder extends RecyclerView.ViewHolder {


        TextView video_title;
        ImageView avtar_image,play_btn;
        private PlayerView playerView;
        private ProgressBar progressBar_player;

        public video_viewholder(@NonNull View itemView) {
            super(itemView);
            play_btn=itemView.findViewById(R.id.imageView_play_my_video);
            video_title = itemView.findViewById(R.id.video_title);
            avtar_image = itemView.findViewById(R.id.video_avtar_id);
            playerView = itemView.findViewById(R.id.player_view);
            progressBar_player = itemView.findViewById(R.id.progressbar_player_scd_fragment);

           // itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            Toast.makeText(view.getContext(), (CharSequence) videoModels.get(getAdapterPosition()),Toast.LENGTH_LONG).show();
//        }
    }
}
