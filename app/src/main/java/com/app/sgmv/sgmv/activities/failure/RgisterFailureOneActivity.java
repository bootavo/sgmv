package com.app.sgmv.sgmv.activities.failure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.activities.user.SearchUserActivity;
import com.app.sgmv.sgmv.activities.vehicle.SearchVehicleActivity;
import com.app.sgmv.sgmv.entities.failure.FailureReport;
import com.app.sgmv.sgmv.entities.user.LoginResponse;
import com.app.sgmv.sgmv.listeners.OnDialogResult;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.DateDialogUtils;
import com.app.sgmv.sgmv.utilities.PreferencesHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gtufinof on 6/13/18.
 */

public class RgisterFailureOneActivity extends BaseActivity{

    @BindView(R.id.fab_driver) FloatingActionButton btnDriver;
    @BindView(R.id.fab_tract) FloatingActionButton btnTract;
    @BindView(R.id.fab_semi_trailer) FloatingActionButton btnSemiTrailer;
    @BindView(R.id.fab_date) FloatingActionButton btnDate;

    @BindView(R.id.et_driver) EditText mDriver;
    @BindView(R.id.et_tract) EditText mTract;
    @BindView(R.id.et_semi_trailer) EditText mSemiTrailer;
    @BindView(R.id.et_date) EditText mDate;
    @BindView(R.id.et_mileage) EditText mMileage;

    @BindView(R.id.v_tab) View mView;

    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.iv_back) ImageView btnBack;

    private String TAG = RgisterFailureOneActivity.class.getSimpleName();
    public static Activity activity;
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_one);
        initButterKinife();
        setViewStep();

        mDriver.setText("Gustavo");
        mTract.setText("DAS-224");
        mSemiTrailer.setText("FEG-234");
        mDate.setText("1994-12-12");
        mMileage.setText("21.0");

        activity = this;
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/4;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*1,4);
        mView.setLayoutParams(parms);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.fab_driver, R.id.fab_tract, R.id.fab_semi_trailer, R.id.fab_date, R.id.btn_next, R.id.iv_back})
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.fab_driver:
                next(Constants.TAG_TYPE_EMPLOYEE, Constants.TYPE_DRIVER, SearchUserActivity.class, false);
                break;
            case R.id.fab_tract:
                next(Constants.TAG_TYPE_VEHICLE, Constants.TYPE_TRACT, SearchVehicleActivity.class, false);
                break;
            case R.id.fab_semi_trailer:
                next(Constants.TAG_TYPE_VEHICLE, Constants.TYPE_SEMITRAILER, SearchVehicleActivity.class, false);
                break;
            case R.id.fab_date:
                showDialog();
                break;
            case R.id.btn_next:
                if(verifyFields()){

                    LoginResponse loginResponse = PreferencesHelper.getMyUserPref(ctx);

                    Constants.FAILURE_REPORT_OBJECT = new FailureReport();
                    Constants.FAILURE_REPORT_OBJECT.setSgmv_employee_id(loginResponse.getResults().getUser().getSgmv_employee_id());
                    Constants.FAILURE_REPORT_OBJECT.setSgmv_driver_id(Constants.EMPLOYEE_ID);
                    Constants.FAILURE_REPORT_OBJECT.setSgmv_tract_id(Constants.TRACT_ID);
                    Constants.FAILURE_REPORT_OBJECT.setSgmv_semitrailer_id(Constants.SEMITRAILER_ID);
                    Constants.FAILURE_REPORT_OBJECT.setMileage(Float.parseFloat(mMileage.getText().toString()));
                    Constants.FAILURE_REPORT_OBJECT.setDate(mDate.getText().toString());
                    nextActivity();
                }else{
                    Toast.makeText(ctx, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "driver_id: "+Constants.EMPLOYEE_ID);
        Log.d(TAG, "tract_id: "+Constants.TRACT_PLACA);
        Log.d(TAG, "semitrailer_id: "+Constants.SEMITRAILER_PLACA);
        mDriver.setText(Constants.EMPLOYEE_NAME);
        mTract.setText(Constants.TRACT_PLACA);
        mSemiTrailer.setText(Constants.SEMITRAILER_PLACA);
    }

    public void showDialog(){
        DateDialogUtils.showDialog(this, this, true, new OnDialogResult() {
            @Override
            public void onOk(String year, String month, String day) {
                Log.d("gus", "id_year: "+year+", id_month: "+month+", day: "+day);
                mDate.setText(year+"-"+month+"-"+day);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public String getMileage(){
        return mMileage.getText().toString();
    }

    public boolean verifyFields(){
        if(getMileage().equals("")){
            return false;
        }

        if(getMileage().length()== 0){
            return false;
        }

        if(Double.parseDouble(getMileage()) < 0.1){
            return false;
        }

        if(mDriver.getText().toString().equals("")){
            return false;
        }

        if(mTract.getText().toString().equals("")){
            return false;
        }

        if(mSemiTrailer.getText().toString().equals("")){
            return false;
        }

        if(mDate.getText().toString().equals("")){
            return false;
        }

        return true;

    }

    public void nextActivity(){
        Toast.makeText(ctx, "Siguiente", Toast.LENGTH_SHORT).show();
        next(RegisterFailureTwoActivity.class, false);
    }

}
