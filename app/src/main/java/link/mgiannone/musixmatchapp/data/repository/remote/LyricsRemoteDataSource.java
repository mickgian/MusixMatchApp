package link.mgiannone.musixmatchapp.data.repository.remote;

import javax.inject.Inject;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.api.LyricsService;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse;
import link.mgiannone.musixmatchapp.data.repository.LyricsDataSource;

public class LyricsRemoteDataSource implements LyricsDataSource {

	private LyricsService lyricsService;

	@Inject LyricsRemoteDataSource(LyricsService lyricsService) {
		this.lyricsService = lyricsService;
	}

	@Override
	public Observable<LyricsResponse> loadLyricsResponse(int trackId, String apiKey) {
		return lyricsService.getLyrics(trackId, apiKey);
	}
}
