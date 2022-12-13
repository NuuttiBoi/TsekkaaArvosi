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
 */

@Entity
public class Mittaus {

    /**
     * The primary key column, a running number starting from 1
     */
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
     * @param kuukausi The month the measurement was taken
     * @param vuosi The year the measurement was taken
     * @param tunnit The hour the measurement was taken
     * @param minuutit The minutes when the measurement was taken
     * @param aika The time of the measurement as a long value
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

    /**
     * Returns the time the measurement was taken
     * @return The time as a long value
     */
    public Long getAika(){
        return aika;
    }

    /**
     * Returns the day and month of the measurement as a string
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

    /**
     * Returns the id
     * @return The id of the measurement object
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a measured systolic blood pressure value
     * @return The systolic blood pressure value
     */
    public Double getYlapaine() {
        return ylapaine;
    }


    /**
     * Returns a measured diastolic blood pressure value
     * @return The diastolic blood pressure value
     */
    public Double getAlapaine() {
        return alapaine;
    }


    /**
     * Returns a measured heart rate
     * @return The heart rate value
     */
    public Double getSyke() {
        return syke;
    }


    /**
     * Returns a measured blood sugar value
     * @return The blood sugar value
     */
    public Double getVerensokeri() {
        return verensokeri;
    }


    /**
     * Returns a measured blood oxygen value
     * @return The blood oxygen value
     */
    public Double getHappipitoisuus() {
        return happipitoisuus;
    }

    /**
     * Returns the day the measurement was taken
     * @return The day of the measurement
     */
    public int getPaiva() {
        return paiva;
    }

    /**
     * Returns the month the measurement was taken
     * @return The month of the measurement
     */
    public int getKuukausi() {
        return kuukausi;
    }

    /**
     * Returns the year the measurement was taken
     * @return The year of the measurement
     */
    public int getVuosi() {
        return vuosi;
    }

    /**
     * Returns the hours the measurement was taken
     * @return The hours of the measurement
     */
    public int getTunnit() {
        return tunnit;
    }

    /**
     * Returns the minutes when the measurement was taken
     * @return The minutes of the measurement
     */
    public int getMinuutit() {
        return minuutit;
    }

    /**
     * Converts the date into a Date object
     * @return The date of the measurement as a Date object
     */
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