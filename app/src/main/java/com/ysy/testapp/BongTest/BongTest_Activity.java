package com.ysy.testapp.BongTest;

import android.app.Activity;
import android.os.Bundle;

import com.exp.ysy.Tools.NativeBong;

/**
 * User: ysy
 * Date: 2015/8/10
 */
public class BongTest_Activity extends Activity {


    NativeBong nativeBong;


    static {
        System.loadLibrary("NativeBong");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        double in1[] = new double[100];
        double in2[] = new double[100];

        double out1[] = new double[100];
        double out2[] = new double[100];
        nativeBong = new NativeBong();
        nativeBong.BongInit(0.00109, 0.1088, 50.0, 0.5);

        for (int i = 0; i < 100; i++) {
            in1[i] = i / 100.0;
            in2[i] = i / 100.0;
        }

        for (int i = 0; i < 100; i++) {
            out1[i] = nativeBong.DoBongL(in1[i]);
            out2[i] = nativeBong.DoBongR(in2[i]);

            System.out.println("out1["+i+"]="+out1[i]+" out2["+i+"]="+out2[i]);
        }
    }
}
