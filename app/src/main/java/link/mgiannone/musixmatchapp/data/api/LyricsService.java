package link.mgiannone.musixmatchapp.data.api;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface LyricsService {

	@GET("track.lyrics.get")
	@Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
	Observable<LyricsResponse> getLyrics(
			@Query("track_id") int trackId,
			@Query("apikey") String apiKey);
}
