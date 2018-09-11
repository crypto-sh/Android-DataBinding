package ir.prime.android;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

import ir.prime.R;
import ir.prime.baseClass.BaseActivity;
import ir.prime.databinding.ActivityDetailsSerialBinding;
import ir.prime.fragment.ListFragment;
import ir.prime.model.Api;
import ir.prime.model.ItemsDataParcelable;
import ir.prime.model.SeasonModel;
import ir.prime.utils.PublicFunction;
import ir.prime.utils.PublicValue;
import ir.prime.utils.RequestParams;
import ir.prime.utils.ResponseHandler;
import ir.prime.utils.RestClient;


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
            getDetailsData(serial.mId);
        }
    }

    private void getDetailsData(String mediaId){
        String url = PublicValue.getBaseUrl() + "getSerialDetails";
        RestClient.Post(DetailsSerialActivity.this, url, new RequestParams(), new ResponseHandler() {
            @Override
            public void onSuccess(byte[] result) {
                try {
                    Api.SerialDetails details = Api.SerialDetails.parseFrom(result);
                    setDetailsBinding(details);
                } catch (InvalidProtocolBufferException e) {
                    PublicFunction.LogData(false, TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(int errorCode, String errorMsg) {
                PublicFunction.LogData(false,TAG,errorMsg);
            }
        });
    }

    private void setDetailsBinding(Api.SerialDetails model) {
        if (model == null) {
            return;
        }
        for (Api.Season item : model.getSeasonsList()){
            mSeasons.add(new SeasonModel(item));
        }
        pagerSerialAdapter adapter = new pagerSerialAdapter(DetailsSerialActivity.this,mSeasons,getSupportFragmentManager());
        binding.setAdapterHandler(adapter);
        binding.setSeasonHandler(binding.pager);
    }

    public class pagerSerialAdapter extends FragmentPagerAdapter {

        Context context;

        List<SeasonModel> seasonModels;

        public pagerSerialAdapter(Context context,List<SeasonModel> seasons,FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context      = context;
            this.seasonModels = seasons;
        }

        @Override
        public Fragment getItem(int position) {
            SeasonModel season = seasonModels.get(position);
            binding.setSeason(season);
            return ListFragment.newInstance(season.mTitle,season.listParcelable);
        }

        @Override
        public int getCount() {
            if (seasonModels == null){
                return 0;
            }
            return seasonModels.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (seasonModels == null || seasonModels.size() < position){
                return "";
            }
            return seasonModels.get(position).getTitle();
        }
    }


    public class DetailsHandler {

//        public void AddtoWatchList(DetailsMovieModel details) {
//            PublicFunction.LogData(true, TAG, "AddToWatchList : " + details.mTitle);
//        }
//
//        public void PlayTrailer(DetailsMovieModel details) {
//            PublicFunction.LogData(true, TAG, "PlayTrailer : " + details.mTitle);
//        }
    }
}
