package com.ybdev.states.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.ybdev.states.CallBacks.ListCallBack;
import com.ybdev.states.R;
import com.ybdev.states.CallBacks.RetrofitCallBack;
import com.ybdev.states.retrofit.StateController;
import com.ybdev.states.fragments.BordersFragment;
import com.ybdev.states.fragments.ListFragment;
import com.ybdev.states.model.State;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListFragment listFragment;
    private BordersFragment bordersFragment;
    private HashMap<String, State> statesHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statesHashMap = new HashMap<>();
        initFragments();
        //retrofit
        StateController stateController = new StateController(retrofitCallBack);
        stateController.getAllStates();
    }

    //transfer data from the web using retrofit
    RetrofitCallBack retrofitCallBack = new RetrofitCallBack() {

        //get an array of all the countries (name, native name and borders)
        @Override
        public void listFromRetrofit(ArrayList<State> stateArrayList) {
            for (State state : stateArrayList)
                statesHashMap.put(state.getAlpha3Code(), state);
            listFragment.setStateArray(stateArrayList);

            //show screen after the splash screen
            setTheme(R.style.Theme_States);
        }
    };

    //transfer data between the fragments and hide/show them
    ListCallBack listCallBack = new ListCallBack() {
        @Override
        public void getBackToList() {
            getSupportFragmentManager().beginTransaction().hide(bordersFragment).commit();
            getSupportFragmentManager().beginTransaction().show(listFragment).commit();
        }

        @Override
        public void showBorders(State state) {
            if (state.getBorders().size() == 0) {
                Toast.makeText(getBaseContext(), "No borders", Toast.LENGTH_SHORT).show();
            }
            else{
                ArrayList<State> borderStatesArray = new ArrayList<>();
                for (String s : state.getBorders())
                    borderStatesArray.add(statesHashMap.get(s));

                for (State state1 : borderStatesArray)
                    Log.d("jjjj", "" + state1.getName());
                bordersFragment.getBorders(borderStatesArray);
            }
            getSupportFragmentManager().beginTransaction().show(bordersFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(listFragment).commit();
        }

        @Override
        public void sentMainCountry(State state) { bordersFragment.getMainState(state);}
    };

    private void initFragments() {
        listFragment = new ListFragment(listCallBack);
        bordersFragment = new BordersFragment(listCallBack);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.MainActivity_Fragment, listFragment);
        transaction.add(R.id.MainActivity_Fragment, bordersFragment);
        getSupportFragmentManager().beginTransaction().hide(bordersFragment).commit();
        transaction.commit();
    }
}