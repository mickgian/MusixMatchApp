package link.mgiannone.musixmatchapp.data.repository;

import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.Observable;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.Track;
import link.mgiannone.musixmatchapp.util.schedulers.RunOn;

import static link.mgiannone.musixmatchapp.util.schedulers.SchedulerType.IO;
import static link.mgiannone.musixmatchapp.util.schedulers.SchedulerType.UI;

public class MusixMatchRepository implements ChartDataSource {

	private static final String TAG = MusixMatchRepository.class.getSimpleName();

	private ChartDataSource remoteChartDataSource;
	private ChartDataSource localChartDataSource;

	@VisibleForTesting
	List<Track> trackCaches;

	@Inject
	public MusixMatchRepository(@Local ChartDataSource localChartDataSource,
								@Remote ChartDataSource remoteChartDataSource) {
		this.localChartDataSource = localChartDataSource;
		this.remoteChartDataSource = remoteChartDataSource;

		trackCaches = new ArrayList<>();
	}

	public Observable<List<Track>> loadLocalTracks() {

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
	public Observable<ChartResponse> loadChartResponse() {
		return remoteChartDataSource.loadChartResponse();
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
	public Observable<List<Track>> getTracks() {
		return null;
	}

	public void addTrack(Track track) {
		//Currently, we do not need this.
		throw new UnsupportedOperationException("Unsupported operation");
	}

	@Override public void clearTracksData() {
		trackCaches.clear();
		localChartDataSource.clearTracksData();
	}
}

