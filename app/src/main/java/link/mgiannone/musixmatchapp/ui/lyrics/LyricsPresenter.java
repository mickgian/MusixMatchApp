package link.mgiannone.musixmatchapp.ui.lyrics;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import link.mgiannone.musixmatchapp.ui.base.BasePresenter;

public class LyricsPresenter implements BasePresenter, LifecycleObserver {

	private static final String TAG = LyricsPresenter.class.getSimpleName();

	private LyricsContract.View view;

	@Inject
	public LyricsPresenter(LyricsContract.View view){
		this.view = view;

		if(view instanceof LifecycleOwner) {
			((LifecycleOwner) view).getLifecycle().addObserver(this);
		}
	}

	@Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	public void onAttach() {

	}

	@Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	public void onDetach() {

	}
}
