package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

/**
 * A class that defines a reminder object with text details and a timestamp
 * The @Entity annotation turns the class into a table in the Room database where each attribute represents a column
 *
 * @author  Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 */

@Entity
public class Muistutus {

    /**
     * The primary key column, a running number starting from 1
     */
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

    /**
     * Class constructor
     * @param paiva The day the reminder is set to
     * @param kuukausi The month the reminder is set to
     * @param vuosi The year the reminder is set to
     * @param tunnit The hour the reminder is set to
     * @param minuutit The minutes the reminder is set to
     * @param mitattavat A string listing which blood values the user has selected to be reminded of
     * @param lisatiedot A custom note the user can add
     * @param requestCode A unique identifier needed to manage and delete reminders
     */
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

    /**
     * Returns the id
     * @return The id of the
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new id
     * @param id The new id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the day the reminder is set to
     * @return The set day
     */
    public int getPaiva() {
        return paiva;
    }

    /**
     * Sets a new day
     * @param paiva The new day to be set
     */
    public void setPaiva(int paiva) {
        this.paiva = paiva;
    }

    /**
     * Returns the month the reminder is set to
     * @return The set month
     */
    public int getKuukausi() {
        return kuukausi;
    }

    /**
     * Sets a new month
     * @param kuukausi The new month to be set
     */
    public void setKuukausi(int kuukausi) {
        this.kuukausi = kuukausi;
    }

    /**
     * Returns the year the reminder is set to
     * @return The set year
     */
    public int getVuosi() {
        return vuosi;
    }

    /**
     * Sets a new year
     * @param vuosi The new day to be set
     */
    public void setVuosi(int vuosi) {
        this.vuosi = vuosi;
    }

    /**
     * Returns the hour the reminder is set to
     * @return The set hour
     */
    public int getTunnit() {
        return tunnit;
    }

    /**
     * Sets a new hour
     * @param tunnit The new hour to be set
     */
    public void setTunnit(int tunnit) {
        this.tunnit = tunnit;
    }

    /**
     * Returns the minutes the reminder is set to
     * @return The set minutes
     */
    public int getMinuutit() {
        return minuutit;
    }

    /**
     * Sets a new amount of minutes
     * @param minuutit The new minutes to be set
     */
    public void setMinuutit(int minuutit) {
        this.minuutit = minuutit;
    }

    /**
     * Returns the list of values the user has selected to be reminded of
     * @return A string listing selected blood values
     */
    public String getMitattavat() {
        return mitattavat;
    }

    /**
     * Returns the custom note the user added to the reminder
     * @return The note received from user input
     */
    public String getLisatiedot() {
        return lisatiedot;
    }

    /**
     * Returns the request code of the reminder in question
     * @return The request code of the reminder
     */
    public int getRequestCode() { return requestCode; }

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
