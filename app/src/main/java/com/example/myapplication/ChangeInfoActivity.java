package com.example.myapplication;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Info.InfoBuyer;
import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constants.BaseUrlBuyer;

public class ChangeInfoActivity extends BaseActivity {
    @BindView(R.id.change_name)
    EditText changename;
    @BindView(R.id.change_sdt)
    EditText changesdt;
    @BindView(R.id.change_address)
    EditText changeaddress;


    @BindView(R.id.save)
    Button save;

    String username, name, sdt, address;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_change_info;
    }

    @Override
    protected void setupListener() {
        Save();
    }

    @Override
    protected void populateData() {
        callback();
        setTitle("Thay đổi thông tin");
    }

    private void Save() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
                Toast.makeText(ChangeInfoActivity.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void sendData() {
        SharedPreferences preferences = getSharedPreferences("data_login", MODE_PRIVATE);
        username = preferences.getString("username", "");
        if (TextUtils.isEmpty(changename.getText().toString())) {
            name = preferences.getString("name", "");
        } else {
            name = changename.getText().toString();
        }
        if (TextUtils.isEmpty(changesdt.getText().toString())) {
            sdt = preferences.getString("sdt", "");
        } else {
            sdt = changesdt.getText().toString();
        }

        if (TextUtils.isEmpty(changeaddress.getText().toString())) {
            address = preferences.getString("address", "");
        } else {
            address = changeaddress.getText().toString();
        }


        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
        Call<InfoBuyer> call = apiInterface.updateinfo(username, name, sdt, address);

        call.enqueue(new Callback<InfoBuyer>() {
            @Override
            public void onResponse(Call<InfoBuyer> call, Response<InfoBuyer> response) {

                InfoBuyer infoBuyer = response.body();

                Log.d("Server Response", infoBuyer.getResponse());

            }

            @Override
            public void onFailure(Call<InfoBuyer> call, Throwable t) {
                Log.d("Fail Response", "" + username + name + sdt + address);
            }
        });
    }
}
