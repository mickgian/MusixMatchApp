package link.mgiannone.musixmatchapp.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import link.mgiannone.musixmatchapp.data.repository.ChartDataSource;
import link.mgiannone.musixmatchapp.data.repository.Local;
import link.mgiannone.musixmatchapp.data.repository.Remote;
import link.mgiannone.musixmatchapp.data.repository.local.ChartLocalDataSource;
import link.mgiannone.musixmatchapp.data.repository.remote.ChartRemoteDataSource;


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

}
