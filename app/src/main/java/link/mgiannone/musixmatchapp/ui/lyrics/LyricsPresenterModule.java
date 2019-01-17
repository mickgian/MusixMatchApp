package link.mgiannone.musixmatchapp.ui.lyrics;

import dagger.Module;
import dagger.Provides;

@Module
public class LyricsPresenterModule {
	private LyricsContract.View view;

	public LyricsPresenterModule(LyricsContract.View view) {
		this.view = view;
	}

	@Provides
	public LyricsContract.View provideView() {
		return view;
	}
}
