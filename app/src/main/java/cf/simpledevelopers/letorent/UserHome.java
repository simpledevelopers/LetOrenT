package cf.simpledevelopers.letorent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserHome extends AppCompatActivity {
    /*
    *These variables are used to populate the grid of houses
    */
    private GridView myGrid =null;
    private ArrayList<House> houseArray;
    HouseAdapter adapter= null;

  /*
  * Fire base variables goes here
  * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        //Initialise variables
        initialize();
    }


    /*
    * The following function initialises all our global variables
    */

    private void initialize(){
        setTitle("Home");
        myGrid = findViewById(R.id.user_home_grid);

        houseArray = new ArrayList<>();
        addHouses();
        adapter = new HouseAdapter(getApplicationContext(),houseArray);
        myGrid.setAdapter(adapter);

        myGrid.setOnItemClickListener(onItemClickListener);
    }

    /*
    * Function to create houses
    * */


    private void addHouses(){

        houseArray.add(new House("0001","251 Chamunyu aka Munongo rd","Budiriro","Harare",null,null,null,12));
        houseArray.add(new House("0001","251 munondo rd","Budiriro","Harare",null,null,null,12));
        houseArray.add(new House("0001","251 munondo rd","Budiriro","Harare",null,null,null,12));
        houseArray.add(new House("0001","251 munondo rd","Budiriro","Harare",null,null,null,12));
    }

    /*
    * We use this to handle clicks for each house clicked or selected
    * */
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          showToast("Item "+ i +" Clicked");
        }
    };

    /*
    * Handle the activity when back is clicked
    * */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();//close the application.
    }

    /*
    * This adds menu icons to the action bar
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
     /*
     * This handles clicks on the menu icon
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();
        if (selected==R.id.item_login){
           startActivity( new Intent(this,Login.class));
           finish();
        }
        if(selected==R.id.item_help){
            // startActivity( new Intent(this,Login.class));

        }
     /*   if (selected==R.id.app_bar_switch)
        {

        }*/
        return super.onOptionsItemSelected(item);
    }

    /*
    * We use this to alert the user
    * */
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
