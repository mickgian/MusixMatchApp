package link.mgiannone.musixmatchapp.data.repository;

import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.Observable;

import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.TrackList;

public class MusixMatchRepository implements ChartDataSource {

	private static final String TAG = MusixMatchRepository.class.getSimpleName();

	private ChartDataSource remoteChartDataSource;
	private ChartDataSource localChartDataSource;
	private AlbumDataSource remoteAlbumDataSource;

	@VisibleForTesting
	List<TrackList> trackCaches;

	@Inject
	public MusixMatchRepository(@Local ChartDataSource localChartDataSource,
								@Remote ChartDataSource remoteChartDataSource,
								@Remote AlbumDataSource remoteAlbumDataSource) {
		this.localChartDataSource = localChartDataSource;
		this.remoteChartDataSource = remoteChartDataSource;
		this.remoteAlbumDataSource = remoteAlbumDataSource;

		trackCaches = new ArrayList<>();
	}

	public Observable<List<TrackList>> loadLocalTracks() {

		if (trackCaches.size() > 0) {
			// if cache is available, return it immediately
			return Observable.just(trackCaches);
		} else {
			// else return data from local storage
			return localChartDataSource.getTracks()
					.take(1)
					.flatMap(Observable::fromIterable)
					.doOnNext(track -> trackCaches.add(track))
					.toList()
					.toObservable();
		}
	}

	@Override
	public Observable<ChartResponse> loadChartResponse(String chartName, int page, int pageSize, String country, int hasLyrics, String apiKey) {
		return remoteChartDataSource.loadChartResponse(chartName, page, pageSize, country, hasLyrics, apiKey);
	}

//	/**
//	 * Loads a question by its question id.
//	 *
//	 * @param trackId question's id.
//	 * @return a corresponding question from cache.
//	 */
//	public Observable<Track> getTrack(long trackId) {
//		return Observable.fromIterable(trackCaches).filter(track -> track.getId() == trackId);
//	}


	@Override
	public Observable<List<TrackList>> getTracks() {
		return null;
	}

	@Override
	public void addTrack(TrackList trackList) {
		throw new UnsupportedOperationException("Unsupported operation");
	}

	@Override public void clearTracksData() {
		trackCaches.clear();
		localChartDataSource.clearTracksData();
	}
}

