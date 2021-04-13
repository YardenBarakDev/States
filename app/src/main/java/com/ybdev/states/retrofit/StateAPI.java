package com.ybdev.states.retrofit;

import com.ybdev.states.model.State;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StateAPI {

    @GET("all?fields=name;nativeName;borders;flag;latlng;area;alpha3Code")
    Call<ArrayList<State>> loadStatesInfo();

}
