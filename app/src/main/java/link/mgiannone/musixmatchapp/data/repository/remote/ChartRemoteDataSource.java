package link.mgiannone.musixmatchapp.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.api.ChartService;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.repository.ChartDataSource;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

public class ChartRemoteDataSource implements ChartDataSource {

	private ChartService chartService;

	@Inject
	public ChartRemoteDataSource(ChartService chartService) {
		this.chartService = chartService;
	}

	@Override
	public Observable<ChartResponse> loadChartResponse(String chartName, int page, int pageSize, String country, int hasLyrics, String apiKey) {
		return chartService.getIrishChart(chartName, page, pageSize, country, hasLyrics, apiKey);
	}

	@Override
	public Observable<List<TrackList>> getTracks() {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	@Override
	public void addTrack(TrackList trackList) {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	@Override
	public void clearTracksData() {
		throw new UnsupportedOperationException("Unsupported operation");
	}
}
