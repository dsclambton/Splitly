package com.example.splitly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText emaildId, password;
    Button loginbtn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mFirebaseAuth = FirebaseAuth.getInstance();
        emaildId = findViewById( R.id.email );
        password = findViewById( R.id.password );
        loginbtn = findViewById( R.id.button );

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText( MainActivity.this, "You are logged in", Toast.LENGTH_SHORT ).show();
                    Intent i = new Intent( MainActivity.this, Dashboard.class );
                    startActivity( i );
                }
                else {
                    Toast.makeText( MainActivity.this, "Please login", Toast.LENGTH_SHORT ).show();

                }
            }
        };

        loginbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emaildId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()){
                    emaildId.setError( "Please enter the email id" );
                    emaildId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError( "Please enter your password" );
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword( email, pwd ).addOnCompleteListener( MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Error, Please login again",Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Intent intToHome = new Intent( MainActivity.this, Dashboard.class );
                                startActivity( intToHome );
                            }
                        }
                    } );

                }
                else {
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        } );

        textView = (TextView)findViewById( R.id.tv2 );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity( intent );
                overridePendingTransition( android.R.anim.fade_in,0 );

            }
        } );

    }


}
