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
	public Observable<ChartResponse> loadChartResponse() {
		return chartService.getIrishChart();
	}

	@Override
	public Observable<List<Track>> getTracks() {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	@Override
	public void addTrack(Track track) {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	@Override
	public void clearTracksData() {
		throw new UnsupportedOperationException("Unsupported operation");
	}
}
