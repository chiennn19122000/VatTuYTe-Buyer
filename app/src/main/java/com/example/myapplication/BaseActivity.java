package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        setupListener();
        populateData();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void setupListener();

    protected abstract void populateData();

    protected void HideTitle()
    {
        // ẩn Title
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    protected void callback()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}


