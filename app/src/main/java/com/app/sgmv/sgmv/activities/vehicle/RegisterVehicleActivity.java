package com.app.sgmv.sgmv.activities.vehicle;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.vehicle.Vehicle;
import com.app.sgmv.sgmv.entities.vehicle.VehicleResponse;
import com.app.sgmv.sgmv.listeners.OnDialogResult;
import com.app.sgmv.sgmv.services.VehicleInterface;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.DateDialogUtils;
import com.app.sgmv.sgmv.utilities.ImagePicker;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gtufinof on 6/19/18.
 */

public class RegisterVehicleActivity extends BaseActivity {

    @BindView(R.id.et_placa) EditText mPlaca;
    @BindView(R.id.et_brand) EditText mBrand;
    @BindView(R.id.et_model) EditText mModel;
    @BindView(R.id.et_class) EditText mClass;
    @BindView(R.id.et_constancy) EditText mConstancy;
    @BindView(R.id.et_category) EditText mCategory;
    @BindView(R.id.et_axis_number) EditText mAxisNumber;
    @BindView(R.id.et_chassis_series) EditText mChassisSeries;
    @BindView(R.id.et_year_of_production) EditText mYearOfProducction;
    @BindView(R.id.et_useful_load) EditText mUsefulLoad;
    @BindView(R.id.et_weight_dry) EditText mWieghtDry;
    @BindView(R.id.et_km_total) EditText mKmTotal;
    @BindView(R.id.civ_photo) CircleImageView mPhoto;

    @BindView(R.id.fab_year_of_producction) FloatingActionButton btnYearOfProducction;
    @BindView(R.id.btn_register) CircularProgressButton btnRegisterVehicle;

    private static final int PICK_IMAGE_ID = 100;

    private String TAG = RegisterVehicleActivity.class.getSimpleName();
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);
        initButterKinife();
    }

    @OnClick({R.id.fab_year_of_producction, R.id.btn_register, R.id.civ_photo})
    public void onCllick(View view) {
        switch (view.getId()) {
            case R.id.civ_photo:
                permissions();
                break;
            case R.id.fab_year_of_producction:
                showDialog();
                break;
            case R.id.btn_register:
                callServiceRegister();
                break;
        }
    }

    public void permissions(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
            return;
        } else {
            openGallery();
        }
    }

    public void openGallery(){
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission accepted.
                    openGallery();
                } else {
                    //permission denied.
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE_ID:
                Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                //if(bitmap != null){
                mPhoto.setImageBitmap(bitmap);
                //uploadFile(data.getData());
                //}
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public String getPlaca(){
        if (mPlaca.getText().toString() == null || mPlaca.getText().toString().equals("")){
            return "";
        }else{
            return mPlaca.getText().toString();
        }
    }

    public String getModel(){
        if (mModel.getText().toString() == null || mModel.getText().toString().equals("")){
            return "";
        }else{
            return mModel.getText().toString();
        }
    }

    public String getBrand(){
        if (mBrand.getText().toString() == null || mBrand.getText().toString().equals("")){
            return "";
        }else{
            return mBrand.getText().toString();
        }
    }

    public String getClasses(){
        if (mClass.getText().toString() == null || mClass.getText().toString().equals("")){
            return "";
        }else{
            return mClass.getText().toString();
        }
    }

    public String getConstancy(){
        if (mConstancy.getText().toString() == null || mConstancy.getText().toString().equals("")){
            return "";
        }else{
            return mConstancy.getText().toString();
        }
    }

    public String getCategory(){
        if (mCategory.getText().toString() == null || mCategory.getText().toString().equals("")){
            return "";
        }else{
            return mCategory.getText().toString();
        }
    }

    public String getAxisNumber(){
        if (mAxisNumber.getText().toString() == null || mAxisNumber.getText().toString().equals("")){
            return "";
        }else{
            return mAxisNumber.getText().toString();
        }
    }

    public String getChassisSeries(){
        if (mChassisSeries.getText().toString() == null || mChassisSeries.getText().toString().equals("")){
            return "";
        }else{
            return mChassisSeries.getText().toString();
        }
    }

    public String getYearOfProducction(){
        if (mYearOfProducction.getText().toString() == null || mYearOfProducction.getText().toString().equals("")){
            return "";
        }else{
            return mYearOfProducction.getText().toString();
        }
    }

    public String getUsefulLoad(){
        if (mUsefulLoad.getText().toString() == null || mUsefulLoad.getText().toString().equals("")){
            return "";
        }else{
            return mUsefulLoad.getText().toString();
        }
    }

    public String getWeightDry(){
        if (mWieghtDry.getText().toString() == null || mWieghtDry.getText().toString().equals("")){
            return "";
        }else{
            return mWieghtDry.getText().toString();
        }
    }

    public String getKmTotal(){
        if (mKmTotal.getText().toString() == null || mKmTotal.getText().toString().equals("")){
            return "";
        }else{
            return mKmTotal.getText().toString();
        }
    }

    public boolean verifiyRegister(){
        if(getPlaca().equals("")){
            mPlaca.setError("Ingrese el número de placa");
            return false;
        }

        if(getBrand().equals("")){
            mBrand.setError("Ingrese la marca");
            return false;
        }

        if(getModel().equals("")){
            mModel.setError("Ingrese el modelo");
            return false;
        }

        if(getClasses().equals("")){
            mClass.setError("Ingrese la clase de vehículo");
            return false;
        }

        if(getConstancy().equals("")){
            mConstancy.setError("Ingrese la constancia");
            return false;
        }

        if(getCategory().equals("")){
            mCategory.setError("Ingrese la categoría");
            return false;
        }

        if(getAxisNumber().equals("")){
            mAxisNumber.setError("Ingrese el número de ejes");
            return false;
        }

        if(getChassisSeries().equals("")){
            mChassisSeries.setError("Ingrese el número de chasis");
            return false;
        }

        if(getYearOfProducction().equals("")){
            mConstancy.setError("Ingrese el año de producción");
            return false;
        }

        if(getUsefulLoad().equals("")){
            mConstancy.setError("Ingrese la carga útil");
            return false;
        }

        if(getWeightDry().equals("")){
            mConstancy.setError("Ingrese el peso seco");
            return false;
        }

        return true;
    }

    public Vehicle buildVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setPlaca(getPlaca());
        vehicle.setBrand(getBrand());
        vehicle.setModel(getModel());
        vehicle.setClasses(getModel());
        vehicle.setConstancy(getConstancy());
        vehicle.setCategory(getCategory());
        vehicle.setAxis_number(getAxisNumber());
        vehicle.setChassis_series(getChassisSeries());
        vehicle.setYear_production(getYearOfProducction());
        vehicle.setUseful_load(getUsefulLoad());
        vehicle.setKm_total(Float.parseFloat(getKmTotal()));
        return vehicle;
    }

    public void callServiceRegister(){
        if (verifiyRegister()){
            VehicleInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(VehicleInterface.class);
            Call<VehicleResponse> mCall = mInterface.registerVehicle(buildVehicle());
            mCall.enqueue(new Callback<VehicleResponse>() {
                @Override
                public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                    if(response.body().getStatus().getCode() == 404){
                        btnRegisterVehicle.revertAnimation();
                        Toast.makeText(ctx, response.body().getStatus().getDetail(), Toast.LENGTH_SHORT).show();
                    }else{
                        btnRegisterVehicle.revertAnimation();
                        Toast.makeText(ctx, "Registro satisfactorio", Toast.LENGTH_SHORT).show();
                        nextActivity();
                    }
                }

                @Override
                public void onFailure(Call<VehicleResponse> call, Throwable t) {
                    btnRegisterVehicle.revertAnimation();
                    Toast.makeText(ctx, Constants.MJS_ERROR_CONEXION_SERVICIO, Toast.LENGTH_SHORT).show();
                    Log.e(Constants.ERROR, Constants.MJS_ERROR_CONEXION_SERVICIO);
                }
            });
        }
    }

    public void showDialog(){
        DateDialogUtils.showDialog(this, this, true, new OnDialogResult() {
            @Override
            public void onOk(String year, String month, String day) {
                Log.d("gus", "id_year: "+year+", id_month: "+month+", day: "+day);
                mYearOfProducction.setText(year+"-"+month+"-"+day);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void nextActivity(){
        finish();
    }

}
