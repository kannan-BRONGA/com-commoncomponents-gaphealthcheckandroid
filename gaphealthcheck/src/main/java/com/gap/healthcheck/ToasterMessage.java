package com.gap.healthcheck;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by Kwaw Annan on 10-05-2021.
 */
public class ToasterMessage {

    //For testing Library is working with sample App
    public static void s(Context c, String message){
        Toast.makeText(c,message, Toast.LENGTH_SHORT).show();

    }
}
