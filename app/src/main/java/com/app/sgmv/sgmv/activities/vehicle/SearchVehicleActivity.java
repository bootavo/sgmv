package com.app.sgmv.sgmv.activities.vehicle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.adapters.VehicleAdapter;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.vehicle.Vehicle;
import com.app.sgmv.sgmv.entities.vehicle.VehicleResponse;
import com.app.sgmv.sgmv.services.VehicleInterface;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.GridSpacingItemDecoration;
import com.app.sgmv.sgmv.utilities.InputSoftKeyboardHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gtufinof on 6/17/18.
 */

public class SearchVehicleActivity extends BaseActivity {

    Toolbar mToolbar;
    VehicleAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<Vehicle> mList;
    TextView mEmptyView;

    private SearchView mSearchView;

    private Context ctx = this;
    private String TAG = SearchVehicleActivity.class.getSimpleName();
    private SearchVehicleActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_failure_report);
        //getFailureReports();
        initButterKinife();
        initControllers();
        activity = this;
    }

    public void initControllers(){
        setContentView(R.layout.activity_search_failure_report);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Buscar Avería");

        mRecyclerView = (RecyclerView) findViewById(R.id.listshow);
        mEmptyView = (TextView) findViewById(R.id.emptyView);

        Intent i = getIntent();
        mList = (List<Vehicle>) i.getSerializableExtra(Constants.TAG_LIST);

        if(mList != null){
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mAdapter = new VehicleAdapter(ctx, mList, object -> {
                Vehicle vehicle = (Vehicle) object;
                Toast.makeText(ctx, vehicle.getPlaca(), Toast.LENGTH_SHORT).show();
                finish();
            });

            mRecyclerView.setAdapter(mAdapter);
        }else{
            Intent mIntent = getIntent();
            String type = mIntent.getStringExtra(Constants.TAG_TYPE_VEHICLE);
            serviceFailureReport(type);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        mSearchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(mSearchView);
        mSearchView.setQueryHint("Placa de vehículo");

        myActionMenuItem.expandActionView(); // add for expand toolbar
        mSearchView.setIconified(false); //add for expand toolbar

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!mSearchView.isIconified()) {
                    mSearchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final  List<Vehicle> filtermodelist = filter(mList, newText);
                mAdapter.setfilter(filtermodelist);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    private List<Vehicle> filter(List<Vehicle> pl,String query) {
        query=query.toLowerCase();
        final List<Vehicle> filteredModeList = new ArrayList<Vehicle>();
        for (Vehicle model : pl)
        {
            final String text= model.getPlaca().toLowerCase();
            if (text.startsWith(query) || text.contains(query)) {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    public void serviceFailureReport(String type){
        VehicleInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(VehicleInterface.class);
        Call<VehicleResponse> mCall = null;

        if(type == null){
            mCall = mInterface.getVehicles();
        } else if(type.equals(Constants.TYPE_TRACT)){
            mCall = mInterface.getTracts();
        }else if(type.equals(Constants.TYPE_SEMITRAILER)){
            mCall = mInterface.getSemitracts();
        }
        mCall.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                Log.d(TAG, "Response: "+response.body().toString());
                if (response.body().getStatus().getCode() == 404){

                }else{
                    mList = new ArrayList<Vehicle>();
                    mList = response.body().getResults().getVehicles();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                    mAdapter = new VehicleAdapter(ctx, mList, object -> {
                        Vehicle vehicle = (Vehicle) object;
                        if(type.equals(Constants.TYPE_TRACT)){
                            Constants.TRACT_ID = vehicle.getSgmv_vehicle_id();
                            Constants.TRACT_PLACA = vehicle.getPlaca();
                        }else{
                            Constants.SEMITRAILER_ID = vehicle.getSgmv_vehicle_id();
                            Constants.SEMITRAILER_PLACA = vehicle.getPlaca();
                        }
                        Toast.makeText(ctx, vehicle.getPlaca(), Toast.LENGTH_SHORT).show();
                        mSearchView.clearFocus();
                        finish();
                    });

                    mRecyclerView.setAdapter(mAdapter);
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
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");
        InputSoftKeyboardHelper.hideInputSoft(activity);
    }

}
