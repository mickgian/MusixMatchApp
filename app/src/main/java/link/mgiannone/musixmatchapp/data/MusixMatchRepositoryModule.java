package link.mgiannone.musixmatchapp.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import link.mgiannone.musixmatchapp.data.repository.AlbumDataSource;
import link.mgiannone.musixmatchapp.data.repository.ChartDataSource;
import link.mgiannone.musixmatchapp.data.repository.Local;
import link.mgiannone.musixmatchapp.data.repository.LyricsDataSource;
import link.mgiannone.musixmatchapp.data.repository.Remote;
import link.mgiannone.musixmatchapp.data.repository.local.ChartLocalDataSource;
import link.mgiannone.musixmatchapp.data.repository.remote.AlbumRemoteDataSource;
import link.mgiannone.musixmatchapp.data.repository.remote.ChartRemoteDataSource;
import link.mgiannone.musixmatchapp.data.repository.remote.LyricsRemoteDataSource;


@Module
public class MusixMatchRepositoryModule {

	@Provides
	@Local
	@Singleton
	public ChartDataSource provideLocalChartDataSource(ChartLocalDataSource chartLocalDataSource) {
		return chartLocalDataSource;
	}

	@Provides
	@Remote
	@Singleton
	public ChartDataSource provideRemoteChartDataSource(ChartRemoteDataSource chartRemoteDataSource) {
		return chartRemoteDataSource;
	}

	@Provides
	@Remote
	@Singleton
	public AlbumDataSource provideRemoteAlbumDataSource(AlbumRemoteDataSource albumRemoteDataSource) {
		return albumRemoteDataSource;
	}

	@Provides
	@Remote
	@Singleton
	public LyricsDataSource provideRemoteLyricsDataSource(LyricsRemoteDataSource lyricsRemoteDataSource) {
		return lyricsRemoteDataSource;
	}

}
