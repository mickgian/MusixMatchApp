package link.mgiannone.musixmatchapp.data.repository;

import java.util.List;
import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

public interface ChartDataSource {

	Observable<ChartResponse> loadChartResponse();

	Observable<List<Track>> getTracks();

	void addTrack(Track track);

	void clearTracksData();

}
