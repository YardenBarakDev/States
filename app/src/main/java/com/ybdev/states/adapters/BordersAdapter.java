package com.ybdev.states.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestBuilder;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.ybdev.states.R;
import com.ybdev.states.model.State;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BordersAdapter extends RecyclerView.Adapter<BordersAdapter.ViewHolder>{


    private final Context context;
    private final ArrayList<State> stateArrayList;
    private final GoogleMap googleMap;
    private final RequestBuilder<PictureDrawable> requestBuilder;

    public BordersAdapter(Context context, GoogleMap googleMap) {
        this.context = context;
        this.stateArrayList = new ArrayList<>();
        this.googleMap = googleMap;

        //give glide the option to load images
        requestBuilder = GlideToVectorYou.init().with(context).getRequestBuilder();
    }

    @NonNull
    @Override
    public BordersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.state_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BordersAdapter.ViewHolder holder, int position) {
        State state = stateArrayList.get(position);

        requestBuilder
                .load(state.getFlag())
                .into(holder.flag);

        holder.name.setText(state.getName());
        holder.nativeName.setText(state.getNativeName());
    }


    public void clearArrayList(){stateArrayList.clear();}

    public void setStateArrayList(ArrayList<State> arrayList){
        stateArrayList.addAll(arrayList);
    }

    private State getItem(int position) {
        return stateArrayList.get(position);
    }
    @Override
    public int getItemCount() {
        return stateArrayList.size();
    }

    public void moveCamera(LatLng latLng, float distance){
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, distance));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView name;
        TextView nativeName;

        ViewHolder(View view) {
            super(view);

            //find views
            flag = view.findViewById(R.id.flag);
            name = view.findViewById(R.id.name);
            nativeName = view.findViewById(R.id.nativeName);

            view.setOnClickListener(view1 -> {
                if (stateArrayList != null && getItem(getAdapterPosition()).getLatlng()  != null && getItem(getAdapterPosition()).getLatlng().size() > 1){
                    LatLng latLng = new LatLng(getItem(getAdapterPosition()).getLatlng().get(0), getItem(getAdapterPosition()).getLatlng().get(1));
                    moveCamera(latLng,  getItem(getAdapterPosition()).getMapZoomLevel());
                }
            });
        }
    }
}
