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

public class Register extends AppCompatActivity {
    TextView textView;
    EditText emaildId, password;
    Button regbtn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        mFirebaseAuth = FirebaseAuth.getInstance();
        emaildId = findViewById( R.id.email );
        password = findViewById( R.id.password );
        regbtn = findViewById( R.id.register );

        regbtn.setOnClickListener( new View.OnClickListener() {

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
                    Toast.makeText(Register.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword( email, pwd ).addOnCompleteListener( Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(Register.this,"Sign Up Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity( new Intent( Register.this, Dashboard.class ) );
                            }
                        }
                    } );

                }
                else {
                    Toast.makeText(Register.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        } );

        textView = (TextView)findViewById( R.id.tv1 );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity( intent );
                overridePendingTransition( android.R.anim.fade_in, 0 );

            }
        } );

    }
}