package link.mgiannone.musixmatchapp.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.Config;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

@Dao
public interface ChartDao {
	@Query("SELECT * FROM " + Config.MUSIX_MATCH_TRACKS_TABLE_NAME)
	Observable<List<Track>> getAllTracks();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Track track);

	@Query("DELETE FROM " + Config.MUSIX_MATCH_TRACKS_TABLE_NAME)
	void deleteAll();
}
