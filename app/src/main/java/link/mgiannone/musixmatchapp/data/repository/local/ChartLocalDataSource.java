package link.mgiannone.musixmatchapp.data.repository.local;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.database.ChartDao;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.repository.ChartDataSource;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

public class ChartLocalDataSource implements ChartDataSource {

	private ChartDao chartDao;

	@Inject
	public ChartLocalDataSource(ChartDao chartDao) {
		this.chartDao = chartDao;
	}


	@Override
	public Observable<ChartResponse> loadChartResponse() {
		return null;
	}

	@Override
	public Observable<List<Track>> getTracks() {
		return chartDao.getAllTracks();
	}

	@Override
	public void addTrack(Track track) {
		chartDao.insert(track);
	}

	@Override
	public void clearTracksData() {
		chartDao.deleteAll();
	}
}
