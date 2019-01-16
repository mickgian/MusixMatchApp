package link.mgiannone.musixmatchapp.ui.chart;

import android.content.Context;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

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

		@BindView(R.id.track_album_image_view)
		ImageView trackAlbumImageView;
		@BindView(R.id.track_title_text_view)
		TextView trackTitleTextView;
		@BindView(R.id.track_artist_text_view)
		TextView trackArtistTextView;

		public TrackViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}

	private List<TrackList> trackList;
	private Context context;

	public ChartAdapter(@NonNull List<TrackList> trackList, Context context) {
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
		TrackList trackList = this.trackList.get(i);

		Glide.with(vh.trackAlbumImageView)
				.load(ResourcesCompat.getDrawable(context.getResources(), R.drawable.placeholder, null))
				.into(vh.trackAlbumImageView);
		vh.trackTitleTextView.setText(trackList.getTrack().getTrackName());
		vh.trackArtistTextView.setText(trackList.getTrack().getArtistName());

	}

	@Override public int getItemCount() {
		return trackList.size();
	}

	public void replaceData(List<TrackList> trackList) {
		this.trackList.clear();
		this.trackList.addAll(trackList);
		notifyDataSetChanged();
	}

	public TrackList getItem(int position) {
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
