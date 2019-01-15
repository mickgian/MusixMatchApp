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

import link.mgiannone.musixmatchapp.data.model.ChartResponse;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.Track;
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

	@Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) public void onAttach() {
		loadTracks(false);
	}

	@Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) public void onDetach() {
		// Clean up any no-longer-use resources here
		disposeBag.clear();
	}

	public void loadTracks(boolean onlineRequired) {
		// Clear old data on view
		view.clearTracks();

		if(onlineRequired) {
			// Load from remote and populate into view
			Disposable disposable = repository.loadChartResponse()
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

		List<Track> tracks = chartResponse.getBody().getTrack();
		view.showTracks(tracks);
	}

	private void handleRemoteError(Throwable throwable) {

		String message = throwable.getMessage();
	}

	/**
	 * Updates view after loading data from local source is completed successfully.
	 */
	private void handleLocalReturnedData(List<Track> list) {
		view.stopLoadingIndicator();
		if (list != null && !list.isEmpty()) {
			view.showTracks(list);
		} else {
			view.showNoDataMessage();
		}
	}

	/**
	 * load data from remote source if there is an error after loading data from local source.
	 */
	private void handleLocalError(Throwable throwable) {
//		view.stopLoadingIndicator();
//		view.showErrorMessage(error.getLocalizedMessage());
		String message = throwable.getMessage();
		repository.loadChartResponse();
	}

	@Override public void getTrack(long trackId) {
//		Disposable disposable = repository.loadLocalTracks(trackId)
//				.filter(track -> track != null)
//				.subscribeOn(ioScheduler)
//				.observeOn(uiScheduler)
//				.subscribe(track -> view.showTrackDetail(track));
//		disposeBag.add(disposable);
	}
}
