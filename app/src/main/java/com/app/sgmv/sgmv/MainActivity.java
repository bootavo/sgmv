package com.app.sgmv.sgmv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.entities.LoginResponse;
import com.app.sgmv.sgmv.entities.Modules;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.GlideApp;
import com.app.sgmv.sgmv.utilities.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_name) TextView mName;
    @BindView(R.id.tv_rol) TextView mRol;
    @BindView(R.id.civ_photo) CircleImageView mPhoto;

    @BindView(R.id.cv_magnament) CardView mMagnament;
    @BindView(R.id.cv_register) CardView mRegister;
    @BindView(R.id.cv_report) CardView mReport;
    @BindView(R.id.cv_search) CardView mSearch;

    private String TAG = MainActivity.class.getSimpleName();
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ctx = getApplicationContext();
        loadUserInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void loadUserInfo(){
        LoginResponse loginResponse = PreferencesHelper.getMyUserPref(ctx);

        GlideApp
                .with(ctx)
                .load(loginResponse.getResults().getUser().getPhoto())
                .error(R.drawable.ic_man)
                .placeholder(R.drawable.ic_man)
                .centerCrop()
                .into(mPhoto);

        mName.setText(loginResponse.getResults().getUser().getNames()+" "+loginResponse.getResults().getUser().getLast_name());
        mRol.setText(loginResponse.getResults().getUser().getRol());

        String[] menuItem = {Constants.MAGNAMENT, Constants.REGISTER, Constants.REPORT, Constants.SEARCH};

        if(loginResponse.getResults().getModules() != null){
            for (int i=0; i<loginResponse.getResults().getModules().size(); i++){
                for (int j=0; j<menuItem.length; j++){
                    if(menuItem[j].equalsIgnoreCase(loginResponse.getResults().getModules().get(i).getModules())){
                        Log.d(TAG, loginResponse.getResults().getModules().get(i).getModules());
                        switch (loginResponse.getResults().getModules().get(i).getModules()){
                            case "Gestión de Operaciones":
                                mMagnament.setVisibility(View.VISIBLE);
                                break;
                            case "Registro":
                                mRegister.setVisibility(View.VISIBLE);
                                break;
                            case "Reportes":
                                mReport.setVisibility(View.VISIBLE);
                                break;
                            case "Búsqueda":
                                mSearch.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                }
            }
        }else{
            Toast.makeText(ctx, "Usted no cuenta con pemrisos", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Usted no cuenta con pemrisos");
        }

        /*
        for (Modules module : modules){
            if (modules.contains(module.getModules())){
                Log.d(TAG, module.getModules());
            }else{
                Log.d(TAG, "xd");
            }
        }
        */

    }


    @Nullable
    @OnClick({R.id.cv_magnament, R.id.cv_register, R.id.cv_report, R.id.cv_search})
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.cv_magnament:
                Toast.makeText(ctx, "Magnament", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_register:
                Toast.makeText(ctx, "Register", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_report:
                Toast.makeText(ctx, "Report", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_search:
                Toast.makeText(ctx, "Search", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
