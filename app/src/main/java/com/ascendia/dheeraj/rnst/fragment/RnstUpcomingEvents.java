package com.ascendia.dheeraj.rnst.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ascendia.dheeraj.rnst.R;
import com.ascendia.dheeraj.rnst.adapter.CardAdapter;


public class RnstUpcomingEvents extends Fragment {
	
	RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;





	public RnstUpcomingEvents() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.rnst_fragment_upcoming_events, container,
				false);

		 RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view1);
		  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		  
		  CardAdapter mAdapter =  new CardAdapter();
		  recyclerView.setAdapter(mAdapter);

		return rootView;
	}

}

/* String[] menutitles;
	TypedArray menuIcons;

	ListView lView;

	CustomAdapter adapter;
	private List<RowItem> rowItems;
public RnstUpcomingEvents() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.rnst_fragment_upcoming_events, container,
				false);
         // 1. get a reference to recyclerView 
         RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
 
         // 2. set layoutManger 
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
 
         // this is data fro recycler view 
         ItemData itemsData[] = {
             new ItemData("Indigo", R.drawable.quotemahatamaje),
                 new ItemData("Red", R.drawable.quotemahatamaje),
                 new ItemData("Blue", R.drawable.quotemahatamaje),
                 new ItemData("Green", R.drawable.quotemahatamaje),
                 new ItemData("Amber", R.drawable.quotemahatamaje),
                 new ItemData("Deep Orange", R.drawable.quotemahatamaje)
         }; 
 
 
        
         MyAdapter mAdapter = new MyAdapter(itemsData);
       
         recyclerView.setAdapter(mAdapter);
         
         recyclerView.setItemAnimator(new DefaultItemAnimator());
 
         return rootView;
     } */
