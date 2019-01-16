package link.mgiannone.musixmatchapp.data.repository;

import java.util.List;
import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

public interface ChartDataSource {

	Observable<ChartResponse> loadChartResponse(String chartName, int page, int pageSize, String country, int hasLyrics, String apiKey);

	Observable<List<TrackList>> getTracks();

	void addTrack(TrackList trackList);

	void clearTracksData();

}
