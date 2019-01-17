package link.mgiannone.musixmatchapp.data.repository;

import android.os.AsyncTask;

import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import io.reactivex.functions.Consumer;
import link.mgiannone.musixmatchapp.data.model.AlbumResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.TrackList;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse;

public class MusixMatchRepository implements ChartDataSource {

	private static final String TAG = MusixMatchRepository.class.getSimpleName();

	private ChartDataSource remoteChartDataSource;
	private ChartDataSource localChartDataSource;
	private AlbumDataSource remoteAlbumDataSource;
	private LyricsDataSource remoteLyricsDataSource;

	@VisibleForTesting
	List<TrackList> trackCaches;

	@Inject
	public MusixMatchRepository(@Local ChartDataSource localChartDataSource,
								@Remote ChartDataSource remoteChartDataSource,
								@Remote AlbumDataSource remoteAlbumDataSource,
								@Remote LyricsDataSource lyricsDataSource) {
		this.localChartDataSource = localChartDataSource;
		this.remoteChartDataSource = remoteChartDataSource;
		this.remoteAlbumDataSource = remoteAlbumDataSource;
		this.remoteLyricsDataSource = lyricsDataSource;

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
		return remoteChartDataSource.loadChartResponse(chartName, page, pageSize, country, hasLyrics, apiKey)
				.doOnNext(new Consumer<ChartResponse>() {
					@Override
					public void accept(ChartResponse chartResponse) throws Exception {
						clearTracksData();
					}
				});
	}

	public Observable<AlbumResponse> loadAlbum(int albumId, String musixMatchApiKey) {
		return remoteAlbumDataSource.loadAlbumResponse(albumId, musixMatchApiKey);
	}

	public Observable<LyricsResponse> loadLyrics(int trackId, String musixMatchApiKey) {
		return remoteLyricsDataSource.loadLyricsResponse(trackId,musixMatchApiKey);
	}

	@Override public void clearTracksData() {
		// Clear cache
		trackCaches.clear();
		// Clear data in local storage
		new DeleteAllTracksAsyncTask(localChartDataSource).execute();
	}
	@Override
	public void addTrack(TrackList trackList) {
		trackCaches.add(trackList);
		new AddTracksAsyncTask(localChartDataSource, trackList).execute();
	}

	@Override
	public Observable<List<TrackList>> getTracks() {
		return null;
	}

	private static class DeleteAllTracksAsyncTask extends AsyncTask<Void, Void, Void> {
		private ChartDataSource asyncTaskLocalChartDataSource;

		DeleteAllTracksAsyncTask(ChartDataSource localChartDataSource) {
			asyncTaskLocalChartDataSource = localChartDataSource;
		}

		@Override
		protected Void doInBackground(Void... voids) {
			asyncTaskLocalChartDataSource.clearTracksData();
			return null;
		}
	}

	private static class AddTracksAsyncTask extends AsyncTask<TrackList, Void, Void> {
		private ChartDataSource asyncTaskLocalChartDataSource;
		private TrackList trackList;

		AddTracksAsyncTask(ChartDataSource localChartDataSource, TrackList trackList) {
			asyncTaskLocalChartDataSource = localChartDataSource;
			this.trackList = trackList;
		}

		@Override
		protected Void doInBackground(TrackList... trackLists) {
			asyncTaskLocalChartDataSource.addTrack(trackList);
			return null;
		}

	}
}

