package ir.prime.android;


import android.os.Bundle;

import com.google.protobuf.InvalidProtocolBufferException;

import ir.prime.R;
import ir.prime.adapter.RecyclerAdapterPageList;
import ir.prime.baseClass.BaseActivity;

import ir.prime.custom_view.LayoutManagerCustom;
import ir.prime.databinding.ActivityDetailsMovieBinding;
import ir.prime.model.Api;
import ir.prime.model.DetailsMovieModel;
import ir.prime.model.ItemsDataParcelable;
import ir.prime.utils.PublicFunction;
import ir.prime.utils.PublicValue;
import ir.prime.utils.RequestParams;
import ir.prime.utils.ResponseHandler;
import ir.prime.utils.RestClient;


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
            getDetailsData(movie.mId);
        }

    }

    private void getDetailsData(String mediaId) {
        String url = PublicValue.getBaseUrl() + "getMovieDetails";
        RestClient.Post(DetailsMovieActivity.this, url, new RequestParams(), new ResponseHandler() {
            @Override
            public void onSuccess(byte[] result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Api.MovieDetails details = Api.MovieDetails.parseFrom(result);
                            setDetailsBinding(new DetailsMovieModel(details.getMovie()));
                        } catch (InvalidProtocolBufferException e) {
                            PublicFunction.LogData(false, TAG, e.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onFailure(int errorCode, String errorMsg) {
                PublicFunction.LogData(false, TAG, errorMsg);
            }
        });
    }

    private void setDetailsBinding(DetailsMovieModel model) {
        if (model == null) {
            return;
        }
        binding.setDetails(model);
        RecyclerAdapterPageList adapterPage = new RecyclerAdapterPageList(DetailsMovieActivity.this, model.detailsList);
        binding.recyclerView.setLayoutManager(new LayoutManagerCustom(DetailsMovieActivity.this,true));
        binding.setRecyclerAdapter(adapterPage);
    }

    public class DetailsHandler {
        public void AddtoWatchList(DetailsMovieModel details) {
            PublicFunction.LogData(true, TAG, "AddToWatchList : " + details.mTitle);
        }

        public void PlayTrailer(DetailsMovieModel details) {
            PublicFunction.LogData(true, TAG, "PlayTrailer : " + details.mTitle);
        }
    }

}
