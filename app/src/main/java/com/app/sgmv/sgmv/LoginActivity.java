package com.app.sgmv.sgmv;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.LoginResponse;
import com.app.sgmv.sgmv.entities.User;
import com.app.sgmv.sgmv.services.LoginInterface;
import com.app.sgmv.sgmv.utilities.AnimationUtils;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.GlideApp;
import com.app.sgmv.sgmv.utilities.PreferencesHelper;
import com.bumptech.glide.request.RequestOptions;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity{

    @BindView(R.id.btn_login) CircularProgressButton btnLogin;
    @BindView(R.id.username) TextView txtUsuario;
    @BindView(R.id.password) TextView txtPassword;
    @BindView(R.id.usernameInpLayout) TextInputLayout textInputLayoutUsuario;
    @BindView(R.id.passwordeInpLayout) TextInputLayout textInputLayoutPassword;
    @BindView(R.id.iv_background) ImageView mBackground;

    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ctx = getApplicationContext();

        GlideApp
                .with(ctx)
                .load(R.drawable.bg_login)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(15)))
                .into(mBackground);

        if (PreferencesHelper.getMyUserPref(ctx)!= null) {
            next(MainActivity.class, true);
        }

    }

    @Nullable
    @OnClick(R.id.btn_login)
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
        }
    }

    public void login() {

        if(loginVerify()){
            btnLogin.startAnimation();

            User login = new User();
            login.setUser(txtUsuario.getText().toString());
            login.setPassword(txtPassword.getText().toString());

            LoginInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(LoginInterface.class);
            Call<LoginResponse> mCall = mInterface.login(login);
            mCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try {
                        LoginResponse responseUser = response.body();

                        if (responseUser.getStatus().getCode() == 404) {
                            AnimationUtils.shake(txtUsuario);
                            AnimationUtils.shake(txtPassword);
                            Toast.makeText(ctx, responseUser.getStatus().getDetail(), Toast.LENGTH_SHORT).show();
                            textInputLayoutUsuario.setErrorEnabled(true);
                            textInputLayoutUsuario.setError(Constants.MJS_ERROR_CREDENTIALS);
                            textInputLayoutPassword.setError(Constants.MJS_ERROR_CREDENTIALS);

                            btnLogin.revertAnimation();
                        }else{
                            PreferencesHelper.setMyUserPref(ctx, responseUser);
                            btnLogin.doneLoadingAnimation(getResources().getColor(R.color.transparent), BitmapFactory.decodeResource(getResources(),R.drawable.ic_done_white_48dp));
                            next(MainActivity.class, true);
                        }

                    } catch (Exception e) {
                        btnLogin.revertAnimation();
                        Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(Constants.ERROR, e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    btnLogin.revertAnimation();
                    Toast.makeText(ctx, Constants.MJS_ERROR_CONEXION_SERVICIO,
                            Toast.LENGTH_SHORT).show();
                    Log.e(Constants.ERROR, Constants.MJS_ERROR_CONEXION_SERVICIO);
                }
            });

        }else{
            btnLogin.revertAnimation();
            Toast.makeText(ctx, Constants.MSJ_ERROR_EMPTY_FIELDS, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean loginVerify(){
        boolean state = false;
        textInputLayoutUsuario.setError("");
        textInputLayoutPassword.setError("");

        if (txtPassword.getText().toString().isEmpty() ||
                txtUsuario.getText().toString().isEmpty()) {

            textInputLayoutUsuario.setError(Constants.MJS_ERROR_INVALID_INPUT_USER);
            textInputLayoutPassword.setError(Constants.MJS_ERROR_INVALID_INPUT_PASSWORD);
        }else{
            state = true;
        }
        btnLogin.revertAnimation();
        return state;
    }

}
