package cf.simpledevelopers.letorent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simple Dev on 11/24/2018.
 */

public class HouseAdapter extends ArrayAdapter<House> {
    private List<House> houseArrayList;

    public HouseAdapter(@NonNull Context context, List<House> houseArrayList) {
        super(context, 0,houseArrayList);
        this.houseArrayList=houseArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         House myHouse = houseArrayList.get(position);
         View view = convertView;
         if (view==null){
             view = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item,parent,false);
         }
        TextView address= view.findViewById(R.id.tv_address);
        TextView rooms= view.findViewById(R.id.tv_numRooms);
        TextView city= view.findViewById(R.id.tv_city);

        String test =myHouse.getStreet_address()+", "+myHouse.getLocation()+", "+myHouse.getCity();
        address.setText(test);

        String rooms1 = myHouse.getNumOfRooms()+ " Rooms Available";
        rooms.setText(rooms1);

        city.setText(myHouse.getCity());

      /*  try {


        }catch (NullPointerException px){
            Log.e("House Adapter: ",px.getMessage());
        }*/
        return view;
    }
}
