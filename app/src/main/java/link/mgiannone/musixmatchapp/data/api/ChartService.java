package link.mgiannone.musixmatchapp.data.api;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ChartService {

	//get the top 100 songs for a country, filtering by songs with an available lyrics
	@GET("chart.tracks.get")
	@Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
	Observable<ChartResponse> getIrishChart(
			@Query("chart_name") String chartName,
			@Query("page") int page,
			@Query("page_size") int pageSize,
			@Query("country") String country,
			@Query("f_has_lyrics") int hasLyrics,
			@Query("apikey") String apiKey);
}
