package link.mgiannone.musixmatchapp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import link.mgiannone.musixmatchapp.data.model.ChartResponse.Track;

@Database(entities = Track.class, version = 1)
public abstract class MusixMatchDB extends RoomDatabase {

	public abstract ChartDao chartDao();

}