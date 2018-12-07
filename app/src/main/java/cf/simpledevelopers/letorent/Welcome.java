package cf.simpledevelopers.letorent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
     Button bt_next = null;
     RelativeLayout relativeLayout1,relativeLayout2;
     Animation downIn, upIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initialise();
    }
    private void initialise(){
        setTitle("Welcome");
        bt_next = findViewById(R.id.bt_next);
        relativeLayout1 = findViewById(R.id.rl1);
        relativeLayout2 = findViewById(R.id.rl2);


        downIn = AnimationUtils.loadAnimation(this,R.anim.down_in);
        upIn = AnimationUtils.loadAnimation(this,R.anim.up_in);

        bt_next.setOnClickListener(this);

        relativeLayout1.setAnimation(downIn);
        relativeLayout2.setAnimation(upIn);

    }

    @Override
    public void onClick(View view) {
        int myView = view.getId();
        switch (myView){
            case R.id.bt_next:
            {
               startActivity(new Intent(this,UserHome.class));
               finish();
            }
        }
    }


}
