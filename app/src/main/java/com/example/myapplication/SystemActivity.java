package com.example.myapplication;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Home.BasketFragment;
import com.example.myapplication.Home.HomeFragment;
import com.example.myapplication.Home.OrderProductFragment;
import com.example.myapplication.Home.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class SystemActivity extends BaseActivity {

    @BindView(R.id.mainbar)
    BottomNavigationView MainBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_system;
    }

    @Override
    protected void setupListener() {
        ListenItemBar(); // set fragment đầu tiên khi đăng nhập
    }

    @Override
    protected void populateData() {

    }
    private void ListenItemBar()
    {
        setTitle("Trang sản phẩm");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        MainBar.setOnNavigationItemSelectedListener(mainbarListen);
    }
    // hàm để chuyển frag ment khi chọn các item màn hình chính
    private BottomNavigationView.OnNavigationItemSelectedListener mainbarListen =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            setTitle("Trang sản phẩm");
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.post_add:
                            setTitle("Giỏ Hàng");
                            selectedFragment = new BasketFragment();
                            break;
                        case R.id.order:
                            setTitle("Đơn đặt hàng");
                            selectedFragment = new OrderProductFragment();
                            break;
                        case R.id.personal:
                            setTitle("Thông tin cá nhân");
                            selectedFragment = new PersonalFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                    return true;
                }

            };
    //phương thức tìm kiếm
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                TODO: bắt các giá trị khi ta tìm kiếm
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void setTitle(String s)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);
    }

}
