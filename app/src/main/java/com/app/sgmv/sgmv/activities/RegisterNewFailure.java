package com.app.sgmv.sgmv.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.utilities.BaseActivity;

/**
 * Created by gtufinof on 6/13/18.
 */

public class RegisterNewFailure extends BaseActivity{

    private String TAG = RegisterNewFailure.class.getSimpleName();
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_failure);
        initButterKinife();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showDialog(){
        AlertDialogUtils.showMonthPicker((Activity) context, new OnDialogResult() {
            @Override
            public void onOk(String id_year, String id_month) {
                Log.d("gus", "id_year: "+id_year+", id_month: "+id_month);
                obtenerHistorial(id_month, id_year);
            }

            @Override
            public void onCancel() {

            }
        });
    }
}
