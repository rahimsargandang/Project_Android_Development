package com.example.twiceapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twiceapps.adapter.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        List<homerecycler> allHomeInfor = getAllHomeInfor();
        HomeRecyclerViewAdapter homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getActivity(), allHomeInfor);
        recyclerView.setAdapter(homeRecyclerViewAdapter);

        return v;
    }

    private List<homerecycler>getAllHomeInfor(){
        List<homerecycler> allHome = new ArrayList<homerecycler>();

        allHome.add(new homerecycler("Stress", R.drawable.stress));
        allHome.add(new homerecycler("Anxiety", R.drawable.anxiety));
        allHome.add(new homerecycler("Depression", R.drawable.depression));

        return allHome;
    }

}