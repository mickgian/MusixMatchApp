package link.mgiannone.musixmatchapp.ui.chart;


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import link.mgiannone.musixmatchapp.R;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.Track;
import link.mgiannone.musixmatchapp.data.model.LyricsResponse.Lyrics;
import link.mgiannone.musixmatchapp.ui.base.BaseActivity;
import link.mgiannone.musixmatchapp.ui.lyrics.LyricsActivity;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

public class ChartActivity extends BaseActivity implements ChartContract.View {

	private static final String TAG = ChartActivity.class.getSimpleName();

	@BindView(R.id.tracks_recycler_view)
	RecyclerView repoRecyclerView;
	@BindView(R.id.refreshTracks)
	SwipeRefreshLayout refreshLayout;
	@BindView(R.id.tracks_text_notification)
	TextView notificationText;
	@BindView(R.id.loadTracksProgressBar)
	ProgressBar loadReposProgressBar;

	private ChartAdapter adapter;

	@Inject
	ChartPresenter presenter;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		ButterKnife.bind(this);
		initializePresenter();
		setupWidgets();
	}

	@Override
	protected void onResume() {
		super.onResume();
		actionBarSetup(ChartActivity.this);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		notificationText.setText(savedInstanceState.getString("notification_text"));
	}

	private void initializePresenter() {
		DaggerChartComponent.builder()
				.chartPresenterModule(new ChartPresenterModule(this))
				.musixMatchRepositoryComponent(getMusixMatchRepositoryComponent())
				.build()
				.inject(this);
	}


	@Override
	public void showProgressBarIfHidden() {
		if(loadReposProgressBar.getVisibility() == View.GONE){
			loadReposProgressBar.setVisibility(View.VISIBLE);
		}
	}

	private void setupWidgets() {
		// Setup recycler view
		adapter = new ChartAdapter(new ArrayList<>(), this);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		repoRecyclerView.setLayoutManager(layoutManager);
		repoRecyclerView.setAdapter(adapter);
		repoRecyclerView.setItemAnimator(new DefaultItemAnimator());
		adapter.setOnItemClickListener(
				(view, position) -> {
					Track selectedTrack = adapter.getItem(position).getTrack();
					presenter.getTrack(selectedTrack.getTrackId(),
							selectedTrack.getTrackName(),
							selectedTrack.getArtistName(),
							selectedTrack.getAlbumImageUrl());
				});


		// Refresh layout
		refreshLayout.setOnRefreshListener(() -> presenter.loadTracks(true));
		// Set notification text visible first
		notificationText.setVisibility(View.GONE);
	}

	@Override public void showTracks(List<TrackList> tracks) {
		refreshLayout.setVisibility(View.VISIBLE);
		notificationText.setVisibility(View.GONE);
		adapter.replaceData(tracks);
		loadReposProgressBar.setVisibility(View.GONE);
	}

	@Override public void clearTracks() {
		adapter.clearData();
	}

	@Override public void showNoDataMessage() {
		showNotification(getString(R.string.msg_no_data));
	}

	@Override public void showErrorMessage(String error) {
		showNotification(error);
	}

	@Override public void showBadSyntaxError() {
		showNotification(getString(R.string.error_bad_syntax));
	}

	@Override public void showInvalidOrMissingApiError() {
		showNotification(getString(R.string.error_invalid_or_missing_api));
	}

	@Override public void showLimitReachedError() {
		showNotification(getString(R.string.error_limit_reached));
	}

	@Override public void showNotAuthorizedError() {
		showNotification(getString(R.string.error_no_authorized));
	}

	@Override public void showResourceNotFoundError() {
		showNotification(getString(R.string.error_resource_not_found));
	}

	@Override public void showRequestedMethodNotFound() {
		showNotification(getString(R.string.error_method_not_found));
	}

	@Override public void showSomethingWentWrongError() {
		showNotification(getString(R.string.error_something_went_wrong));
	}

	@Override public void showSystemBusyError() {
		showNotification(getString(R.string.error_system_busy));
	}

	@Override public void stopLoadingIndicator() {
		if (refreshLayout.isRefreshing()) {
			refreshLayout.setRefreshing(false);
		}
	}

	private void showNotification(String message) {
		loadReposProgressBar.setVisibility(View.GONE);
		refreshLayout.setVisibility(View.GONE);
		notificationText.setVisibility(View.VISIBLE);
		notificationText.setText(message);
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("notification_text", notificationText.getText().toString());

		// call superclass to save any view hierarchy
		super.onSaveInstanceState(outState);
	}

	@Override public void showTrackLyrics(Lyrics lyrics, String trackName, String trackArtist, String imageCoverUrl) {
		Intent intent = new Intent(ChartActivity.this, LyricsActivity.class);
		intent.putExtra("lyrics", (Serializable) lyrics);
		intent.putExtra("track_name", trackName);
		intent.putExtra("track_artist", trackArtist);
		intent.putExtra("image_cover_url", imageCoverUrl);
		startActivity(intent);
	}
}

