package com.app.sgmv.sgmv.activities.failure;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.adapters.FailureAdapter;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.failure.FailureReport;
import com.app.sgmv.sgmv.entities.failure.FailureResponse;
import com.app.sgmv.sgmv.services.FailureReportInterface;
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

public class SearchFailureReportActivity extends BaseActivity {

    Toolbar mToolbar;
    FailureAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<FailureReport> productlists;
    TextView mEmptyView;

    private Context ctx = this;
    private String TAG = SearchFailureReportActivity.class.getSimpleName();
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_failure_report);
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
        productlists = (List<FailureReport>) i.getSerializableExtra(Constants.TAG_LIST);

        if(productlists != null){
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mAdapter = new FailureAdapter(ctx, productlists, object -> {
                FailureReport failureReport = (FailureReport) object;
                Toast.makeText(ctx, failureReport.getCod_report(), Toast.LENGTH_SHORT).show();
            });

            mRecyclerView.setAdapter(mAdapter);
        }else{
            serviceFailureReport();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(mSearchView);
        mSearchView.setQueryHint("Código de reporte de avería");

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
                final  List<FailureReport> filtermodelist = filter(productlists,newText);
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

    private List<FailureReport> filter(List<FailureReport> pl,String query) {
        query=query.toLowerCase();
        final List<FailureReport> filteredModeList = new ArrayList<FailureReport>();
        for (FailureReport model : pl)
        {
            final String text=model.getCod_report().toLowerCase();
            if (text.startsWith(query) || text.contains(query)) {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
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
                    productlists = new ArrayList<FailureReport>();
                    productlists = response.body().getResults().getFailureReports();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                    mAdapter = new FailureAdapter(ctx, productlists, object -> {
                        FailureReport failureReport = (FailureReport) object;
                        Toast.makeText(ctx, failureReport.getCod_report(), Toast.LENGTH_SHORT).show();
                        finish();
                    });

                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<FailureResponse> call, Throwable t) {
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
