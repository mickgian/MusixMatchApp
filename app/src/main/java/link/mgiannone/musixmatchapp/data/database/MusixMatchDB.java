package link.mgiannone.musixmatchapp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

@Database(entities = TrackList.class, version = 1)
public abstract class MusixMatchDB extends RoomDatabase {

	public abstract ChartDao chartDao();

}