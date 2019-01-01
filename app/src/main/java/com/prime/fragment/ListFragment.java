package com.prime.fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prime.adapter.RecyclerAdapterPageList;
import com.prime.baseClass.BaseFragment;

import com.prime.R;

import com.prime.custom_view.LayoutManagerCustom;
import com.prime.databinding.FragmentListBinding;
import com.prime.model.PageDataParcelable;
import com.prime.utils.PublicFunction;


/**
 * A simple {@link BaseFragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends BaseFragment {

    PageDataParcelable listRows;

    private static final String ARG_ListRow  = "listRow";

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listRow Parameter 1.
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(PageDataParcelable listRow) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ListRow  , listRow);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listRows = getArguments().getParcelable(ARG_ListRow);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentListBinding binding = setContentView(container,R.layout.fragment_list);
        View view = binding.getRoot();
        binding.recyclerView.setLayoutManager(new LayoutManagerCustom(getContext(),true));
        RecyclerAdapterPageList adapter = new RecyclerAdapterPageList((item, type) -> {
            PublicFunction.LogData(true,ListFragment.class.getSimpleName(),"onSelectedItem : " + item.title);
        },listRows.listRow);
        binding.setAdapter(adapter);
        return view;
    }


}
