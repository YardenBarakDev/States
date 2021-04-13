package com.ybdev.states.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ybdev.states.CallBacks.ListCallBack;
import com.ybdev.states.R;
import com.ybdev.states.adapters.StateAdapter;
import com.ybdev.states.model.State;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    protected View view;
    private  StateAdapter stateAdapter;
    private RecyclerView ListFragment_RecyclerView;
    private ArrayList<State> stateArrayList;
    private SearchView ListFragment_SearchView;
    private final ListCallBack listCallBack;

    public ListFragment(ListCallBack listCallBack){
        this.listCallBack = listCallBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_list, container, false);

        stateArrayList = new ArrayList<>();
        findViews();
        searchListener();

        return view;
    }


    private void searchListener() {
        ListFragment_SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                //no text
                if (s.length() == 0) {
                    stateAdapter.setArray(stateArrayList);
                    ListFragment_SearchView.clearFocus();
                }
                else{
                    ArrayList <State> filteredStateList = new ArrayList<>();
                    for (State state : stateArrayList){
                        if (state.getName().toLowerCase().startsWith(s))
                            filteredStateList.add(state);
                    }
                    stateAdapter.setArray(filteredStateList);
                }
                stateAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    public void setStateArray(ArrayList<State> states){
        stateArrayList.addAll(states);
        setRecyclerView();
    }

    private void setRecyclerView() {
        stateAdapter = new StateAdapter(getContext());
        stateAdapter.setArray(stateArrayList);
        stateAdapter.setClickListener(listCallBack);
        ListFragment_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFragment_RecyclerView.setAdapter(stateAdapter);
    }

    private void findViews() {
        ListFragment_RecyclerView = view.findViewById(R.id.ListFragment_RecyclerView);
        ListFragment_SearchView = view.findViewById(R.id.ListFragment_SearchView);
    }
}