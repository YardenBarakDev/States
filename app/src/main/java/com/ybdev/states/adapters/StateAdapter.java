package com.ybdev.states.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestBuilder;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.ybdev.states.CallBacks.ListCallBack;
import com.ybdev.states.R;
import com.ybdev.states.model.State;
import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {


    private final Context context;
    private ArrayList<State> stateArrayList;
    private ListCallBack listCallBack;
    private final RequestBuilder<PictureDrawable> requestBuilder;

    public StateAdapter(Context context) {
        this.context = context;

        //give glide the option to load images
        requestBuilder = GlideToVectorYou.init().with(context).getRequestBuilder();
    }

    public void setClickListener(ListCallBack listCallBack){
        this.listCallBack = listCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.state_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        State state = stateArrayList.get(position);

        requestBuilder
                .load(state.getFlag())
                .into(holder.flag);

        holder.name.setText(state.getName());
        holder.nativeName.setText(state.getNativeName());
    }

    @Override
    public int getItemCount() {
        return stateArrayList.size();
    }

    public void setArray(ArrayList<State> stateArrayList){this.stateArrayList = stateArrayList;}

    private State getItem(int position) {
        return stateArrayList.get(position);
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
                if (listCallBack != null) {
                    listCallBack.showBorders(getItem(getAdapterPosition()));
                    listCallBack.sentMainCountry(getItem(getAdapterPosition()));
                }
            });
        }
    }
}
