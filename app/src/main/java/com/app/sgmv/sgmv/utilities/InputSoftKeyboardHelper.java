package com.app.sgmv.sgmv.utilities;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by gtufinof on 6/20/18.
 */

public class InputSoftKeyboardHelper {

    public static void hideInputSoft(Activity activity){
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

}
