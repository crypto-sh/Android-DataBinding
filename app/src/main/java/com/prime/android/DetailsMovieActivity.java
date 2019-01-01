package com.prime.android;


import android.os.Bundle;


import com.prime.adapter.RecyclerAdapterPageList;
import com.prime.baseClass.BaseActivity;

import com.prime.fragment.ListFragment;
import com.prime.model.ItemsDataParcelable;
import com.prime.utils.PublicFunction;
import com.prime.utils.PublicValue;

import com.prime.R;

import com.prime.custom_view.LayoutManagerCustom;
import com.prime.databinding.ActivityDetailsMovieBinding;

import com.prime.model.DetailsMovieModel;


public class DetailsMovieActivity extends BaseActivity {

    ItemsDataParcelable movie;

    ActivityDetailsMovieBinding binding;

    final static String TAG = DetailsMovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = setContentView(this, R.layout.activity_details_movie);
        binding.setHandler(new DetailsHandler());

        if (getIntent().hasExtra(PublicValue.EXTRA_DETAILS)) {
            movie = getIntent().getParcelableExtra(PublicValue.EXTRA_DETAILS);
            getDetailsData(movie.id);
        }

    }

    private void getDetailsData(String mediaId) {
        String url = PublicValue.getBaseUrl() + "getMovieDetails";
//        RestClient.Post(DetailsMovieActivity.this, url, new RequestParams(), new ResponseHandler() {
//            @Override
//            public void onSuccess(byte[] result) {
//                runOnUiThread(() -> {
////                    try {
////                        Api.MovieDetails details = Api.MovieDetails.parseFrom(result);
////                        setDetailsBinding(new DetailsMovieModel(details.getMovie()));
////                    } catch (Exception e) {
////                        PublicFunction.LogData(false, TAG, e.getMessage());
////                    }
//                });
//            }
//
//            @Override
//            public void onFailure(int errorCode, String errorMsg) {
//                PublicFunction.LogData(false, TAG, errorMsg);
//            }
//        });
    }

    private void setDetailsBinding(DetailsMovieModel model) {
        if (model == null) {
            return;
        }
        binding.setDetails(model);
        RecyclerAdapterPageList adapterPage = new RecyclerAdapterPageList((item, type) -> {

            PublicFunction.LogData(true,ListFragment.class.getSimpleName(),"onSelectedItem : " + item.title);

        }, model.detailsList.listRow);
        binding.recyclerView.setLayoutManager(new LayoutManagerCustom(DetailsMovieActivity.this,true));
        binding.setRecyclerAdapter(adapterPage);
    }

    public class DetailsHandler {
        public void AddtoWatchList(DetailsMovieModel details) {
            PublicFunction.LogData(true, TAG, "AddToWatchList : " + details.title);
        }

        public void PlayTrailer(DetailsMovieModel details) {
            PublicFunction.LogData(true, TAG, "PlayTrailer : " + details.title);
        }
    }

}
