package com.app.sgmv.sgmv.activities.failure;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.util.IOUtils;
import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.apis.ApiRetrofitClient;
import com.app.sgmv.sgmv.entities.failure.FailureRequest;
import com.app.sgmv.sgmv.entities.failure.FailureResponse;
import com.app.sgmv.sgmv.services.FailureReportInterface;
import com.app.sgmv.sgmv.utilities.BaseActivity;
import com.app.sgmv.sgmv.utilities.Constants;
import com.app.sgmv.sgmv.utilities.ImagePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gtufinof on 6/13/18.
 */

public class RegisterFailureFourActivity extends BaseActivity{

    @BindView(R.id.v_tab) View mView;
    @BindView(R.id.btn_register_failure) CircularProgressButton btnRegisterFailure;
    @BindView(R.id.iv_back) ImageView btnBack;
    @BindView(R.id.et_observaciones) EditText mObservations;

    @BindView(R.id.iv_one) ImageView mPhotoOne;
    @BindView(R.id.iv_two) ImageView mPhotoTwo;
    @BindView(R.id.iv_three) ImageView mPhotoThree;

    private static final int PICK_IMAGE_ID = 100;
    private AmazonS3Client s3Client;
    private BasicAWSCredentials credentials;

    private String TAG = RegisterFailureFourActivity.class.getSimpleName();
    private Context ctx = this;
    private int photoSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_four);
        initButterKinife();

        AWSMobileClient.getInstance().initialize(this).execute();
        credentials = new BasicAWSCredentials(Constants.AWS_KEY, Constants.AWS_SECRET);
        s3Client = new AmazonS3Client(credentials);

        setViewStep();
    }

    public void setViewStep(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/4;
        int height = size.y;
        FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(width*4,4);
        mView.setLayoutParams(parms);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.btn_register_failure, R.id.iv_back, R.id.iv_one, R.id.iv_two, R.id.iv_three})
    public void onCllick(View view) {
        switch (view.getId()){
            case R.id.btn_register_failure:
                callService();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_one:
                photoSelected = 1;
                permissions();
                break;
            case R.id.iv_two:
                photoSelected = 2;
                permissions();
                break;
            case R.id.iv_three:
                photoSelected = 3;
                permissions();
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
                if(bitmap != null){
                    if(photoSelected == 1){
                        mPhotoOne.setImageBitmap(bitmap);
                    }else if(photoSelected == 2){
                        mPhotoTwo.setImageBitmap(bitmap);
                    }else if(photoSelected == 3){
                        mPhotoThree.setImageBitmap(bitmap);
                    }
                    Uri fileUri = data.getData();
                    uploadFile(fileUri);
                }else{
                    bitmap.recycle();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }

    private void uploadFile(Uri fileUri) {

        if (fileUri != null) {
            //final String fileName = edtFileName.getText().toString();
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            final String fileName = ts;

            if (!validateInputFileName(fileName)) {
                return;
            }

            final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "/" + fileName);

            createFile(getApplicationContext(), fileUri, file);

            TransferUtility transferUtility =
                    TransferUtility.builder()
                            .context(getApplicationContext())
                            .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                            .s3Client(s3Client)
                            .build();

            TransferObserver uploadObserver =
                    transferUtility.upload("sgmv/" + fileName + "." + getFileExtension(fileUri), file);

            uploadObserver.setTransferListener(new TransferListener() {

                @Override
                public void onStateChanged(int id, TransferState state) {
                    if (TransferState.COMPLETED == state) {
                        Toast.makeText(getApplicationContext(), "Upload Completed!", Toast.LENGTH_SHORT).show();
                        file.delete();
                    } else if (TransferState.FAILED == state) {
                        file.delete();
                    }
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                    float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                    int percentDone = (int) percentDonef;

                    //tvFileName.setText("ID:" + id + "|bytesCurrent: " + bytesCurrent + "|bytesTotal: " + bytesTotal + "|" + percentDone + "%");
                }

                @Override
                public void onError(int id, Exception ex) {
                    ex.printStackTrace();
                }

            });
        }
    }

    private boolean validateInputFileName(String fileName) {

        if (TextUtils.isEmpty(fileName)) {
            Toast.makeText(this, "Enter file name!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void createFile(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            IOUtils.copy(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyFields(){
        return true;
    }

    public void nextActivity(){
        RgisterFailureOneActivity.activity.finish();
        RegisterFailureTwoActivity.activity.finish();
        RegisterFailureThreeActivity.activity.finish();
        finish();
    }

    public void callService(){
        if(verifyFields()){
            btnRegisterFailure.startAnimation();

            FailureRequest failureRequest = new FailureRequest();
            Constants.FAILURE_REPORT_OBJECT.setObservations(mObservations.getText().toString());
            failureRequest.setFailureReport(Constants.FAILURE_REPORT_OBJECT);
            failureRequest.setMainComponents(Constants.LIST_MAIN_COMPONENTE);
            failureRequest.setWheelComponents(Constants.LIST_WHEEL_COMPONENT);

            FailureReportInterface mInterface = ApiRetrofitClient.getRetrofitClient().create(FailureReportInterface.class);
            Call<FailureResponse> mCall = mInterface.registerFailureReport(failureRequest);
            mCall.enqueue(new Callback<FailureResponse>() {
                @Override
                public void onResponse(Call<FailureResponse> call, Response<FailureResponse> response) {
                    if(response.body().getStatus().getCode() == 404){
                        btnRegisterFailure.revertAnimation();
                        Toast.makeText(ctx, response.body().getStatus().getDetail(), Toast.LENGTH_SHORT).show();
                    }else{
                        btnRegisterFailure.revertAnimation();
                        Toast.makeText(ctx, "Registro satisfactorio", Toast.LENGTH_SHORT).show();
                        nextActivity();
                    }
                }

                @Override
                public void onFailure(Call<FailureResponse> call, Throwable t) {
                    btnRegisterFailure.revertAnimation();
                    Toast.makeText(ctx, Constants.MJS_ERROR_CONEXION_SERVICIO, Toast.LENGTH_SHORT).show();
                    Log.e(Constants.ERROR, Constants.MJS_ERROR_CONEXION_SERVICIO);
                }
            });

        }
    }

}
