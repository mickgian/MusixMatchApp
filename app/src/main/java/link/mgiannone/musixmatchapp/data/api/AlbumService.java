package link.mgiannone.musixmatchapp.data.api;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.AlbumResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface AlbumService {

	@GET("album.get")
	@Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
	Observable<AlbumResponse> getAlbum(
			@Query("album_id") int albumId,
			@Query("apikey") String apiKey);
}
