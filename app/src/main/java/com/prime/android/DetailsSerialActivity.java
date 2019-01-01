package com.prime.android;


import android.os.Bundle;

import com.prime.baseClass.BaseActivity;
import com.prime.model.ItemsDataParcelable;
import com.prime.utils.PublicFunction;
import com.prime.utils.PublicValue;

import java.util.ArrayList;
import java.util.List;

import com.prime.R;
import com.prime.databinding.ActivityDetailsSerialBinding;

import com.prime.model.SeasonModel;


public class DetailsSerialActivity extends BaseActivity {

    ItemsDataParcelable serial;

    ActivityDetailsSerialBinding binding;

    List<SeasonModel> mSeasons = new ArrayList<>();

    final String TAG = DetailsMovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setContentView(this,R.layout.activity_details_serial);
        if (getIntent().hasExtra(PublicValue.EXTRA_DETAILS)) {
            serial = getIntent().getParcelableExtra(PublicValue.EXTRA_DETAILS);
            getDetailsData(serial.id);
        }
    }

    private void getDetailsData(String mediaId){
        String url = PublicValue.getBaseUrl() + "getSerialDetails";
//        RestClient.Post(DetailsSerialActivity.this, url, new RequestParams(), new ResponseHandler() {
//            @Override
//            public void onSuccess(byte[] result) {
////                try {
////                    Api.SerialDetails details = Api.SerialDetails.parseFrom(result);
////                    setDetailsBinding(details);
////                } catch (Exception e) {
////                    PublicFunction.LogData(false, TAG, e.getMessage());
////                }
//            }
//
//            @Override
//            public void onFailure(int errorCode, String errorMsg) {
//                PublicFunction.LogData(false,TAG,errorMsg);
//            }
//        });
    }

    private void setDetailsBinding() {
//        if (model == null) {
//            return;
//        }
//        for (Api.Season item : model.getSeasonsList()){
//            mSeasons.add(new SeasonModel(item));
//        }
//        PagerSerialAdapter adapter = new PagerSerialAdapter(mSeasons,getSupportFragmentManager());
//        //binding.setSeason(season);
//        binding.setAdapterHandler(adapter);
//        binding.setSeasonHandler(binding.pager);
    }



    public class DetailsHandler {

//        public void AddtoWatchList(DetailsMovieModel details) {
//            PublicFunction.LogData(true, TAG, "AddToWatchList : " + details.title);
//        }
//
//        public void PlayTrailer(DetailsMovieModel details) {
//            PublicFunction.LogData(true, TAG, "PlayTrailer : " + details.title);
//        }
    }
}
