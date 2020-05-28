package com.example.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Home.HomeFragment;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.UsernameAndPassword;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constants.BaseUrlBuyer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.userBuyer)
    EditText user;

    @BindView(R.id.passBuyer)
    EditText pass;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.registration)
    Button registration;

    String User,Pass;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupListener() {

        Login();
        Registration();
    }

    @Override
    protected void populateData() {
        HideTitle();
    }

    private void Login()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User = user.getText().toString();
                Pass = pass.getText().toString();
                ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
                Call<UsernameAndPassword> call = apiInterface.loginacc( User,Pass);

                call.enqueue(new Callback<UsernameAndPassword>() {
                    @Override
                    public void onResponse(Call<UsernameAndPassword> call, Response<UsernameAndPassword> response) {

                        UsernameAndPassword usernameAndPassword = response.body();
                        Log.d("Server Response",""+usernameAndPassword.getResponse());
                        if(usernameAndPassword.getResponse().equals("Successfully"))
                        {
                            SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username",User);
                            editor.putString("password",Pass);
                            editor.commit();
                            startActivity(new Intent(MainActivity.this, SystemActivity.class));

                        }
                        else {
                            Toast.makeText(MainActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<UsernameAndPassword> call, Throwable t) {
                        Log.d("Server Response",""+t.toString());

                    }
                });
            }
        });
    };

    private void Registration()
    {
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
    }

}