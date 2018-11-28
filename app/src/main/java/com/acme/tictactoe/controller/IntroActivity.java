package com.acme.tictactoe.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.acme.tictactoe.R;
import com.acme.tictactoe.controller.retrofit.RetrofitClientInstance;
import com.acme.tictactoe.controller.retrofit.UserService;
import com.acme.tictactoe.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity {

    Spinner player1;
    Spinner player2;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        player1 = (Spinner) findViewById(R.id.player1);
        player2 = (Spinner) findViewById(R.id.player2);

        /*Create handle for the RetrofitInstance interface*/
        UserService service = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        Call<List<User>> call = service.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                initializePlayer(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(IntroActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initializePlayer(List<User> users){
        List<String> list = new ArrayList<>();

        for (User user : users) {
            list.add(user.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1.setAdapter(dataAdapter);
        player2.setAdapter(dataAdapter);
    }

}
