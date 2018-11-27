package com.acme.tictactoe.controller.retrofit;

import com.acme.tictactoe.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MarchellinAntonia on 27/11/18.
 */
public interface UserService {

    @GET("/users")
    Call<List<User>> getAllUsers();

}
