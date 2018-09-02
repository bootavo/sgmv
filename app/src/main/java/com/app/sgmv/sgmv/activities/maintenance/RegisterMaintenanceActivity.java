package com.app.sgmv.sgmv.activities.maintenance;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.user.User;
import com.app.sgmv.sgmv.entities.user.UsersResponse;
import com.app.sgmv.sgmv.listeners.OnDialogResult;
import com.app.sgmv.sgmv.services.UserInterface;
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

public class RegisterMaintenanceActivity extends BaseActivity {

    @BindView(R.id.et_name) EditText mName;
    @BindView(R.id.et_last_name) EditText mLastName;
    @BindView(R.id.et_dni) EditText mDni;
    @BindView(R.id.et_birthdate) EditText mBirthDate;
    @BindView(R.id.et_email) EditText mEmail;
    @BindView(R.id.et_mobile_phone) EditText mMobilePhone;
    @BindView(R.id.rg_gender) RadioGroup mGender;
    @BindView(R.id.civ_photo) CircleImageView mPhoto;

    @BindView(R.id.fab_birthdate) FloatingActionButton btnBirthDate;
    @BindView(R.id.btn_register) CircularProgressButton btnRegisterUser;

    @BindView(R.id.sw_boss) Switch mBoss;
    @BindView(R.id.sw_management) Switch mManagement;
    @BindView(R.id.sw_driver) Switch mDriver;

    private static final int PICK_IMAGE_ID = 100;

    private String TAG = RegisterMaintenanceActivity.class.getSimpleName();
    private Context ctx = this;

    private int rol_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maintenance);
        initButterKinife();
        initSwitches();
    }

    public void initSwitches(){
        CompoundButton.OnCheckedChangeListener multiListener = (v, isChecked) -> {
            switch (v.getId()){
                case R.id.sw_boss:
                    if(isChecked) {
                        mManagement.setChecked(false);
                        mDriver.setChecked(false);
                        rol_id = 1;
                    }
                    break;
                case R.id.sw_management:
                    if(isChecked) {
                        mBoss.setChecked(false);
                        mDriver.setChecked(false);
                        rol_id = 2;
                    }
                    break;
                case R.id.sw_driver:
                    if(isChecked) {
                        mBoss.setChecked(false);
                        mManagement.setChecked(false);
                        rol_id = 3;
                    }
                    break;
            }
        };
        mBoss.setOnCheckedChangeListener(multiListener);
        mManagement.setOnCheckedChangeListener(multiListener);
        mDriver.setOnCheckedChangeListener(multiListener);
    }

    @OnClick({R.id.fab_birthdate, R.id.btn_register, R.id.civ_photo})
    public void onCllick(View view) {
        switch (view.getId()) {
            case R.id.civ_photo:
                permissions();
                break;
            case R.id.fab_birthdate:
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

    public String getName(){
        if (mName.getText().toString() == null || mName.getText().toString().equals("")){
            return "";
        }else{
            return mName.getText().toString();
        }
    }

    public String getLastName(){
        if (mLastName.getText().toString() == null || mLastName.getText().toString().equals("")){
            return "";
        }else{
            return mLastName.getText().toString();
        }
    }

    public String getDni(){
        if (mDni.getText().toString() == null || mDni.getText().toString().equals("")){
            return "";
        }else{
            return mDni.getText().toString();
        }
    }

    public String getBirthDate(){
        if (mBirthDate.getText().toString() == null || mBirthDate.getText().toString().equals("")){
            return "";
        }else{
            return mBirthDate.getText().toString();
        }
    }

    public String getEmail(){
        if (mEmail.getText().toString() == null || mEmail.getText().toString().equals("")){
            return "";
        }else{
            return mEmail.getText().toString();
        }
    }

    public String getMobilePhone(){
        if (mMobilePhone.getText().toString() == null || mMobilePhone.getText().toString().equals("")){
            return "";
        }else{
            return mMobilePhone.getText().toString();
        }
    }

    public boolean verifiyRegister(){
        if(getName().equals("")){
            mName.setError("Ingrese los nombres");
            return false;
        }

        if(getLastName().equals("")){
            mLastName.setError("Ingrese los apellidos");
            return false;
        }

        if(getDni().equals("")){
            mDni.setError("Ingrese el número de dni");
            return false;
        }

        if(getBirthDate().equals("")){
            mBirthDate.setError("Ingrese la fecha de nacimiento");
            return false;
        }

        if(getEmail().equals("")){
            mEmail.setError("Ingrese el email");
            return false;
        }

        if(getMobilePhone().equals("")){
            mMobilePhone.setError("Ingrese el número de teléfono");
            return false;
        }

        if(getGender().equals("")){
            Toast.makeText(ctx, "Seleccione el género", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rol_id == 0){
            Toast.makeText(ctx, "Seleccione un rol", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public String getGender(){
        int gender = mGender.getCheckedRadioButtonId();
        if(gender == R.id.rb_male){
            return "M";
        }else if(gender == R.id.rb_female){
            return "F";
        }else{
            return "";
        }
    }

    public User buildUser(){
        User user = new User();
        user.setNames(getName());
        user.setLast_name(getLastName());
        user.setDni(getDni());
        user.setBirthday(getBirthDate());
        user.setEmail(getEmail());
        user.setMobile_phone(getMobilePhone());
        user.setGender(getGender());
        user.setSgmv_rol_id(rol_id);
        return user;
    }

    public void callServiceRegister(){

        if (verifiyRegister()){

            UserInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(UserInterface.class);
            Call<UsersResponse> mCall = mInterface.registerUser(buildUser());
            mCall.enqueue(new Callback<UsersResponse>() {
                @Override
                public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                    if(response.body().getStatus().getCode() == 404){
                        btnRegisterUser.revertAnimation();
                        Toast.makeText(ctx, response.body().getStatus().getDetail(), Toast.LENGTH_SHORT).show();
                    }else{
                        btnRegisterUser.revertAnimation();
                        Toast.makeText(ctx, "Registro satisfactorio", Toast.LENGTH_SHORT).show();
                        nextActivity();
                    }
                }

                @Override
                public void onFailure(Call<UsersResponse> call, Throwable t) {
                    btnRegisterUser.revertAnimation();
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
                mBirthDate.setText(year+"-"+month+"-"+day);
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
