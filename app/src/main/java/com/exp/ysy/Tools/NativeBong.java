package com.exp.ysy.Tools;

/**
 * User: ysy
 * Date: 2015/8/10
 */
public class NativeBong {

    public NativeBong() {

    }

    public native void BongInit(double at2,double rt2,double b2,double thresh_amp2);

    public native void BongRelase();

    public native double DoBongL(double data);

    public native double DoBongR(double data);

    public native void SetValueAt(double at2);

    public native void SetValueRt(double rt2);

    public native void SetValueB(double b2);

    public native void SetValueTH(double thresh_amp2);
}
