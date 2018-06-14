package com.app.sgmv.sgmv.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;

import java.util.concurrent.ConcurrentSkipListMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FailureActivity extends BaseActivity {

    //@BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    //@BindView(R.id.pb_products_offers) ProgressBar mPBProducts;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private Context ctx = this;
    private String title = "";
    private Menu collapsedMenu;
    private boolean appBarExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failures);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra(Constants.TAG_MODULE);
        //mCollapsingToolbar.setTitle(title);

        setSupportActionBar(mToolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initCollapsingToolbar();
        eventUI();

    }

    private void initCollapsingToolbar() {
        mCollapsingToolbar.setTitle(" ");
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle(title);
                    isShow = true;
                    invalidateOptionsMenu(); //new
                    appBarExpanded = true;
                } else if (isShow) {
                    mCollapsingToolbar.setTitle(" ");
                    isShow = false;
                    invalidateOptionsMenu(); //new
                    appBarExpanded = false; //new
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_failure, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (title){
            case Constants.FAILURE:
                switch (item.getItemId()) {
                    case R.id.mf_new:
                        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mf_search:
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            case Constants.USERS:
                switch (item.getItemId()) {
                    case R.id.mf_new:
                        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mf_search:
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void eventUI(){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
