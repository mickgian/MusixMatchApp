package link.mgiannone.musixmatchapp.data.repository;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.model.AlbumResponse;

public interface AlbumDataSource {

	Observable<AlbumResponse> loadAlbumResponse(int albumId, String apiKey);

}
