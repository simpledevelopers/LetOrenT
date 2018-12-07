package cf.simpledevelopers.letorent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

/*
* last section needs commenting
* */
public class Login extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Log In" ;

    /*
    * This class allows a landlord to login and post a house
    * */

    /*
    * Main global view variables
    * */
     /* private EditText user_email, user_password;
      private TextView forgot_password,no_account;
      private Button login_with_email, login_with_google,login_with_facebook,login_with_twitter;
*/
      /*
      * Main global firebase variables
      * */
      FirebaseAuth firebaseAuth ;
      FirebaseAuth.AuthStateListener authStateListener ;

    // Choose authentication providers

    private int RC_SIGN_IN = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

       final List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               if (user!=null){
                   updateUI(user);
               }else{
                   startActivityForResult(
                           AuthUI.getInstance()
                                   .createSignInIntentBuilder()
                                   .setIsSmartLockEnabled(false)
                                   .setAvailableProviders(providers)
                                   .build(),
                           RC_SIGN_IN);
                }
            }
        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_SIGN_IN){
            if (resultCode== RESULT_OK){
                showToast("You are signed in");
            }else if (resultCode==RESULT_CANCELED){
                showToast("Cancelled Sign In");
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }




    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    /*initialise();
        FirebaseUser  user = firebaseAuth.getCurrentUser();
        try {
            if (user==null){
                // Create and launch sign-in intent

            }
        }catch (NullPointerException ex){
            Log.e("Login",ex.getMessage());
        }*/

/*    private void initialise(){


        //Change the title of the activity
        setTitle(R.string.login);

        //initialisation of our global view variables

        //Edit texts
        user_email = findViewById(R.id.et_login_mail);
        user_password= findViewById(R.id.et_login_password);

        //Text views
        forgot_password = findViewById(R.id.tv_forgot_password);
        no_account =findViewById(R.id.tv_no_account);

        //Buttons
        login_with_email = findViewById(R.id.bt_login);
        login_with_google = findViewById(R.id.bt_google_sign_in);
        login_with_facebook = findViewById(R.id.bt_facebook_sign_in);
        login_with_twitter = findViewById(R.id.bt_twitter_sign_in);

        //Fire base variables
        firebaseAuth = FirebaseAuth.getInstance();

        //manage when the user clicks a button or a text view
        setClicks();


    }*/

    /*
    * Sign in method
    * */
   /* private void signIn(String mEmail,String mPassword){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait while Logging in.....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword (mEmail,mPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Grand the user access
                    progressDialog.dismiss();
                    Log.e(TAG,"login user with email: success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                }else {
                    //Notify the user of the error
                    progressDialog.dismiss();
                    Log.e(TAG,"login user with email: failure");
                    showToast("Login failed");
                    updateUI(null);
                }
            }
        });

    }*/
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser!=null){
            showToast("Signed in as "+ currentUser.getEmail());
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else{
            // showToast("Authentication failed");
        }
    }
  /*  private void setClicks(){
        //Buttons
        login_with_email.setOnClickListener(this);
        login_with_google.setOnClickListener(this);
        login_with_facebook.setOnClickListener(this);
        login_with_twitter.setOnClickListener(this);

        //Text Views
        forgot_password.setOnClickListener(this);
        no_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int itemId = view.getId();

        switch(itemId){
            case R.id.bt_login:{
                *//*
                * call login method for email and password
                * if successful open the Landlord home page
                * *//*
                //grab the user email and password
                String email = user_email.getText().toString().trim();
                String password = user_password.getText().toString().trim();

                //validate email
                if (TextUtils.isEmpty(email)){
                    showToast("Please enter email");
                    return;
                }
                    //validate password
                if (TextUtils.isEmpty(password)){
                    showToast("Please enter password");
                    return;
                }
                //Call sign in method
                signIn(email,password);
                break;

            }
            case R.id.bt_google_sign_in:{
               *//*
                * call the google sign in method
                * if successful open the Landlord home page
                * *//*
                showToast("Google sign in");
                break;
            }
            case R.id.bt_facebook_sign_in:{
                *//*
                * call facebook sign in method
                * if successful open the Landlord home page
                * *//*
                showToast("Facebook Sign in");
                break;
            }
            case R.id.bt_twitter_sign_in:{
                *//*
                * call twitter sign in method
                * if successful open the Landlord home page
                * *//*
                showToast("Twitter Sign in");
                break;
            }
            case R.id.tv_forgot_password:{
                *//*
                * call the forgot password method
                * if successful open the Landlord home page
                * *//*
                showToast("Forgot password clicked");
                break;
            }
            case R.id.tv_no_account:{
                *//*
                * start activity for sign up
                * *//*
                startActivity( new Intent(this,SignUp.class));
                finish();
                showToast("No Account Clicked");
                break;
            }
            default:{
                showToast("Wrong Item clicked");
            }

        }
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,UserHome.class));
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

    }
}
