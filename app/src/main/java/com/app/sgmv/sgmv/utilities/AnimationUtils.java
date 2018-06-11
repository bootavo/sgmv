package com.app.sgmv.sgmv.utilities;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AnimationUtils {

    public static void shake(View target){
        YoYo.with(Techniques.Tada)
                .duration(400)
                .repeat(0)
                .playOn(target);
    }

}
