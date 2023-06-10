package org.javadegree.utils;

import java.text.DecimalFormat;

public class Celsius {

    private final int cel;

    public Celsius(String val){
        this.cel= Integer.parseInt(val);
    }

    public String toFahrenheit(){
        float far = ((float) (this.cel * 9) / 5) + 32;
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(far);
    }
}
