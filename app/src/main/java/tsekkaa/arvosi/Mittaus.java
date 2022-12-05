package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Mittaus {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "ylapaine")
    private double ylapaine;

    @ColumnInfo(name = "alapaine")
    private double alapaine;

    @ColumnInfo(name = "syke")
    private double syke;

    @ColumnInfo(name = "verensokeri")
    private double verensokeri;

    @ColumnInfo(name = "happipitoisuus")
    private double happipitoisuus;

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



    public Mittaus(double ylapaine, double alapaine, double syke, double verensokeri, double happipitoisuus, int paiva, int kuukausi,
                   int vuosi, int tunnit, int minuutit) {
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
    }

    public int getId() {
        return id;
    }

    public double getYlapaine() {
        return ylapaine;
    }

    public double getAlapaine() {
        return alapaine;
    }

    public double getSyke() {
        return syke;
    }

    public double getVerensokeri() {
        return verensokeri;
    }

    public double getHappipitoisuus() {
        return happipitoisuus;
    }

    public double getPvmKK(){

        // SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        // Date date = new Date();
        // String dateformatted = dateFormat.format(date);
        // return Double.valueOf(dateformatted);

        return Double.parseDouble(tunnit + "." + minuutit);
    }

    public int getPaiva() {
        return paiva;
    }

    public String getPvmKk(){
        SimpleDateFormat xd = new SimpleDateFormat("HH.MM");
        String pvm = xd.format(paiva + kuukausi);
        return pvm;
    }

    public Date getPvDate(){
        return new Date();
    }


    public Date getTime(){
        new Date().getTime();
        return getTime();
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