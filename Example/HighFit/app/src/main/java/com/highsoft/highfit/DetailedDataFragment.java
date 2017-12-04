package com.highsoft.highfit;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedDataFragment extends Fragment {

    private ArrayList<String> samples;
    private ArrayList<String> dates;

    public DetailedDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        Bundle bundle = this.getArguments();
        samples = bundle.getStringArrayList("SamplesArrayList");
        dates = bundle.getStringArrayList("DatesArrayList");
        View view = inflater.inflate(R.layout.fragment_data_all, container, false);
        TextView textView = (TextView) view.findViewById(R.id.unitTxt);
        textView.setText(bundle.getString("unit"));
        ListView listView = (ListView)view.findViewById(R.id.listDetailedData);
        listView.setAdapter(new DetailedDataAdapter());
        return view;
    }

    private class DetailedDataAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return samples.size();
        }
        @Override
        public String getItem(int position){
            return samples.get(position);
        }
        public String getDateItem(int position){
            return dates.get(position);
        }
        @Override
        public long getItemId(int position){
            return samples.get(position).hashCode();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_data_item, container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(getItem(position));
            ((TextView) convertView.findViewById(android.R.id.text2))
                    .setText(getDateItem(position));
            return convertView;
        }

    }
}
