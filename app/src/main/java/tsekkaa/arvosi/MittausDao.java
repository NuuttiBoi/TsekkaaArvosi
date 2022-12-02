package tsekkaa.arvosi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Data access object

@Dao
public interface MittausDao {

    @Insert
    void lisaaMittaus(Mittaus mittaus);

    @Query("SELECT * FROM mittaus")
    LiveData<List<Mittaus>> haeMittaukset();
}