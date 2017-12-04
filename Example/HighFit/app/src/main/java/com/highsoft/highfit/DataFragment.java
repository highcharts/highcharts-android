package com.highsoft.highfit;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    private String[] dataMenuItems = {
            "Steps",
            "Calories (Eaten)",
            "Calories (Burned)"
    };

    private Integer[] imgid = {
        R.drawable.ic_directions_walk_black_48dp,
        R.drawable.ic_local_dining_black_48dp,
        R.drawable.ic_whatshot_black_48dp
    };

    public DataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                switch (position){
                    case 0:
                        fragmentTransaction.replace(R.id.container, new AreaChartFragment(), "AreaChartFragment");
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.container, new ColumnChartFragment(), "ColumnChartFragment");
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        fragmentTransaction.replace(R.id.container, new SplineChartFragment(), "SplineChartFragment");
                        fragmentTransaction.commit();
                        break;
                }

            }
        });
        listView.setAdapter(new DataMenuAdapter());
        return view;
    }

    private class DataMenuAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return dataMenuItems.length;
        }
        @Override
        public String getItem(int position){
            return dataMenuItems[position];
        }
        @Override
        public long getItemId(int position){
            return dataMenuItems[position].hashCode();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_menu_data_item, container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(getItem(position));
           ((ImageView) convertView.findViewById(R.id.itemIcon))
                   .setImageResource(imgid[position]);

            return convertView;
        }

    }
}
