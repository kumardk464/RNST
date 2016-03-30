package com.ascendia.dheeraj.rnst.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.ascendia.dheeraj.rnst.R;
import com.ascendia.dheeraj.rnst.adapter.CustomAdapter;
import com.ascendia.dheeraj.rnst.pojo.RowItem;


public class RnstLatestNews extends Fragment {
	
	String[] menutitles;
	TypedArray menuIcons;

	ListView lView;

	CustomAdapter adapter;
	private List<RowItem> rowItems;

	public RnstLatestNews() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.rnst_fragment_latest_news, container,
				false);

	

		return rootView;
	}
/*
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		menutitles = getResources().getStringArray(R.array.titles);
		// menuIcons = getResources().obtainTypedArray(R.array.icons);

		rowItems = new ArrayList<RowItem>();

		for (int i = 0; i < menutitles.length; i++) {
			RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
					i, -1));

			rowItems.add(items);

		}

		adapter = new CustomAdapter(getActivity(), rowItems);

		lView.setAdapter(adapter);

		lView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				 Toast.makeText(getActivity(), menutitles[position],
				Toast.LENGTH_SHORT).show();

				Intent i = new Intent(getActivity(),
						HomeFragmentDetailPage.class);
				// i.putExtra("childone.txt", menutitles[position]);
				//startActivity(i);

				
				 * HomeFragmentDetailPage newFragment = new
				 * HomeFragmentDetailPage(); Bundle args = new Bundle();
				 * AssetManager am = getActivity().getAssets(); //InputStream is
				 * = am.open("childone.txt");
				 * 
				 * args.putString(
				 * "com.example.storyapplicationslide:raw/somefile.txt",
				 * HomeFragment.get(menutitles[position].toString()));
				 * 
				 * newFragment.setArguments(args); FragmentTransaction
				 * transaction = getFragmentManager() .beginTransaction();
				 * 
				 * transaction.replace(R.id.listHome, newFragment);
				 * transaction.addToBackStack(null);
				 * 
				 * newFragment.setArguments(args);
				 
				// FragmentTransaction fragmentTransaction =
				// getSupportFragmentManager().beginTransaction();
				// fragmentTransaction.replace(R.id.listHome,newFragment,null);
				// fragmentTransaction.commit();

			}
		});
	}*/
}
