package com.example.splitly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );

        logout = findViewById( R.id.logout );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, MainActivity.class);
                    startActivity( intent );
                    overridePendingTransition( android.R.anim.fade_in, 0 );

                }
            });
        }
    }