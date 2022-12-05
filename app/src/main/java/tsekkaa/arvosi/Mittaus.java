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

    public Mittaus(Double ylapaine, Double alapaine, Double syke, Double verensokeri, Double happipitoisuus, int paiva, int kuukausi,
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

    public double getPvmKK(){
        return Double.valueOf(paiva + "." + kuukausi);
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