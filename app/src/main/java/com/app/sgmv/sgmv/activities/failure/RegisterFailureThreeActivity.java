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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.entities.failure.WheelComponent;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gtufinof on 6/13/18.
 */

public class RegisterFailureThreeActivity extends BaseActivity{

    @BindView(R.id.v_tab) View mView;

    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.iv_back) ImageView btnBack;

    @BindView(R.id.sw_revisar_ajuste) Switch mSwitchRevisarAjuste;
    @BindView(R.id.sw_corte_averias) Switch mSwitchCorteAverias;
    @BindView(R.id.sw_revisar_presion) Switch mSwitchRevisarPresion;
    @BindView(R.id.sw_cocada_baja) Switch mSwitchCocadaBaja;
    @BindView(R.id.sw_llanta_cambiada) Switch mSwitchLlantaCambiada;
    @BindView(R.id.sw_llanta_reportada) Switch mSwitchLlantaReportada;
    @BindView(R.id.sw_otros_componentes) Switch mSwitchOtrosComponentes;

    List<Integer> myList = new ArrayList<Integer>();
    List<WheelComponent> mWheelComponent = new ArrayList<>();

    private String TAG = RegisterFailureThreeActivity.class.getSimpleName();
    public static Activity activity;
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_three);
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
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*3,4);
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
                    Constants.LIST_WHEEL_COMPONENT = new ArrayList<>();
                    Constants.LIST_WHEEL_COMPONENT = mWheelComponent;
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
        if(mWheelComponent.size() >= 1){
            Log.d(TAG, "Wheel component con data");
            return true;
        }else {
            Log.d(TAG, "Wheel component sin data");
            return true;
        }
    }

    public void nextActivity(){
        Toast.makeText(ctx, "Siguiente", Toast.LENGTH_SHORT).show();
        next(RegisterFailureFourActivity.class, false);
    }

    public void initSwitchers(){

        mSwitchRevisarAjuste.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myList.add(1);
                }else{
                    myList.remove(new Integer(1));
                }
            }
        });


        mSwitchCorteAverias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myList.add(2);
                }else{
                    myList.remove(new Integer(2));
                }
            }
        });

        mSwitchRevisarPresion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myList.add(3);
                }else{
                    myList.remove(new Integer(3));
                }
            }
        });

        mSwitchCocadaBaja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myList.add(4);
                }else{
                    myList.remove(new Integer(4));
                }
            }
        });

        mSwitchLlantaCambiada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myList.add(5);
                }else{
                    myList.remove(new Integer(5));
                }
            }
        });

        mSwitchLlantaReportada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myList.add(6);
                }else{
                    myList.remove(new Integer(6));
                }
            }
        });

    }

    public void createJsonObject(){
        for(int i: myList){
            getValuesFromNumberId(i);
            Log.d(TAG, "Wheel component: "+i);
        }

        mWheelComponent.toString();

    }

    public void getValuesFromNumberId(int id){

        WheelComponent entitie = null;

        if(id == 1){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(1);
        }else if(id == 2){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(2);
        }else if(id == 3){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(3);
        }else if(id == 4){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(4);
        }else if(id == 5){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(5);
        }else if(id == 6){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(6);
        }else if(id == 7){
            entitie = new WheelComponent();
            entitie.setSgmv_wheel_component_id(7);
        }

        mWheelComponent.add(entitie);
    }

}
