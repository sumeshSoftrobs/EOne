package com.example.eone.mainLayouts.home;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.eone.JSONResponse.JSONResponse;
import com.example.eone.R;
import com.example.eone.VideoPlayer.VideoPlayerActivity;
import com.google.android.gms.common.internal.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private RecyclerView recyclerView;
    String iD,title,desc,imgUrl;
    List<JSONResponse> allVideos;
    String APIKey = "AIzaSyDZSyRA_M4NtoBytqQN-CYsUe_aYjSJTCw";
    String U_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCIwFjwMjI0y7PDBVEO9-bkQ&key=AIzaSyDZSyRA_M4NtoBytqQN-CYsUe_aYjSJTCw";
    HomeVideoListAdapter adapter;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerHome);
        allVideos = new ArrayList<>();
        extractVideos();
    }

    private void extractVideos() {
        OkHttpClient okHttpClient = new OkHttpClient();
        AndroidNetworking.initialize(getContext(), okHttpClient);
        AndroidNetworking.get(U_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        int count = 0;


                        //progressBar.setVisibility(View.GONE);

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    Log.d("YouTube Video List : ", jsonObject.toString());
                                    //JSONObject  jsonObject = response.getJSONObject("items");

                                    JSONArray items = jsonObject.getJSONArray("items");
                                    for (int j = 0; j < items.length(); j++) {
                                        //yourArrayList.add(participants.getString(i))
                                        JSONObject jsonValue = items.getJSONObject(j);
                                        JSONObject id = jsonValue.getJSONObject("id");

                                            iD =  id.getString("videoId");
                                        JSONObject snippet = jsonValue.getJSONObject("snippet");
                                            title = snippet.getString("title");
                                            desc = snippet.getString("description");
                                        JSONObject img = snippet.getJSONObject("thumbnails");
                                        JSONObject image = img.getJSONObject("high");
                                            imgUrl = image.getString("url");
                                        JSONResponse jsonResponse = new JSONResponse(title,imgUrl,desc,iD);
                                        allVideos.add(jsonResponse);

                                    }
                                } catch(JSONException e) {
                                    // Handle exception.
                                }

                        adapter = new HomeVideoListAdapter(allVideos,getContext());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListerner(new HomeVideoListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(JSONResponse jsonResponse) {
                                Intent i = new Intent(getActivity(), VideoPlayerActivity.class);
                                i.putExtra("id",jsonResponse.getVideoId());
                                i.putExtra("title",jsonResponse.getTitle());
                                i.putExtra("desc",jsonResponse.getDesc());
                                i.putExtra("imgUrl",jsonResponse.getImgUrl());
                                startActivity(i);

                            }
                        });

                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("Error Message",error.toString());
                        // progressBar.setVisibility(View.GONE);
                    }
                });
    }
}