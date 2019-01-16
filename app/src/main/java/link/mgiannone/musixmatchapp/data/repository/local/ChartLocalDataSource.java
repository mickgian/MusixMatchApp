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
	public Observable<ChartResponse> loadChartResponse(String chartName, int page, int pageSize, String country, int hasLyrics, String apiKey) {
		return null;
	}

	@Override
	public Observable<List<TrackList>> getTracks() {
		return chartDao.getAllTracks();
	}

	@Override
	public void addTrack(TrackList trackList) {
		chartDao.insert(trackList);
	}

	@Override
	public void clearTracksData() {
		chartDao.deleteAll();
	}
}
