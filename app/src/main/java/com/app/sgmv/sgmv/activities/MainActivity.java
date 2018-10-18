package com.app.sgmv.sgmv.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.entities.user.LoginResponse;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.GlideApp;
import com.app.sgmv.sgmv.utilities.PreferencesHelper;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_name) TextView mName;
    @BindView(R.id.tv_rol) TextView mRol;
    @BindView(R.id.civ_photo) CircleImageView mPhoto;

    @BindView(R.id.cv_magnament) CardView mMagnament;
    @BindView(R.id.cv_register) CardView mRegister;
    @BindView(R.id.cv_report) CardView mReport;
    @BindView(R.id.cv_search) CardView mSearch;

    @BindView(R.id.ic_exit) ImageView mExit;

    private String TAG = MainActivity.class.getSimpleName();
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = getApplicationContext();
        initButterKinife();
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

        String[] menuItem = {Constants.M_MAGNAMENT, Constants.M_REGISTER, Constants.M_REPORT, Constants.M_SEARCH};

        if(loginResponse.getResults().getModules() != null){
            for (int i=0; i<loginResponse.getResults().getModules().size(); i++){
                for (int j=0; j<menuItem.length; j++){
                    if(menuItem[j].equalsIgnoreCase(loginResponse.getResults().getModules().get(i).getModules())){
                        Log.d(TAG, loginResponse.getResults().getModules().get(i).getModules());
                        switch (loginResponse.getResults().getModules().get(i).getModules()){
                            case Constants.M_MAGNAMENT:
                                mMagnament.setVisibility(View.VISIBLE);
                                break;
                            case Constants.M_REGISTER:
                                mRegister.setVisibility(View.VISIBLE);
                                break;
                            case Constants.M_REPORT:
                                mReport.setVisibility(View.VISIBLE);
                                break;
                            case Constants.M_SEARCH:
                                mSearch.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                }
            }
        }else{
            Toast.makeText(ctx, "Usted no cuenta con pemrisos", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Usted no cuenta con permisos");
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
    @OnClick({R.id.cv_magnament, R.id.cv_register, R.id.cv_report, R.id.cv_search, R.id.ic_exit})
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.cv_magnament:
                Toast.makeText(ctx, Constants.M_MAGNAMENT, Toast.LENGTH_SHORT).show();
                next(Constants.TAG_MODULE, Constants.M_MAGNAMENT, SubModulesActivity.class, false);
                break;
            case R.id.cv_register:
                Toast.makeText(ctx, Constants.M_REGISTER, Toast.LENGTH_SHORT).show();
                next(Constants.TAG_MODULE, Constants.M_REGISTER, SubModulesActivity.class, false);
                break;
            case R.id.cv_report:
                Toast.makeText(ctx, Constants.M_REPORT, Toast.LENGTH_SHORT).show();
                next(Constants.TAG_MODULE, Constants.M_REPORT, SubModulesActivity.class, false);
                break;
            case R.id.cv_search:
                Toast.makeText(ctx, Constants.M_SEARCH, Toast.LENGTH_SHORT).show();
                next(Constants.TAG_MODULE, Constants.M_SEARCH, SubModulesActivity.class, false);
                break;
            case R.id.ic_exit:
                PreferencesHelper.cleanMyUserPref(ctx);
                /*
                Intent mIntent = new Intent(this, PermissionActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 */
                next(LoginActivity.class, true);
                break;
        }
    }

}
