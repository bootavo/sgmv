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
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        module = getIntent().getStringExtra(Constants.TAG_MODULE);
        Log.d(TAG, "Module ---> "+module);

        mTitle.setText(module);

        if(module.equalsIgnoreCase(Constants.SEARCH)){
            mCosts.setVisibility(View.VISIBLE);
        }

    }

    @Nullable
    @OnClick({R.id.cv_failure, R.id.cv_users, R.id.cv_safe, R.id.cv_maintenance, R.id.cv_vehicle, R.id.cv_costs})
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.cv_failure:
                Toast.makeText(ctx, Constants.FAILURE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.MAGNAMENT:
                        next(Constants.TAG_MODULE, Constants.FAILURE, FailureActivity.class, false);
                        next(RegisterNewFailure.class, false);
                        break;
                    case Constants.REGISTER:
                        //
                        break;
                    case Constants.REPORT:
                        //
                        break;
                    case Constants.SEARCH:
                        //
                        break;
                }
                break;
            case R.id.cv_users:
                Toast.makeText(ctx, Constants.USERS, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.MAGNAMENT:
                        //
                        break;
                    case Constants.REGISTER:
                        //
                        break;
                    case Constants.REPORT:
                        //
                        break;
                    case Constants.SEARCH:
                        //
                        break;
                }
                break;
            case R.id.cv_safe:
                Toast.makeText(ctx, Constants.SAFE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.MAGNAMENT:
                        //
                        break;
                    case Constants.REGISTER:
                        //
                        break;
                    case Constants.REPORT:
                        //
                        break;
                    case Constants.SEARCH:
                        //
                        break;
                }
                break;
            case R.id.cv_maintenance:
                Toast.makeText(ctx, Constants.MAINTENANCE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.MAGNAMENT:
                        //
                        break;
                    case Constants.REGISTER:
                        //
                        break;
                    case Constants.REPORT:
                        //
                        break;
                    case Constants.SEARCH:
                        //
                        break;
                }
                break;
            case R.id.cv_vehicle:
                Toast.makeText(ctx, Constants.VEHICLE, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.MAGNAMENT:
                        //
                        break;
                    case Constants.REGISTER:
                        //
                        break;
                    case Constants.REPORT:
                        //
                        break;
                    case Constants.SEARCH:
                        //
                        break;
                }
                break;
            case R.id.cv_costs:
                Toast.makeText(ctx, Constants.COSTOS, Toast.LENGTH_SHORT).show();
                switch (module){
                    case Constants.MAGNAMENT:
                        //
                        break;
                    case Constants.REGISTER:
                        //
                        break;
                    case Constants.REPORT:
                        //
                        break;
                    case Constants.SEARCH:
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
