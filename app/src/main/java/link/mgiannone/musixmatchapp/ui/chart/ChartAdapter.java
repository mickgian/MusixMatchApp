package link.mgiannone.musixmatchapp.ui.chart;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.security.InvalidParameterException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.annotations.NonNull;

import link.mgiannone.musixmatchapp.R;
import link.mgiannone.musixmatchapp.ui.base.BaseRecyclerViewAdapter;

import static link.mgiannone.musixmatchapp.data.model.ChartResponse.*;

class ChartAdapter extends BaseRecyclerViewAdapter<ChartAdapter.TrackViewHolder> {

	class TrackViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.repo_title_text_view)
		TextView repoTitleTextView;
		@BindView(R.id.repo_language_text_view)
		TextView repoLanguageTextView;
		@BindView(R.id.repo_description_text_view)
		TextView repoDescriptionTextView;

		@BindView(R.id.repo_stars_count_text_view)
		TextView repoStarsCountTextView;
		@BindView(R.id.repo_forks_count_text_view)
		TextView repoForksCountTextView;
		@BindView(R.id.repo_branches_count_text_view)
		TextView repoBranchesCountTextView;
		@BindView(R.id.repo_commits_count_text_view)
		TextView repoCommitsCountTextView;


		public TrackViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}

	private List<Track> trackList;
	private Context context;

	public ChartAdapter(@NonNull List<Track> trackList, Context context) {
		this.trackList = trackList;
		this.context = context;
	}

	@Override public TrackViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.item_tracks, viewGroup, false);
		return new TrackViewHolder(view);
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
		super.onBindViewHolder(viewHolder, i);
		TrackViewHolder vh = (TrackViewHolder) viewHolder; //safe cast
		Track track = trackList.get(i);

//		vh.repoTitleTextView.setText(track.getName());
//		vh.repoLanguageTextView.setText(track.getLanguage());
//		vh.repoDescriptionTextView.setText(track.getDescription());
//
//		vh.repoStarsCountTextView.setText(String.valueOf(track.getStargazersCount()));
//		vh.repoForksCountTextView.setText(String.valueOf(track.getForksCount()));
//		vh.repoBranchesCountTextView.setText(String.valueOf(track.getBranchesCount()));
//		vh.repoCommitsCountTextView.setText(String.valueOf(track.getCommitsCount()));
	}

	@Override public int getItemCount() {
		return trackList.size();
	}

	public void replaceData(List<Track> trackList) {
		this.trackList.clear();
		this.trackList.addAll(trackList);
		notifyDataSetChanged();
	}

	public Track getItem(int position) {
		if (position < 0 || position >= trackList.size()) {
			throw new InvalidParameterException("Invalid item index");
		}
		return trackList.get(position);
	}

	public void clearData() {
		trackList.clear();
		notifyDataSetChanged();
	}
}