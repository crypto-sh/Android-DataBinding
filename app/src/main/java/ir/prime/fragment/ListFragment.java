package ir.prime.fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ir.prime.R;
import ir.prime.adapter.RecyclerAdapterPageList;
import ir.prime.baseClass.BaseFragment;
import ir.prime.custom_view.LayoutManagerCustom;
import ir.prime.databinding.FragmentListBinding;
import ir.prime.model.PageListParcelable;


/**
 * A simple {@link BaseFragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends BaseFragment {

    private static final String ARG_PAGE_KEY = "pageKey";
    private static final String ARG_ListRow  = "listRow";

    String mPageKey;

    PageListParcelable mListRows;

    static final String TAG = ListFragment.class.getSimpleName();

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pageKey Parameter 1.
     * @param listRow Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(String pageKey,PageListParcelable listRow) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PAGE_KEY, pageKey);
        args.putParcelable(ARG_ListRow, listRow);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageKey    = getArguments().getString(ARG_PAGE_KEY);
            mListRows   = getArguments().getParcelable(ARG_ListRow);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentListBinding binding = setContentView(container,R.layout.fragment_list);
        View view = binding.getRoot();
        binding.recyclerView.setLayoutManager(new LayoutManagerCustom(getContext(),true));
        RecyclerAdapterPageList adapter = new RecyclerAdapterPageList(getActivity(),mListRows);
        adapter.setMediaItemSelected(this);
        binding.setAdapter(adapter);
        return view;
    }


}
