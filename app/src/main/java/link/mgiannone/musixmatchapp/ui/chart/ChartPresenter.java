package link.mgiannone.musixmatchapp.ui.chart;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.List;
import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import link.mgiannone.musixmatchapp.data.Config;
import link.mgiannone.musixmatchapp.data.model.AlbumResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.TrackList;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse.Lyrics;
import link.mgiannone.musixmatchapp.data.repository.MusixMatchRepository;
import link.mgiannone.musixmatchapp.util.schedulers.RunOn;

import static link.mgiannone.musixmatchapp.util.schedulers.SchedulerType.IO;
import static link.mgiannone.musixmatchapp.util.schedulers.SchedulerType.UI;

/**
 * A presenter with life-cycle aware.
 */
public class ChartPresenter implements ChartContract.Presenter, LifecycleObserver {

	private static final String TAG = ChartPresenter.class.getSimpleName();


	private MusixMatchRepository repository;

	private ChartContract.View view;

	private Scheduler ioScheduler;
	private Scheduler uiScheduler;

	private CompositeDisposable disposeBag;

	private List<TrackList> tracks;

	private String selectedTrackName;
	private String selectedArtistName;
	private String selectedImageCoverUrl;


	@Inject public ChartPresenter(MusixMatchRepository repository, ChartContract.View view,
								  @RunOn(IO) Scheduler ioScheduler, @RunOn(UI) Scheduler uiScheduler) {
		this.repository = repository;
		this.view = view;
		this.ioScheduler = ioScheduler;
		this.uiScheduler = uiScheduler;

		// Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
		if (view instanceof LifecycleOwner) {
			((LifecycleOwner) view).getLifecycle().addObserver(this);
		}

		disposeBag = new CompositeDisposable();
	}

	@Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	public void onAttach() {
		loadTracks(false);
	}

	@Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	public void onDetach() {
		// Clean up any no-longer-use resources here
		disposeBag.clear();
	}

	public void loadTracks(boolean onlineRequired) {
		// Clear old data on view
		view.clearTracks();

		if(onlineRequired) {

			view.showProgressBarIfHidden();

			// Load from remote and populate into view
			Disposable disposable = repository.loadChartResponse(
					Config.PARAMETER_CHART_NAME,
					Config.PARAMETER_PAGE,
					Config.PARAMETER_PAGE_SIZE,
					Config.PARAMETER_COUNTRY,
					Config.PARAMETER_HAS_LYRICS,
					Config.MUSIX_MATCH_API_KEY)
					.subscribeOn(ioScheduler)
					.observeOn(uiScheduler)
					.subscribe(this::handleRemoteReturnedData, this::handleRemoteError, () -> view.stopLoadingIndicator());
			disposeBag.add(disposable);
		}else{
			// Load from local and populate into view
			Disposable disposable = repository.loadLocalTracks()
					.subscribeOn(ioScheduler)
					.observeOn(uiScheduler)
					.subscribe(this::handleLocalReturnedData, this::handleLocalError, () -> view.stopLoadingIndicator());
			disposeBag.add(disposable);
		}
	}



	private void handleRemoteReturnedData(ChartResponse chartResponse) {

		int code = chartResponse.getMessage().getHeader().getStatusCode();

		switch (code){
			case 200:
				tracks = chartResponse.getMessage().getBody().getTrackList();
				for(int i = 0; i < tracks.size(); i++){
					Disposable disposable = repository.loadAlbum(
							tracks.get(i).getTrack().getAlbumId(),
							Config.MUSIX_MATCH_API_KEY)
							.subscribeOn(ioScheduler)
							.observeOn(uiScheduler)
							.subscribe(this::handleRemoteReturnedAlbum, this::handleRemoteAlbumError, () -> view.stopLoadingIndicator());
					disposeBag.add(disposable);

				}
				break;
			case 400:
				view.showBadSyntaxError();
			case 401:
				view.showInvalidOrMissingApiError();
				break;
			case 402:
				view.showLimitReachedError();
				break;
			case 403:
				view.showNotAuthorizedError();
				break;
			case 404:
				view.showResourceNotFoundError();
				break;
			case 405:
				view.showRequestedMethodNotFound();
				break;
			case 500:
				view.showSomethingWentWrongError();
				break;
			case 503:
				view.showSystemBusyError();
				break;

		}

	}

	private void handleRemoteError(Throwable throwable) {
		view.showErrorMessage(throwable.getLocalizedMessage());
	}

	private void handleRemoteReturnedAlbum(AlbumResponse albumResponse) {

		int code = albumResponse.getMessage().getHeader().getStatusCode();

		switch (code){
			case 200:
				AlbumResponse.Album album = albumResponse.getMessage().getBody().getAlbum();
				for(int i = 0; i < tracks.size(); i++) {
					if(tracks.get(i).getTrack().getAlbumId() == album.getAlbumId()){
						tracks.get(i).getTrack().setAlbumImageUrl(album.getAlbumCoverart100x100());
					}
				}

				view.showTracks(tracks);
				break;
			case 400:
				view.showBadSyntaxError();
			case 401:
				view.showInvalidOrMissingApiError();
				break;
			case 402:
				view.showLimitReachedError();
				break;
			case 403:
				view.showNotAuthorizedError();
				break;
			case 404:
				view.showResourceNotFoundError();
				break;
			case 405:
				view.showRequestedMethodNotFound();
				break;
			case 500:
				view.showSomethingWentWrongError();
				break;
			case 503:
				view.showSystemBusyError();
				break;
		}

	}

	private void handleRemoteAlbumError(Throwable throwable) {
		view.showErrorMessage(throwable.getLocalizedMessage());
	}

	/**
	 * Updates view after loading data from local source is completed successfully.
	 */
	private void handleLocalReturnedData(List<TrackList> list) {
		view.stopLoadingIndicator();
		if (list != null && !list.isEmpty()) {
			view.showTracks(list);
		} else {
			loadTracks(true);
		}
	}

	/**
	 * load data from remote source if there is an error after loading data from local source.
	 */
	private void handleLocalError(Throwable throwable) {
		loadTracks(true);
	}

	@Override public void getTrack(int trackId, String trackName, String trackArtist, String trackImageCoverUrl) {

		selectedTrackName = trackName;
		selectedArtistName = trackArtist;
		selectedImageCoverUrl = trackImageCoverUrl;

		Disposable disposable = repository.loadLyrics(trackId, Config.MUSIX_MATCH_API_KEY)
				.filter(track -> track != null)
				.subscribeOn(ioScheduler)
				.observeOn(uiScheduler)
				.subscribe(this::handleRemoteReturnedLyrics, this::handleRemoteLyricsError);
		disposeBag.add(disposable);
	}

	private void handleRemoteReturnedLyrics(LyricsResponse lyricsResponse) {

		int code = lyricsResponse.getMessage().getHeader().getStatusCode();

		switch (code){
			case 200:
				Lyrics lyrics = lyricsResponse.getMessage().getBody().getLyrics();

				view.showTrackLyrics(lyrics, selectedTrackName, selectedArtistName, selectedImageCoverUrl);

				break;
			case 400:
				view.showBadSyntaxError();
			case 401:
				view.showInvalidOrMissingApiError();
				break;
			case 402:
				view.showLimitReachedError();
				break;
			case 403:
				view.showNotAuthorizedError();
				break;
			case 404:
				view.showResourceNotFoundError();
				break;
			case 405:
				view.showRequestedMethodNotFound();
				break;
			case 500:
				view.showSomethingWentWrongError();
				break;
			case 503:
				view.showSystemBusyError();
				break;
		}

	}

	private void handleRemoteLyricsError(Throwable throwable) {
		view.showErrorMessage(throwable.getLocalizedMessage());
	}

}
