package com.app.sgmv.sgmv.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.activities.failure.RgisterFailureOneActivity;
import com.app.sgmv.sgmv.activities.failure.SearchFailureReportActivity;
import com.app.sgmv.sgmv.activities.user.RegisterUserActivity;
import com.app.sgmv.sgmv.activities.user.SearchUserActivity;
import com.app.sgmv.sgmv.activities.vehicle.RegisterVehicleActivity;
import com.app.sgmv.sgmv.activities.vehicle.SearchVehicleActivity;
import com.app.sgmv.sgmv.adapters.FailureAdapter;
import com.app.sgmv.sgmv.adapters.UserAdapter;
import com.app.sgmv.sgmv.adapters.VehicleAdapter;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.failure.FailureReport;
import com.app.sgmv.sgmv.entities.failure.FailureResponse;
import com.app.sgmv.sgmv.entities.user.User;
import com.app.sgmv.sgmv.entities.user.UsersResponse;
import com.app.sgmv.sgmv.entities.vehicle.Vehicle;
import com.app.sgmv.sgmv.entities.vehicle.VehicleResponse;
import com.app.sgmv.sgmv.services.FailureReportInterface;
import com.app.sgmv.sgmv.services.UserInterface;
import com.app.sgmv.sgmv.services.VehicleInterface;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.GlideApp;
import com.app.sgmv.sgmv.utilities.GridSpacingItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends BaseActivity {

    @BindView(R.id.rv_recyclerview) RecyclerView mRecyclerView;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.backdrop) ImageView mBackground;

    private String TAG = ListActivity.class.getSimpleName();
    private Context ctx = this;

    private List<FailureReport> failureReports = null;
    private List<User> userList = null;
    private List<Vehicle> vehicleList = null;

    private int submenu = 0;

    private String title = "";
    private Menu collapsedMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getIntent().getStringExtra(Constants.TAG_MODULE);
        initContentView();
        ButterKnife.bind(this);
        mTitle.setText(title);

        setSupportActionBar(mToolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initCollapsingToolbar();
        eventUI();
        initSubMenu();
    }

    public void setSubmenu(int submenu) {
        this.submenu = submenu;
    }

    public void initContentView(){
        if(title.equals(Constants.SM_FAILURE)){
            setContentView(R.layout.activity_failure);
        }else if(title.equals(Constants.SM_USERS)){
            setContentView(R.layout.activity_user);
        }else if(title.equals(Constants.SM_SAFE)){

        }else if(title.equals(Constants.SM_MAINTENANCE)){

        }else if(title.equals(Constants.SM_VEHICLE)){
            setContentView(R.layout.activity_vehicle);
        }else if(title.equals(Constants.SM_COSTOS)){

        }
    }

    public void initSubMenu(){
        if(title.equals(Constants.SM_FAILURE)){
            submenu = 1;
            GlideApp.with(ctx).load(R.drawable.bg_failure).into(mBackground);
            serviceFailureReport();
        }else if(title.equals(Constants.SM_USERS)){
            submenu = 2;
            GlideApp.with(ctx).load(R.drawable.bg_users).into(mBackground);
            serviceUsers();
        }else if(title.equals(Constants.SM_SAFE)){
            submenu = 3;
            GlideApp.with(ctx).load(R.drawable.bg_safe).into(mBackground);
        }else if(title.equals(Constants.SM_MAINTENANCE)){
            submenu = 4;
            GlideApp.with(ctx).load(R.drawable.bg_maintenance).into(mBackground);
        }else if(title.equals(Constants.SM_VEHICLE)){
            submenu = 5;
            GlideApp.with(ctx).load(R.drawable.bg_vehicle).into(mBackground);
            serviceVehicles();
        }else if(title.equals(Constants.SM_COSTOS)){
            submenu = 6;
            GlideApp.with(ctx).load(R.drawable.bg_costs).into(mBackground);
        }
        Log.d(TAG, ""+submenu);
        Log.d(TAG, title);
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
                    showOptions(R.id.mf_new);
                    showOptions(R.id.mf_search);
                    invalidateOptionsMenu();
                } else if (isShow) {
                    mCollapsingToolbar.setTitle(" ");
                    isShow = false;
                    hideOptions(R.id.mf_new);
                    hideOptions(R.id.mf_search);
                    invalidateOptionsMenu(); //
                }
            }
        });
    }

    public void showOptions(int id){
        MenuItem menuItem = collapsedMenu.findItem(id);
        menuItem.setVisible(true);
    }

    public void hideOptions(int id){
        MenuItem menuItem = collapsedMenu.findItem(id);
        menuItem.setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        collapsedMenu = menu;
        getMenuInflater().inflate(R.menu.menu_failure, collapsedMenu);
        //hideOptions(R.id.mf_new);
        //hideOptions(R.id.mf_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (title){
            case Constants.SM_FAILURE:
                //Failure
                switch (item.getItemId()) {
                    case R.id.mf_new:
                        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                        next(RgisterFailureOneActivity.class, false);
                        break;
                    case R.id.mf_search:
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                        next(Constants.TAG_LIST, (Serializable) failureReports, SearchFailureReportActivity.class, false);
                        break;
                }
                return true;
                //User
            case Constants.SM_USERS:
                switch (item.getItemId()) {
                    case R.id.mf_new:
                        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                        next(RegisterUserActivity.class, false);
                        break;
                    case R.id.mf_search:
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                        next(Constants.TAG_LIST, (Serializable) userList, SearchUserActivity.class, false);
                        break;
                }
                return true;
                //Vehicle
            case Constants.SM_VEHICLE:
                switch (item.getItemId()) {
                    case R.id.mf_new:
                        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                        next(RegisterVehicleActivity.class, false);
                        break;
                    case R.id.mf_search:
                        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                        next(Constants.TAG_LIST, (Serializable) vehicleList, SearchVehicleActivity.class, false);
                        break;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void eventUI(){
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    public void serviceFailureReport(){
        FailureReportInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(FailureReportInterface.class);
        Call<FailureResponse> mCall = mInterface.getFailuresReports();
        mCall.enqueue(new Callback<FailureResponse>() {
            @Override
            public void onResponse(Call<FailureResponse> call, Response<FailureResponse> response) {
                Log.d(TAG, "Response: "+response.body().toString());
                if (response.body().getStatus().getCode() == 404){

                }else{
                    failureReports = new ArrayList<FailureReport>();
                    failureReports = response.body().getResults().getFailureReports();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                    mRecyclerView.setAdapter(new FailureAdapter(ctx, response.body().getResults().getFailureReports(), object -> {
                        FailureReport failureReport = (FailureReport) object;
                        Toast.makeText(ctx, failureReport.getCod_report(), Toast.LENGTH_SHORT).show();
                    }));
                }
            }

            @Override
            public void onFailure(Call<FailureResponse> call, Throwable t) {
                Toast.makeText(ctx, Constants.MJS_ERROR_CONEXION_SERVICIO, Toast.LENGTH_SHORT).show();
                Log.e(Constants.ERROR, Constants.MJS_ERROR_CONEXION_SERVICIO);
            }
        });
    }

    public void serviceUsers(){
        UserInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(UserInterface.class);
        Call<UsersResponse> mCall = mInterface.getEmployees();
        mCall.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                Log.d(TAG, "Response: "+response.body().toString());
                if (response.body().getStatus().getCode() == 404){

                }else{
                    userList = new ArrayList<User>();
                    userList = response.body().getResults().getUsers();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                    mRecyclerView.setAdapter(new UserAdapter(ctx, response.body().getResults().getUsers(), object -> {
                        User user = (User) object;
                        Toast.makeText(ctx, user.getNames(), Toast.LENGTH_SHORT).show();
                    }));
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(ctx, Constants.MJS_ERROR_CONEXION_SERVICIO, Toast.LENGTH_SHORT).show();
                Log.e(Constants.ERROR, Constants.MJS_ERROR_CONEXION_SERVICIO);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSubMenu();
    }

    public void serviceVehicles(){
        VehicleInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(VehicleInterface.class);
        Call<VehicleResponse> mCall = mInterface.getVehicles();
        mCall.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                Log.d(TAG, "Response: "+response.body().toString());
                if (response.body().getStatus().getCode() == 404){

                }else{
                    vehicleList = new ArrayList<Vehicle>();
                    vehicleList = response.body().getResults().getVehicles();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                    mRecyclerView.setAdapter(new VehicleAdapter(ctx, response.body().getResults().getVehicles(), object -> {
                        FailureReport failureReport = (FailureReport) object;
                        Toast.makeText(ctx, failureReport.getCod_report(), Toast.LENGTH_SHORT).show();
                    }));
                }
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                Toast.makeText(ctx, Constants.MJS_ERROR_CONEXION_SERVICIO, Toast.LENGTH_SHORT).show();
                Log.e(Constants.ERROR, Constants.MJS_ERROR_CONEXION_SERVICIO);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
