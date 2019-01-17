package link.mgiannone.musixmatchapp.ui.lyrics;

import dagger.Component;
import link.mgiannone.musixmatchapp.ui.base.ActivityScope;

@ActivityScope
@Component(modules = LyricsPresenterModule.class)
public interface LyricsComponent {
	void inject(LyricsActivity lyricsActivity);
}
