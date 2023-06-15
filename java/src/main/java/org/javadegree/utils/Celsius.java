package org.javadegree.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Cette classe permet la gestion des Celsius
 */
public class Celsius {

    private final int cel;

    /**
     * Constructeur
     * @param val la valeur en Celsius
     */
    public Celsius(String val){
        this.cel= Integer.parseInt(val);
    }

    /**
     * Méthode qui permet de convertir les Celsius en Fahrenheit et exposer les valeurs a Prometheus
     * @return La température en Fahrenheit
     * @throws IOException pour l'ecriture dans un ficher
     */
    public String toFahrenheit() throws IOException {
        float far = ((float) (this.cel * 9) / 5) + 32;
        DecimalFormat df = new DecimalFormat("0.0");

        String cel_str = "temperature_celsius " + this.cel;
        String far_str = "temperature_fahrenheit "+df.format(far);

        File file = new File("/tmp/temperatures.prom");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(cel_str);
        bw.write("\n");
        bw.write(far_str);
        bw.write("\n");
        bw.close();


        return df.format(far);
    }
}
