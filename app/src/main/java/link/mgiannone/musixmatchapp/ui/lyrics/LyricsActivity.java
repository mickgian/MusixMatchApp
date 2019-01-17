package link.mgiannone.musixmatchapp.ui.lyrics;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import link.mgiannone.musixmatchapp.R;
import link.mgiannone.musixmatchapp.ui.base.BaseActivity;

import static link.mgiannone.musixmatchapp.data.model.LyricsResponse.*;

public class LyricsActivity extends BaseActivity implements LyricsContract.View {

	private static final String TAG = LyricsActivity.class.getSimpleName();

	@BindView(R.id.lyrics_track_album_image_view)
	ImageView albumImageView;
	@BindView(R.id.lyrics_track_title_text_view)
	TextView trackTitleTextView;
	@BindView(R.id.lyrics_artist_text_view)
	TextView trackArtistTextView;
	@BindView(R.id.lyrics_text_view)
	TextView lyricsTextView;

	@Inject
	LyricsPresenter presenter;

	private Lyrics lyrics;
	private String trackName;
	private String trackArtist;
	private String imageCoverUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lyrics);
		ButterKnife.bind(this);
		initializePresenter();
		setupWidgets();
	}

	@Override
	protected void onResume() {
		super.onResume();
		actionBarSetup(LyricsActivity.this);
	}

	private void initializePresenter() {
		DaggerLyricsComponent.builder()
				.lyricsPresenterModule(new LyricsPresenterModule(this))
				.build()
				.inject(this);
	}

	private void setupWidgets() {

		lyrics = (Lyrics) getIntent().getSerializableExtra("lyrics");
		trackName = getIntent().getStringExtra("track_name");
		trackArtist = getIntent().getStringExtra("track_artist");
		imageCoverUrl = getIntent().getStringExtra("image_cover_url");

		Glide.with(albumImageView)
				.load(imageCoverUrl)
				.into(albumImageView);
		trackTitleTextView.setText(trackName);
		trackArtistTextView.setText(trackArtist);
		lyricsTextView.setText(lyrics.getLyricsBody());
		lyricsTextView.setMovementMethod(new ScrollingMovementMethod());

	}
}
