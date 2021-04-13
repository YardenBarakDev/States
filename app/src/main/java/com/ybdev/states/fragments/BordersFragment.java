package com.ybdev.states.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestBuilder;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ybdev.states.CallBacks.ListCallBack;
import com.ybdev.states.R;
import com.ybdev.states.adapters.BordersAdapter;
import com.ybdev.states.model.State;

import java.util.ArrayList;

public class BordersFragment extends Fragment {

    protected View view;
    private ImageView BordersFragment_IMG_flag;
    private TextView BordersFragment_LBL_name;
    private RecyclerView BordersFragment_RecyclerView;
    private FloatingActionButton BordersFragment_FloatingActionButton;
    private MapView BordersFragment_MapView;
    private GoogleMap googleMap;
    private BordersAdapter bordersAdapter;
    private LinearLayout BordersFragment_LL_chosenState;
    private final ListCallBack listCallBack;

    public BordersFragment(ListCallBack listCallBack){
        this.listCallBack = listCallBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_borders, container, false);

        findViews();
        onClicks();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BordersFragment_MapView.onCreate(savedInstanceState);
        BordersFragment_MapView.getMapAsync(map -> {
            googleMap = map;

            //set custom map
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));

            initRecyclerView();
        });
    }


    private void onClicks() {
        BordersFragment_FloatingActionButton.setOnClickListener(view -> {
            listCallBack.getBackToList();
            clearArrayList();
        });
    }

    private void initRecyclerView() {
        bordersAdapter = new BordersAdapter(getContext(), googleMap);
        BordersFragment_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BordersFragment_RecyclerView.setAdapter(bordersAdapter);
    }


    @SuppressLint("SetTextI18n")
    public void getMainState(State state){
        //Glide for svg
        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou.init().with(getContext()).getRequestBuilder();

        requestBuilder
                .load(state.getFlag())
                .into(BordersFragment_IMG_flag);

        BordersFragment_LBL_name.setText(state.getName() + "'s area");
        LatLng latLng = new LatLng(state.getLatlng().get(0), state.getLatlng().get(1));
        bordersAdapter.moveCamera(latLng, state.getMapZoomLevel());
        BordersFragment_LL_chosenState.setOnClickListener(view -> bordersAdapter.moveCamera(latLng, state.getMapZoomLevel()));
    }

    public void getBorders(ArrayList<State> stateArrayList){
        bordersAdapter.setStateArrayList(stateArrayList);
        bordersAdapter.notifyDataSetChanged();
    }

    private void clearArrayList(){
        bordersAdapter.clearArrayList();
    }

    private void findViews() {
        BordersFragment_RecyclerView = view.findViewById(R.id.BordersFragment_RecyclerView);
        BordersFragment_MapView = view.findViewById(R.id.BordersFragment_MapView);
        BordersFragment_IMG_flag = view.findViewById(R.id.BordersFragment_IMG_flag);
        BordersFragment_LBL_name = view.findViewById(R.id.BordersFragment_LBL_name);
        BordersFragment_FloatingActionButton = view.findViewById(R.id.BordersFragment_FloatingActionButton);
        BordersFragment_LL_chosenState = view.findViewById(R.id.BordersFragment_LL_chosenState);
    }

    /**
     * for google maps
     */
    @Override
    public void onResume() {
        super.onResume();
        if (BordersFragment_MapView != null)
            BordersFragment_MapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BordersFragment_MapView != null)
            BordersFragment_MapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BordersFragment_MapView != null)
            BordersFragment_MapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BordersFragment_MapView != null)
            BordersFragment_MapView.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BordersFragment_MapView != null)
            BordersFragment_MapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (BordersFragment_MapView != null)
            BordersFragment_MapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        BordersFragment_MapView.onSaveInstanceState(outState);
    }
}