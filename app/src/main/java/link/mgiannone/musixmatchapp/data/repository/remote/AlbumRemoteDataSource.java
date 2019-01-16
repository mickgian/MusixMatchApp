package link.mgiannone.musixmatchapp.data.repository.remote;

import javax.inject.Inject;

import io.reactivex.Observable;
import link.mgiannone.musixmatchapp.data.api.AlbumService;
import link.mgiannone.musixmatchapp.data.model.AlbumResponse;
import link.mgiannone.musixmatchapp.data.repository.AlbumDataSource;

public class AlbumRemoteDataSource implements AlbumDataSource {

	private AlbumService albumService;

	@Inject
	public AlbumRemoteDataSource(AlbumService albumService) {
		this.albumService = albumService;
	}

	@Override
	public Observable<AlbumResponse> loadAlbumResponse(int albumId, String apiKey) {
		return albumService.getAlbum(albumId, apiKey);
	}
}
