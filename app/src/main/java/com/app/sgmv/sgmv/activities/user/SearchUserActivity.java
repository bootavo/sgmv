package com.app.sgmv.sgmv.activities.user;

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
import com.app.sgmv.sgmv.adapters.UserAdapter;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.user.User;
import com.app.sgmv.sgmv.entities.user.UsersResponse;
import com.app.sgmv.sgmv.services.UserInterface;
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

public class SearchUserActivity extends BaseActivity {

    Toolbar mToolbar;
    UserAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<User> mList;
    TextView mEmptyView;
    private SearchView mSearchView;

    private Context ctx = this;
    private String TAG = SearchUserActivity.class.getSimpleName();
    private SearchUserActivity activity;

    //SearchView searchView;
    //FailureAdapter adapter;
    //List<FailureReport> mList;

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
        mList = (List<User>) i.getSerializableExtra(Constants.TAG_LIST);

        if(mList != null){
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mAdapter = new UserAdapter(ctx, mList, object -> {
                User user = (User) object;
                Toast.makeText(ctx, user.getNames(), Toast.LENGTH_SHORT).show();
                finish();
            });

            mRecyclerView.setAdapter(mAdapter);
        }else{
            Intent mIntent = getIntent();
            String type = mIntent.getStringExtra(Constants.TAG_TYPE_EMPLOYEE);
            serviceFailureReport(type);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        mSearchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(mSearchView);
        mSearchView.setQueryHint("Nombre de empleado");

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
                final  List<User> filtermodelist = filter(mList,newText);
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

    private List<User> filter(List<User> pl,String query) {
        query=query.toLowerCase();
        final List<User> filteredModeList = new ArrayList<User>();
        for (User model : pl)
        {
            final String text= (model.getNames()+" "+model.getLast_name()).toLowerCase();
            if (text.startsWith(query) || text.contains(query)) {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    public void serviceFailureReport(String type){
        UserInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(UserInterface.class);
        Call<UsersResponse> mCall = null;
        if(type.equals(Constants.TYPE_EMPLOYEE)){
            mCall = mInterface.getEmployees();
        }else if(type.equals(Constants.TYPE_DRIVER)){
            mCall = mInterface.getDrivers();
        }
        mCall.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                Log.d(TAG, "Response: "+response.body().toString());
                if (response.body().getStatus().getCode() == 404){

                }else{
                    mList = new ArrayList<User>();
                    mList = response.body().getResults().getUsers();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, GridSpacingItemDecoration.dpToPx(10, ctx), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                    mAdapter = new UserAdapter(ctx, mList, object -> {
                        User user = (User) object;
                        Constants.EMPLOYEE_ID = user.getSgmv_employee_id();
                        Constants.EMPLOYEE_NAME = user.getNames()+" "+user.getLast_name();
                        Toast.makeText(ctx, user.getNames(), Toast.LENGTH_SHORT).show();
                        mSearchView.clearFocus();
                        finish();
                    });

                    mRecyclerView.setAdapter(mAdapter);
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
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");
        InputSoftKeyboardHelper.hideInputSoft(activity);
    }

}
