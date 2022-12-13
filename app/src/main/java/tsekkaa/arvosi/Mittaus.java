package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A class that defines a measurement object with blood values and a timestamp
 * The @Entity annotation turns the class into a table in the Room database where each attribute represents a column
 *
 * @author  Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 */

@Entity
public class Mittaus {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "ylapaine")
    private Double ylapaine;

    @ColumnInfo(name = "alapaine")
    private Double alapaine;

    @ColumnInfo(name = "syke")
    private Double syke;

    @ColumnInfo(name = "verensokeri")
    private Double verensokeri;

    @ColumnInfo(name = "happipitoisuus")
    private Double happipitoisuus;

    @ColumnInfo(name = "paiva")
    private int paiva;

    @ColumnInfo(name = "kuukausi")
    private int kuukausi;

    @ColumnInfo(name = "vuosi")
    private int vuosi;

    @ColumnInfo(name = "tunnit")
    private int tunnit;

    @ColumnInfo(name = "minuutit")
    private int minuutit;

    @ColumnInfo(name = "aika")
    private Long aika;

    /**
     * Class constructor
     * @param ylapaine Systolic blood pressure
     * @param alapaine Diastolic blood pressure
     * @param syke Heartbeats per minute
     * @param verensokeri Blood sugar level
     * @param happipitoisuus Blood oxygen level
     * @param paiva The day the measurement was taken
     * @param kuukausi Month
     * @param vuosi Year
     * @param tunnit Hour
     * @param minuutit Minutes
     * @param aika
     */
    public Mittaus(Double ylapaine, Double alapaine, Double syke, Double verensokeri, Double happipitoisuus, int paiva, int kuukausi,
                   int vuosi, int tunnit, int minuutit, Long aika) {
        this.ylapaine = ylapaine;
        this.alapaine = alapaine;
        this.syke = syke;
        this.verensokeri = verensokeri;
        this.happipitoisuus = happipitoisuus;
        this.paiva = paiva;
        this.kuukausi = kuukausi;
        this.vuosi = vuosi;
        this.tunnit = tunnit;
        this.minuutit = minuutit;
        this.aika = aika;
    }

    public Long getAika(){
        return aika;
    }

    /**
     *
     * @param paiva The day the measurement was taken
     * @param kuukausi Month
     * @param vuosi Year
     * @param tunnit Hour
     * @param minuutit Minutes
     * @return the date of the measurement
     */
    public String getAjankohta(){

        String vuosi = String.valueOf(this.vuosi);
        String kuukausi = String.valueOf(this.kuukausi);
        String paiva = String.valueOf(this.paiva);
        String tunnit = String.valueOf(this.tunnit);
        String minuutit = String.valueOf(this.minuutit);
        return  String.valueOf(paiva + "." + kuukausi);

    }

    public int getId() {
        return id;
    }

    public Double getYlapaine() {
        return ylapaine;
    }

    public Double getAlapaine() {
        return alapaine;
    }

    public Double getSyke() {
        return syke;
    }

    public Double getVerensokeri() {
        return verensokeri;
    }

    public Double getHappipitoisuus() {
        return happipitoisuus;
    }

    public int getPaiva() {
        return paiva;
    }


    public int getKuukausi() {
        return kuukausi;
    }

    public int getVuosi() {
        return vuosi;
    }

    public int getTunnit() {
        return tunnit;
    }

    public int getMinuutit() {
        return minuutit;
    }
    public Date getDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dateInString = "" + paiva + "-" + kuukausi + "-" + vuosi + " " + tunnit + ":" + minuutit;
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}