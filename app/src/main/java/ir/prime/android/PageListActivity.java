package ir.prime.android;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.List;
import ir.prime.R;
import ir.prime.databinding.ActivityPageListBinding;
import ir.prime.fragment.ListFragment;
import ir.prime.model.PageListParcelable;
import ir.prime.utils.PublicFunction;
import ir.prime.utils.PublicValue;
import ir.prime.utils.RequestParams;
import ir.prime.utils.ResponseHandler;
import ir.prime.utils.RestClient;
import ir.prime.baseClass.BaseActivity;
import ir.prime.model.Api;


public class PageListActivity extends BaseActivity  {

    ActivityPageListBinding binding;

    final static String TAG = PageListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = setContentView(PageListActivity.this,R.layout.activity_page_list);
        getPageData();
    }

    private void getPageData(){
        String url = PublicValue.getBaseUrl() + "getHome";
        RestClient.Post(PageListActivity.this, url, new RequestParams(), new ResponseHandler() {
            @Override
            public void onSuccess(byte[] result) {
                try {
                    Api.HomeData homeData = Api.HomeData.parseFrom(result);
                    pagerAdapter adapter = new pagerAdapter(PageListActivity.this, homeData, getSupportFragmentManager());
                    binding.setAdapterHandler(adapter);
                    binding.setTabHandler(binding.pager);
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

    public class pagerAdapter extends FragmentPagerAdapter {

        Context context;

        List<Api.TabRow> tabRows;

        List<Api.ListRow> dataList;

        public pagerAdapter(Context context,Api.HomeData homeData,FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context     = context;
            this.tabRows     = homeData.getTabRowList();
            this.dataList    = homeData.getListRowList();
        }


        @Override
        public Fragment getItem(int position) {
            Api.TabRow tab = tabRows.get(position);
            PageListParcelable pageData = new PageListParcelable();
            if (tab.getIsSelected()) {
                pageData = new PageListParcelable(dataList);
            }
            return ListFragment.newInstance(tab.getPageKey(),pageData);
        }

        @Override
        public int getCount() {
            if (tabRows == null){
                return 0;
            }
            return tabRows.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (tabRows == null || tabRows.size() < position){
                return "";
            }
            return tabRows.get(position).getTitle();
        }
    }
}
