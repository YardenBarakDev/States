package com.ybdev.states.retrofit;

import android.util.Log;
import com.ybdev.states.CallBacks.RetrofitCallBack;
import com.ybdev.states.model.State;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateController {

    private final RetrofitCallBack retrofitCallBack;


    public StateController(RetrofitCallBack retrofitCallBack) {
        this.retrofitCallBack = retrofitCallBack;
    }


    public void getAllStates() {
        String BASE_URL = "https://restcountries.eu/rest/v2/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StateAPI stateAPI = retrofit.create(StateAPI.class);

        Call<ArrayList<State>> call = stateAPI.loadStatesInfo();

        call.enqueue(new Callback<ArrayList<State>>() {
            @Override
            public void onResponse(Call<ArrayList<State>> call, Response<ArrayList<State>> response) {
                if (response.isSuccessful()) {
                    retrofitCallBack.listFromRetrofit(response.body());
                }
                else
                    Log.d("jjjj", "Retrofit onResponse: fail");
            }

            @Override
            public void onFailure(Call<ArrayList<State>> call, Throwable t) {
                Log.d("jjjj", "Retrofit onFailure: fail");
            }
        });
    }
}