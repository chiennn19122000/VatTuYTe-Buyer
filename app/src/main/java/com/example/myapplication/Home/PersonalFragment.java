package com.example.myapplication.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ChangeInfoActivity;
import com.example.myapplication.ChangePassActivity;
import com.example.myapplication.GetProduct.APIService;
import com.example.myapplication.Info.InfoBuyer;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.example.myapplication.Constants.BaseUrlGet;

public class PersonalFragment extends Fragment {
    TextView name,sdt, address;
    Button changeinfo,changepass,logout,exit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);

        name = (TextView) view.findViewById(R.id.buyer_name);
        sdt = (TextView) view.findViewById(R.id.buyer_number_phone);
        address = (TextView) view.findViewById(R.id.buyer_place);
        changeinfo = (Button) view.findViewById(R.id.thay_doi_thong_tin);
        changepass = (Button) view.findViewById(R.id.thay_doi_pass);
        logout = (Button) view.findViewById(R.id.logout);
        exit = (Button) view.findViewById(R.id.exit);

        setData();
        ChangeInfo();
        ChangePass();
        Logout();
        Exit();
        return view;
    }

    private void setData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data_login",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<InfoBuyer>> call = apiService.getInfoBuyer();
        call.enqueue(new Callback<List<InfoBuyer>>() {
            @Override
            public void onResponse(Call<List<InfoBuyer>> call, Response<List<InfoBuyer>> response) {
                List<InfoBuyer> infoBuyers = response.body();
                for (int i = 0; i<infoBuyers.size() ; i++) {
                    if(infoBuyers.get(i).getUsername().equals(preferences.getString("username","")))
                    {
                        editor.putString("name",infoBuyers.get(i).getName());
                        editor.putString("sdt",infoBuyers.get(i).getSdt());
                        editor.putString("address",infoBuyers.get(i).getAddress());
                        editor.commit();
                        name.setText("Tên: "+infoBuyers.get(i).getName());
                        sdt.setText("Số điện thoại: "+infoBuyers.get(i).getSdt());
                        address.setText("Địa chỉ: "+infoBuyers.get(i).getAddress());
                        break;
                    }

                }
            }

            @Override
            public void onFailure(Call<List<InfoBuyer>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void ChangeInfo() {
        changeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangeInfoActivity.class));
            }
        });
    }

    private void ChangePass() {
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePassActivity.class));
            }
        });
    }

    private void Logout() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    private void Exit() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Question ?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("are you sure you want to exit");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}
