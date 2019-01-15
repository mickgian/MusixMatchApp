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

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import link.mgiannone.musixmatchapp.R;
import link.mgiannone.musixmatchapp.data.model.ChartResponse.Track;
import link.mgiannone.musixmatchapp.ui.base.BaseActivity;

public class ChartActivity extends BaseActivity implements ChartContract.View {

	private static final String TAG = ChartActivity.class.getSimpleName();

	@BindView(R.id.recycler_repos)
	RecyclerView repoRecyclerView;
	@BindView(R.id.refreshRepos)
	SwipeRefreshLayout refreshLayout;
	@BindView(R.id.repo_owner_text_view)
	TextView repoOwnerTextView;
	@BindView(R.id.repo_text_notification)
	TextView notificationText;
	@BindView(R.id.loadReposProgressBar)
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

		// Refresh layout
		refreshLayout.setOnRefreshListener(() -> presenter.loadTracks(true));
		// Set notification text visible first
		notificationText.setVisibility(View.GONE);
	}

//	@Override public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.repositories, menu);
//
//		// Setup search widget in action bar
//		searchView = (SearchView) menu.findItem(R.id.repos_action_search).getActionView();
//		searchView.setQueryHint(getString(R.string.repo_search_hint));
//		searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//
//		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//			@Override public boolean onQueryTextSubmit(String query) {
//				owner = query;
//				presenter.checkRepoPerUser(query); //starting first call
//				searchView.clearFocus();
//				loadReposProgressBar.setVisibility(View.VISIBLE);
//				return true;
//			}
//
//			@Override public boolean onQueryTextChange(String newText) {
//				return false;
//			}
//		});
//
//		return true;
//	}

	@Override public void showTracks(List<Track> tracks) {
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

	@Override public void stopLoadingIndicator() {
		if (refreshLayout.isRefreshing()) {
			refreshLayout.setRefreshing(false);
		}
	}

	@Override public void showEmptySearchResult() {
		showNotification(getString(R.string.msg_empty_repo_search_result));
	}

	private void showNotification(String message) {
		loadReposProgressBar.setVisibility(View.GONE);
		refreshLayout.setVisibility(View.GONE);
		notificationText.setVisibility(View.VISIBLE);
		notificationText.setText(message);
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("owner", repoOwnerTextView.getText().toString());
		outState.putString("notification_text", notificationText.getText().toString());

		// call superclass to save any view hierarchy
		super.onSaveInstanceState(outState);
	}

	@Override public void showTrackDetail(Track track) {
		Intent intent = new Intent();
//		startActivity(intent);
	}
}

