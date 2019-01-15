package link.mgiannone.musixmatchapp.data.api;

import java.util.List;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChartService {

	//get the top 100 songs in Ireland, filtering by songs with an available lyrics
	@GET("chart.tracks.get?chart_name=top&page=1&page_size=100&country=ie&f_has_lyrics=1")
	Observable<ChartResponse> getIrishChart();

}
