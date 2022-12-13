package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

/**
 * A reminder object with a timestamp
 *
 * @author  Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 */

@Entity
public class Muistutus {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

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

    @ColumnInfo(name = "mitattavat")
    private String mitattavat;

    @ColumnInfo(name = "lisatiedot")
    private String lisatiedot;

    @ColumnInfo(name = "requestCode")
    private int requestCode;

    public Muistutus(int paiva, int kuukausi, int vuosi, int tunnit, int minuutit, String mitattavat, String lisatiedot, int requestCode) {
        this.paiva = paiva;
        this.kuukausi = kuukausi;
        this.vuosi = vuosi;
        this.tunnit = tunnit;
        this.minuutit = minuutit;
        this.mitattavat = mitattavat;
        this.lisatiedot = lisatiedot;
        this.requestCode = requestCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaiva() {
        return paiva;
    }

    public void setPaiva(int paiva) {
        this.paiva = paiva;
    }

    public int getKuukausi() {
        return kuukausi;
    }

    public void setKuukausi(int kuukausi) {
        this.kuukausi = kuukausi;
    }

    public int getVuosi() {
        return vuosi;
    }

    public void setVuosi(int vuosi) {
        this.vuosi = vuosi;
    }

    public int getTunnit() {
        return tunnit;
    }

    public void setTunnit(int tunnit) {
        this.tunnit = tunnit;
    }

    public int getMinuutit() {
        return minuutit;
    }

    public void setMinuutit(int minuutit) {
        this.minuutit = minuutit;
    }

    public String getMitattavat() {
        return mitattavat;
    }

    public void setMitattavat(String mitattavat) {
        this.mitattavat = mitattavat;
    }

    public String getLisatiedot() {
        return lisatiedot;
    }

    public int getRequestCode() { return requestCode; }

    public void setLisatiedot(String lisatiedot) {
        this.lisatiedot = lisatiedot;
    }

    /**
     * A method to format the date
     * @return The date in string format with leading zeros if necessary
     */
    public String getPvm() {
        String pvm = "" + this.paiva + "/";

        if (this.kuukausi < 10) {
            pvm += "0";
        }

        pvm += this.kuukausi + " " + this.tunnit + ":";

        if (this.minuutit < 10) {
            pvm += "0";
        }
        return pvm + this.minuutit;
    }

    /**
     * Returns the date in milliseconds since the Epoch
     * @return The date in milliseconds
     */
    public long AikaMilliSek() {
        Calendar cal = Calendar.getInstance();
        cal.set(this.vuosi, this.kuukausi-1, this.paiva, this.tunnit, this.minuutit, 0);
        return cal.getTimeInMillis();
    }
}
