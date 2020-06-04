package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.SendDataToServer.ApiClient;
import com.example.myapplication.SendDataToServer.ApiInterface;
import com.example.myapplication.SendDataToServer.Register;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Constants.BaseUrlBuyer;

public class RegistrationActivity extends BaseActivity {

    @BindView(R.id.userregis)
    EditText User;

    @BindView(R.id.passregis)
    EditText Pass;

    @BindView(R.id.name)
    EditText Name;

    @BindView(R.id.sdt)
    EditText Sdt;

    @BindView(R.id.address)
    EditText Address;

    @BindView(R.id.registrationuser)
    Button regis;

    String name,user,pass,address;
    int sdt;
    Boolean x = false;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_registration;
    }

    @Override
    protected void setupListener() {
        Regis();
    }

    @Override
    protected void populateData() {
        HideTitle();

    }

    private void Regis()
    {
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getText().toString().equals(""))
                {
                    x = false;
                    Toast.makeText(RegistrationActivity.this, "Bạn chưa điền tài khoản", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (Pass.getText().toString().equals(""))
                    {
                        x = false;
                        Toast.makeText(RegistrationActivity.this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (Name.getText().toString().equals(""))
                        {
                            x = false;
                            Toast.makeText(RegistrationActivity.this, "Bạn chưa điền tên", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (Sdt.getText().toString().equals(""))
                            {
                                x = false;
                                Toast.makeText(RegistrationActivity.this, "Bạn chưa điền số điện thoại", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (Address.getText().toString().equals(""))
                                {
                                    x = false;
                                    Toast.makeText(RegistrationActivity.this, "Bạn chưa điền địa chỉ", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    x = true;
                                }
                            }
                        }
                    }
                }
                if (x)
                {
                    writeData();
                }
            }
        });
    }

    private void writeData() {
        name = Name.getText().toString();
        user = User.getText().toString();
        pass = Pass.getText().toString();
        sdt = Integer.parseInt(Sdt.getText().toString());
        address = Address.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient(BaseUrlBuyer).create(ApiInterface.class);
        Call<Register> call = apiInterface.regis( user,pass,name, sdt,address);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                Register register = response.body();
                Log.d("Server Response",""+register.getResponse());
                if(register.getResponse().equals("Successfully"))
                {
                    Toast.makeText(RegistrationActivity.this,"Đăng kí thàng công",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

                }
                else {
                    Toast.makeText(RegistrationActivity.this,"Tài khoản này đã tồn tại",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.d("Server Response",""+t.toString());

            }
        });
    }


}
