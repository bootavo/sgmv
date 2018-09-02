package com.app.sgmv.sgmv.utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.os.ConfigurationCompat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.listeners.OnDialogResult;

import java.util.Calendar;

public class DateDialogUtils {

    public static Dialog dialog;

    static DatePickerDialog.OnDateSetListener mDataSetListener;
    static DatePickerDialog dateDialog;

    public static void showDialog(final Context context, final Activity activity, boolean cancelable, final OnDialogResult onDialogResult){

        mDataSetListener = (view, year, month, day) -> {

            int mes_final = month+1;

            String month_test = ""+mes_final;
            String day_test = ""+day;

            if (mes_final<10){
                month_test = "0"+month_test;
            }

            if (day<10){
                day_test = "0"+day;
            }
            onDialogResult.onOk(year+"", month_test, day_test);
        };

        Calendar cal = Calendar.getInstance();
        int yeara = cal.get(Calendar.YEAR);
        int montha = cal.get(Calendar.MONTH);
        int daya = cal.get(Calendar.DAY_OF_MONTH);

        //Theme_AppCompat_Light_Dialog_Alert
        //Theme_AppCompat_Light_Dialog
        dateDialog = new DatePickerDialog(context, R.style.Theme_AppCompat_Light_Dialog, mDataSetListener, yeara, montha, daya);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dateDialog.show();
    }

    public static void dimissDialog(){
        if(dialog != null){
            dialog.dismiss();
        }
    }

    public static void showDatePicker(){

    }

    public static void createDialogWithoutDateField(Activity activity) {
        DatePickerDialog dpd = new DatePickerDialog(activity, R.style.Theme_AppCompat_Light_Dialog, mDataSetListener, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {

        }
        dpd.show();
    }

    /*
    public static void showMonthPicker(Activity activity, final OnDialogResult onDialogResult){
        Locale spanish = new Locale("es", "ES");
        Locale current = ConfigurationCompat.getLocales(activity.getResources().getConfiguration()).get(0);

        RackMonthPicker rackMonthPicker = new RackMonthPicker(activity);
        rackMonthPicker.setLocale(current)
                .setColorTheme(R.color.primaryDarkColor)
                .setPositiveText(activity.getResources().getString(R.string.btn_accept))
                .setNegativeText(activity.getResources().getString(R.string.btn_cancel))
                .setPositiveButton((month, startDate, endDate, year, monthLabel) -> {
                    String month_text = month+"";
                    if (month<10){
                        month_text = "0"+month;
                    }
                    onDialogResult.onOk(year+"", month_text);
                })
                .setNegativeButton(dialog -> {
                    onDialogResult.onCancel();
                    rackMonthPicker.dismiss();
                }).show();
    }*/

}
