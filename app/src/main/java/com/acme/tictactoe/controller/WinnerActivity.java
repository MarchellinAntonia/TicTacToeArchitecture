package com.acme.tictactoe.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.acme.tictactoe.R;

public class WinnerActivity extends AppCompatActivity {

    TextView tvWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);
        tvWinner = (TextView) findViewById(R.id.tvWinner);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            tvWinner.setText(extras.getString("winnerName"));
        }
    }
}
