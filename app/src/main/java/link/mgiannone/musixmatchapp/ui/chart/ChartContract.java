package link.mgiannone.musixmatchapp.ui.chart;

import java.util.List;

import link.mgiannone.musixmatchapp.data.model.ChartResponse.TrackList;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse.Lyrics;
import link.mgiannone.musixmatchapp.ui.base.BasePresenter;

public interface ChartContract {
	interface View {
		void showTracks(List<TrackList> tracks);

		void clearTracks();

		void showNoDataMessage();

		void showErrorMessage(String error);

		void showTrackLyrics(Lyrics lyrics, String trackName, String trackArtist, String imageCoverUrl);

		void stopLoadingIndicator();

		void showProgressBarIfHidden();

		void showBadSyntaxError();

		void showInvalidOrMissingApiError();

		void showLimitReachedError();

		void showNotAuthorizedError();

		void showResourceNotFoundError();

		void showRequestedMethodNotFound();

		void showSomethingWentWrongError();

		void showSystemBusyError();
	}

	interface Presenter extends BasePresenter<View> {

		void getTrack(int trackId, String trackName, String trackArtist, String trackImageCoverUrl);

	}
}
