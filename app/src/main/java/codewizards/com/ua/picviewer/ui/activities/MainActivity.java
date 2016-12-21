package codewizards.com.ua.picviewer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import codewizards.com.ua.picviewer.Const;
import codewizards.com.ua.picviewer.R;
import codewizards.com.ua.picviewer.model.DataContainer;
import codewizards.com.ua.picviewer.model.Good;
import codewizards.com.ua.picviewer.ui.recyclerview_goods.GoodAdapter;
import codewizards.com.ua.picviewer.ui.recyclerview_goods.ItemClickListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showListOfGoods();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            //showListOfGoods();
        } else if (id == R.id.nav_exit) {
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showListOfGoods() {
        List<Good> list = DataContainer.getInstance().getListOfGoods(MainActivity.this);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_goods);
        GoodAdapter goodAdapter = new GoodAdapter(MainActivity.this, list);
        rvContacts.setAdapter(goodAdapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(int posOfGood) {
        Intent intent = new Intent(MainActivity.this, PicActivity.class);
        intent.putExtra(Const.EXTRA_POS_OF_GOOD, posOfGood);
        startActivity(intent);
    }
}
