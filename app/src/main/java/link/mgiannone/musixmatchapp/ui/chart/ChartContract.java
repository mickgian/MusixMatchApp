package link.mgiannone.musixmatchapp.ui.chart;

import java.util.List;

import link.mgiannone.musixmatchapp.data.model.ChartResponse.Track;
import link.mgiannone.musixmatchapp.ui.base.BasePresenter;

public interface ChartContract {
	interface View {
		void showTracks(List<Track> tracks);

		void clearTracks();

		void showNoDataMessage();

		void showErrorMessage(String error);

		void showTrackDetail(Track track);

		void stopLoadingIndicator();

		void showEmptySearchResult();

		void showProgressBarIfHidden();
	}

	interface Presenter extends BasePresenter<View> {

		void getTrack(long trackId);

	}
}