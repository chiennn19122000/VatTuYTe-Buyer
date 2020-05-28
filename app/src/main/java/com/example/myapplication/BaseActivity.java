package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
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
}


