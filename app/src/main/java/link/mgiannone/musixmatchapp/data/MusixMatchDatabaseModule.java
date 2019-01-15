package link.mgiannone.musixmatchapp.data;

import androidx.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import link.mgiannone.musixmatchapp.data.database.MusixMatchDB;
import link.mgiannone.musixmatchapp.data.database.ChartDao;

@Module
public class MusixMatchDatabaseModule {
	private static final String DATABASE = "database_name";

	@Provides
	@Named(DATABASE)
	String provideDatabaseName() {
		return Config.MUSIX_MATCH_APP_DATABASE_NAME;
	}

	@Provides
	@Singleton
	MusixMatchDB provideMusixMatchDb(Context context, @Named(DATABASE) String databaseName) {
		return Room.databaseBuilder(context, MusixMatchDB.class, databaseName).build();
	}

	@Provides
	@Singleton
	ChartDao provideRepositoryDao(MusixMatchDB musixMatchDB) {
		return musixMatchDB.chartDao();
	}
}
