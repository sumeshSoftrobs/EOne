package com.example.eone.mainLayouts.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eone.JSONResponse.JSONResponse;
import com.example.eone.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Validator;

public class HomeVideoListAdapter extends RecyclerView.Adapter<HomeVideoListAdapter.VideoHolder> {
    List<JSONResponse> allVideos = new ArrayList<>();
    Bitmap bitmap;
    Context context;
    private OnItemClickListener listener;

    public HomeVideoListAdapter(List<JSONResponse> allVideos, Context context) {
        this.allVideos = allVideos;
        this.context = context;
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        ImageView videoImage;
        CardView cardView;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            videoTitle = (TextView)itemView.findViewById(R.id.videoTitle);
            videoImage = (ImageView)itemView.findViewById(R.id.thumbnail);
            cardView = (CardView)itemView.findViewById(R.id.mainCard);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(allVideos.get(position));
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_home_layout,parent,false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        JSONResponse jsonResponse = allVideos.get(position);
        String imgString = jsonResponse.getImgUrl();

        try {

            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imgString).getContent());
            holder.videoImage.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = jsonResponse.getTitle();
        holder.videoTitle.setText(title);
       // holder.videoImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return allVideos.size();
    }
    public interface OnItemClickListener{
        void onItemClick(JSONResponse jsonResponse);
    }
    public void setOnItemClickListerner(OnItemClickListener listener){
        this.listener = listener;
    }
    public void clear(){
            allVideos.clear();
    }


}
