package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    @ColumnInfo(name = "pvm")
    private String pvm;

    @ColumnInfo(name = "aika")
    private String aika;

    public Mittaus(double ylapaine, double alapaine, double syke, double verensokeri, double happipitoisuus, String pvm, String aika) {
        this.ylapaine = ylapaine;
        this.alapaine = alapaine;
        this.syke = syke;
        this.verensokeri = verensokeri;
        this.happipitoisuus = happipitoisuus;
        this.pvm = pvm;
        this.aika = aika;
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

    public String getPvm() {
        return pvm;
    }

    public String getAika() {
        return aika;
    }
}