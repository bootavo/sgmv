package com.app.sgmv.sgmv.utilities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.sgmv.sgmv.R;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by bootavo on 20/11/2017.
 */



public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/MMRTEXT.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        */

    }

    public void initButterKinife(){
        ButterKnife.bind(this);
    }

    //Method better thet start activity
    public void next(Class<?> activity, boolean isDestroy){

        startActivity(new Intent(this,activity));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if (isDestroy) finish();
    }

    public void next(String name, @Nullable Object object, Class<?> activity, boolean isDestroy){
        Intent intent = new Intent(this,activity);
        if(object != null) intent.putExtra(name, (Serializable) object);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if (isDestroy) finish();
    }

    public void next(String name, String data, Class<?> activity, boolean isDestroy){

        Intent intent = new Intent(this,activity);
        if(data != null) intent.putExtra(name, data);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if (isDestroy) finish();
    }

}