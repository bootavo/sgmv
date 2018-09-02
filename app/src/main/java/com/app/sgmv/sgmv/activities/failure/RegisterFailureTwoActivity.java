package com.app.sgmv.sgmv.activities.failure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.entities.failure.MainComponent;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gtufinof on 6/13/18.
 */

public class RegisterFailureTwoActivity extends BaseActivity{

    @BindView(R.id.v_tab) View mView;

    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.iv_back) ImageView btnBack;

    @BindView(R.id.sw_motor) Switch mSwitchMotor;
    @BindView(R.id.rl_motor) LinearLayout mRLMotor;
    @BindView(R.id.et_motor) EditText mMotor;

    @BindView(R.id.sw_caja) Switch mSwitchCaja;
    @BindView(R.id.rl_caja) LinearLayout mRLcaja;
    @BindView(R.id.et_caja) EditText mCaja;

    @BindView(R.id.sw_corona) Switch mSwitchCorona;
    @BindView(R.id.rl_corona) LinearLayout mRLCorona;
    @BindView(R.id.et_corona) EditText mCorona;

    @BindView(R.id.sw_sistema) Switch mSwitchSistema;
    @BindView(R.id.rl_sistema) LinearLayout mRLSistema;
    @BindView(R.id.et_sistema) EditText mSistema;

    @BindView(R.id.sw_luces) Switch mSwitchLuces;
    @BindView(R.id.rl_luces) LinearLayout mRLLuces;
    @BindView(R.id.et_luces) EditText mLuces;

    @BindView(R.id.sw_frenos) Switch mSwitchFrenos;
    @BindView(R.id.rl_frenos) LinearLayout mRLFrenos;
    @BindView(R.id.et_frenos) EditText mFrenos;

    @BindView(R.id.sw_direccion) Switch mSwitchDireccion;
    @BindView(R.id.rl_direccion) LinearLayout mRLDireccion;
    @BindView(R.id.et_direccion) EditText mDireccion;

    @BindView(R.id.sw_rueda) Switch mSwitchRueda;
    @BindView(R.id.rl_rueda) LinearLayout mRLRueda;
    @BindView(R.id.et_rueda) EditText mRueda;

    @BindView(R.id.sw_suspension) Switch mSwitchSuspension;
    @BindView(R.id.rl_suspension) LinearLayout mRLSuspension;
    @BindView(R.id.et_suspension) EditText mSuspension;

    @BindView(R.id.sw_muelles) Switch mSwitchMuelles;
    @BindView(R.id.rl_muelles) LinearLayout mRLMuelles;
    @BindView(R.id.et_muelles) EditText mMuelles;

    @BindView(R.id.sw_cabina) Switch mSwitchCabina;
    @BindView(R.id.rl_cabina) LinearLayout mRLCabina;
    @BindView(R.id.et_cabina) EditText mCabina;

    @BindView(R.id.sw_rueda_carreta) Switch mSwitchRuedaCarreta;
    @BindView(R.id.rl_rueda_carreta) LinearLayout mRLRuedaCarreta;
    @BindView(R.id.et_rueda_carreta) EditText mRuedaCarreta;

    @BindView(R.id.sw_freno_carreta) Switch mSwitchFrenoCarreta;
    @BindView(R.id.rl_freno_carreta) LinearLayout mRLFrenoCarreta;
    @BindView(R.id.et_freno_carreta) EditText mFrenoCarreta;

    @BindView(R.id.sw_muelle_carreta) Switch mSwitchMuelleCarreta;
    @BindView(R.id.rl_muelle_carreta) LinearLayout mRLMuelleCarreta;
    @BindView(R.id.et_muelle_carreta) EditText mMuelleCarreta;

    @BindView(R.id.sw_soporte) Switch mSwitchSoporte;
    @BindView(R.id.rl_soporte) LinearLayout mRLSoporte;
    @BindView(R.id.et_soporte) EditText mSoporte;

    @BindView(R.id.sw_carroceria) Switch mSwitchCarroceria;
    @BindView(R.id.rl_carroceria) LinearLayout mRLCarroceria;
    @BindView(R.id.et_carroceria) EditText mCarroceria;

    List<Integer> myList = new ArrayList<Integer>();
    List<MainComponent> mMainComponent = new ArrayList<>();

    private String TAG = RegisterFailureTwoActivity.class.getSimpleName();
    public static Activity activity;
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_two);
        initButterKinife();
        setViewStep();
        initSwitchers();

        activity = this;
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/4;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*2,4);
        mView.setLayoutParams(parms);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.btn_next, R.id.iv_back})
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
                if(verifyFields()){
                    Constants.LIST_MAIN_COMPONENTE = new ArrayList<>();
                    Constants.LIST_MAIN_COMPONENTE = mMainComponent;
                    nextActivity();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    public boolean verifyFields(){
        createJsonObject();
        if(mMainComponent.size()>=1){
            Log.d(TAG, "Main component con data");
            return true;
        }else{
            Log.d(TAG, "Main component sin data");
            return false;
        }
    }

    public void nextActivity(){
        Toast.makeText(ctx, "Siguiente", Toast.LENGTH_SHORT).show();
        next(RegisterFailureThreeActivity.class, false);
    }

    public void createJsonObject(){
        for(int i: myList){
            getValuesFromNumberId(i);
            Log.d(TAG, "Main component: "+i);
        }
    }

    public void getValuesFromNumberId(int id){

        MainComponent entitie = null;

        if(id == 1){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(1);
            entitie.setDescription(mMotor.getText().toString());
        }else if(id == 2){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(2);
            entitie.setDescription(mCaja.getText().toString());
        }else if(id == 3){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(3);
            entitie.setDescription(mCorona.getText().toString());
        }else if(id == 4){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(4);
            entitie.setDescription(mSistema.getText().toString());
        }else if(id == 5){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(5);
            entitie.setDescription(mLuces.getText().toString());
        }else if(id == 6){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(6);
            entitie.setDescription(mFrenos.getText().toString());
        }else if(id == 7){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(7);
            entitie.setDescription(mDireccion.getText().toString());
        }else if(id == 8){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(8);
            entitie.setDescription(mRueda.getText().toString());
        }else if(id == 9){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(9);
            entitie.setDescription(mSuspension.getText().toString());
        }else if(id == 10){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(10);
            entitie.setDescription(mMuelles.getText().toString());
        }else if(id == 11){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(11);
            entitie.setDescription(mCabina.getText().toString());
        }else if(id == 12){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(12);
            entitie.setDescription(mRuedaCarreta.getText().toString());
        }else if(id == 13){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(13);
            entitie.setDescription(mFrenoCarreta.getText().toString());
        }else if(id == 14){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(14);
            entitie.setDescription(mMuelleCarreta.getText().toString());
        }else if(id == 15){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(15);
            entitie.setDescription(mSoporte.getText().toString());
        }else if(id == 16){
            entitie = new MainComponent();
            entitie.setSgmv_main_component_id(16);
            entitie.setDescription(mCarroceria.getText().toString());
        }
        mMainComponent.add(entitie);
    }

    public void initSwitchers(){

        mSwitchMotor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLMotor.setVisibility(View.VISIBLE);
                    myList.add(1);
                }else{
                    mRLMotor.setVisibility(View.GONE);
                    myList.remove(new Integer(1));
                }
            }
        });

        mSwitchCaja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLcaja.setVisibility(View.VISIBLE);
                    myList.add(2);
                }else{
                    mRLcaja.setVisibility(View.GONE);
                    myList.remove(new Integer(2));
                }
            }
        });

        mSwitchCorona.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLCorona.setVisibility(View.VISIBLE);
                    myList.add(3);
                }else{
                    mRLCorona.setVisibility(View.GONE);
                    myList.remove(new Integer(3));
                }
            }
        });

        mSwitchSistema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLSistema.setVisibility(View.VISIBLE);
                    myList.add(4);
                }else{
                    mRLSistema.setVisibility(View.GONE);
                    myList.remove(new Integer(4));
                }
            }
        });

        mSwitchLuces.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLLuces.setVisibility(View.VISIBLE);
                    myList.add(5);
                }else{
                    mRLLuces.setVisibility(View.GONE);
                    myList.remove(new Integer(5));
                }
            }
        });

        mSwitchFrenos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLFrenos.setVisibility(View.VISIBLE);
                    myList.add(6);
                }else{
                    mRLFrenos.setVisibility(View.GONE);
                    myList.remove(new Integer(6));
                }
            }
        });

        mSwitchDireccion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLDireccion.setVisibility(View.VISIBLE);
                    myList.add(7);
                }else{
                    mRLDireccion.setVisibility(View.GONE);
                    myList.remove(new Integer(7));
                }
            }
        });

        mSwitchRueda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLRueda.setVisibility(View.VISIBLE);
                    myList.add(8);
                }else{
                    mRLRueda.setVisibility(View.GONE);
                    myList.remove(new Integer(8));
                }
            }
        });

        mSwitchSuspension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLSuspension.setVisibility(View.VISIBLE);
                    myList.add(9);
                }else{
                    mRLSuspension.setVisibility(View.GONE);
                    myList.remove(new Integer(9));
                }
            }
        });

        mSwitchMuelles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLMuelles.setVisibility(View.VISIBLE);
                    myList.add(10);
                }else{
                    mRLMuelles.setVisibility(View.GONE);
                    myList.remove(new Integer(10));
                }
            }
        });

        mSwitchCabina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLCabina.setVisibility(View.VISIBLE);
                    myList.add(11);
                }else{
                    mRLCabina.setVisibility(View.GONE);
                    myList.remove(new Integer(11));
                }
            }
        });

        mSwitchRuedaCarreta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLRuedaCarreta.setVisibility(View.VISIBLE);
                    myList.add(12);
                }else{
                    mRLRuedaCarreta.setVisibility(View.GONE);
                    myList.remove(new Integer(12));
                }
            }
        });

        mSwitchFrenoCarreta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLFrenoCarreta.setVisibility(View.VISIBLE);
                    myList.add(13);
                }else{
                    mRLFrenoCarreta.setVisibility(View.GONE);
                    myList.remove(new Integer(13));
                }
            }
        });

        mSwitchMuelleCarreta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLMuelleCarreta.setVisibility(View.VISIBLE);
                    myList.add(14);
                }else{
                    mRLMuelleCarreta.setVisibility(View.GONE);
                    myList.remove(new Integer(14));
                }
            }
        });

        mSwitchSoporte.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLSoporte.setVisibility(View.VISIBLE);
                    myList.add(15);
                }else{
                    mRLSoporte.setVisibility(View.GONE);
                    myList.remove(new Integer(15));
                }
            }
        });

        mSwitchCarroceria.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mRLCarroceria.setVisibility(View.VISIBLE);
                    myList.add(16);
                }else{
                    mRLCarroceria.setVisibility(View.GONE);
                    myList.remove(new Integer(16));
                }
            }
        });

    }

}
