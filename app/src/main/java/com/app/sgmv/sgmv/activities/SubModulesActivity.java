package com.app.sgmv.sgmv.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.activities.failure.SearchFailureReportActivity;
import com.app.sgmv.sgmv.activities.user.SearchUserActivity;
import com.app.sgmv.sgmv.activities.vehicle.SearchVehicleActivity;
import com.app.sgmv.sgmv.entities.user.LoginResponse;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.PreferencesHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class SubModulesActivity extends BaseActivity{

    @BindView(R.id.iv_back) ImageView mBack;
    @BindView(R.id.tv_title_activity) TextView mTitle;

    @BindView(R.id.cv_failure) CardView mFailure;
    @BindView(R.id.cv_users) CardView mUsers;
    @BindView(R.id.cv_safe) CardView mSafe;
    @BindView(R.id.cv_maintenance) CardView mMaintenance;
    @BindView(R.id.cv_vehicle) CardView mVehicles;
    @BindView(R.id.cv_costs) CardView mCosts;

    private String TAG = SubModulesActivity.class.getSimpleName();
    private Context ctx = this;
    private String module = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_modules);
        initButterKinife();

        mBack.setOnClickListener(v -> finish());

        module = getIntent().getStringExtra(Constants.TAG_MODULE);
        Log.d(TAG, "Module ---> "+module);

        mTitle.setText(module);

        LoginResponse loginResponse = PreferencesHelper.getMyUserPref(ctx);

        String[] menuItem = {Constants.M_MAGNAMENT, Constants.M_REGISTER, Constants.M_REPORT, Constants.M_SEARCH};

        if(loginResponse.getResults().getModules() != null){

            switch (loginResponse.getResults().getUser().getRol()){
                case Constants.BOSS_OPERATIONS:
                    mFailure.setVisibility(View.VISIBLE);
                    mUsers.setVisibility(View.VISIBLE);
                    mSafe.setVisibility(View.VISIBLE);
                    mMaintenance.setVisibility(View.VISIBLE);
                    mVehicles.setVisibility(View.VISIBLE);

                    break;
                case Constants.DRIVER:
                    mFailure.setVisibility(View.VISIBLE);
                    mUsers.setVisibility(View.VISIBLE);
                    mSafe.setVisibility(View.VISIBLE);
                    mMaintenance.setVisibility(View.VISIBLE);
                    mVehicles.setVisibility(View.VISIBLE);
                    break;
                case Constants.MANAGEMENT:
                    mFailure.setVisibility(View.VISIBLE);
                    mUsers.setVisibility(View.VISIBLE);
                    mSafe.setVisibility(View.VISIBLE);
                    mMaintenance.setVisibility(View.VISIBLE);
                    mVehicles.setVisibility(View.VISIBLE);
                    break;
            }

            if(module.equalsIgnoreCase(Constants.M_SEARCH)){
                mCosts.setVisibility(View.VISIBLE);
            }

        }else{
            Toast.makeText(ctx, "Usted no cuenta con pemrisos", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Usted no cuenta con permisos");
        }

    }

    @Nullable
    @OnClick({R.id.cv_failure, R.id.cv_users, R.id.cv_safe, R.id.cv_maintenance, R.id.cv_vehicle, R.id.cv_costs})
    public void onCllick(View view) {
        switch (view.getId()){
            //FAILURE
            case R.id.cv_failure:
                Toast.makeText(ctx, Constants.SM_FAILURE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.M_MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.SM_FAILURE, ListActivity.class, false);
                        break;
                    case Constants.M_REGISTER:
                        //
                        break;
                    case Constants.M_REPORT:
                        //
                        break;
                    case Constants.M_SEARCH:
                        next(SearchFailureReportActivity.class, false);
                        break;
                }
                break;
            //USERS
            case R.id.cv_users:
                Toast.makeText(ctx, Constants.SM_USERS, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.M_MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.SM_USERS, ListActivity.class, false);
                        break;
                    case Constants.M_REGISTER:
                        //
                        break;
                    case Constants.M_REPORT:
                        //
                        break;
                    case Constants.M_SEARCH:
                        next(Constants.TAG_TYPE_EMPLOYEE, Constants.TYPE_EMPLOYEE, SearchUserActivity.class, false);
                        break;
                }
                break;
            //SAFE
            case R.id.cv_safe:
                Toast.makeText(ctx, Constants.SM_SAFE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.M_MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.SM_SAFE, ListActivity.class, false);
                        break;
                    case Constants.M_REGISTER:
                        //
                        break;
                    case Constants.M_REPORT:
                        //
                        break;
                    case Constants.M_SEARCH:
                        //
                        break;
                }
                break;
            //MAINTENANCE
            case R.id.cv_maintenance:
                Toast.makeText(ctx, Constants.SM_MAINTENANCE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.M_MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.SM_MAINTENANCE, ListActivity.class, false);
                        break;
                    case Constants.M_REGISTER:
                        //
                        break;
                    case Constants.M_REPORT:
                        //
                        break;
                    case Constants.M_SEARCH:
                        //
                        break;
                }
                break;
            //VEHICLE
            case R.id.cv_vehicle:
                Toast.makeText(ctx, Constants.SM_VEHICLE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.M_MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.SM_VEHICLE, ListActivity.class, false);
                        break;
                    case Constants.M_REGISTER:
                        //
                        break;
                    case Constants.M_REPORT:
                        //
                        break;
                    case Constants.M_SEARCH:
                        next(SearchVehicleActivity.class, false);
                        break;
                }
                break;
            //COSTS
            case R.id.cv_costs:
                Toast.makeText(ctx, Constants.SM_COSTOS, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.M_MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.SM_COSTOS, ListActivity.class, false);
                        break;
                    case Constants.M_REGISTER:
                        //
                        break;
                    case Constants.M_REPORT:
                        //
                        break;
                    case Constants.M_SEARCH:
                        //
                        break;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
