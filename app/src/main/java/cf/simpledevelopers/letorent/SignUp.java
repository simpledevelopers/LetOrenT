package cf.simpledevelopers.letorent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SIGN UP" ;
    //global views
    private EditText s_email,s_password;
    private Button s_sign_up ;

    //Fire base variables
    private FirebaseAuth mAuth = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialise();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser!=null){
            showToast("Signed in as "+ currentUser.getEmail());
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else{
           // showToast("Authentication failed");
        }
    }

    private void initialise(){
      s_email = findViewById(R.id.et_sign_up_email);
      s_password = findViewById(R.id.et_sign_up_password);
      s_sign_up = findViewById(R.id.bt_sign_up);

      s_sign_up.setOnClickListener(this);
      mAuth =FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Login.class));
        finish();
    }
   private void signUp(String email,String password){
       final ProgressDialog progressDialog = new ProgressDialog(this);
       progressDialog.setTitle("Signing up.....");
       progressDialog.show();
      mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()){
                  progressDialog.dismiss();
                  Log.d(TAG,"create user with email: success");
                  FirebaseUser user = mAuth.getCurrentUser();
                  updateUI(user);
              }else {
                  progressDialog.dismiss();
                 Log.w(TAG,"create user with email: failure");
                 //showToast("Authentication failed");
                 updateUI(null);
              }
          }
      });
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_to_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        if (id==R.id.main_home){
            startActivity(new Intent(this,UserHome.class));
            finish();
        }

        if (id==R.id.main_about){
            startActivity(new Intent(this,About.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
     int getId = view.getId();
     if (getId==R.id.bt_sign_up){
         String email = s_email.getText().toString().trim();
         String password = s_password.getText().toString().trim();

         if (TextUtils.isEmpty(email)){
             showToast("Please enter email");
             return;
         }

         if (TextUtils.isEmpty(password)){
             showToast("Please enter password");
             return;
         }
         //
          signUp(email,password);
     }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
