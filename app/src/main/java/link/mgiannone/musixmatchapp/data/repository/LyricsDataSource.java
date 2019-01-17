package link.mgiannone.musixmatchapp.data.repository;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse;

public interface LyricsDataSource {

	Observable<LyricsResponse> loadLyricsResponse(int trackId, String apiKey);
}
