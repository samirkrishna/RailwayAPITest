package com.example.chsamirkrishna.railwayapitest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PnrStatusAPI {

    @GET("v2/pnr-status/pnr/{pnrno}/apikey/3w7kegzbz0/")
    Call<PnrStatus> getPnrInfo(@Path("pnrno")String pno);//make sure that we must import retrofit call method
}
