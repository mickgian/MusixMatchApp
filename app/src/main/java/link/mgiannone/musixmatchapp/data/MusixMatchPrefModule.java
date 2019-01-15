package link.mgiannone.musixmatchapp.data;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import link.mgiannone.musixmatchapp.data.prefs.AppPreferencesHelper;
import link.mgiannone.musixmatchapp.data.prefs.PreferencesHelper;

@Module
public class MusixMatchPrefModule {

	private final Application mApplication;

	public MusixMatchPrefModule(Application application) {
		mApplication = application;
	}

	@Provides
	@ApplicationContext
	Context provideContext() {
		return mApplication;
	}

	@Provides
	@PreferenceInfo
	String providePreferenceName() {
		return Config.PREF_NAME;
	}

	@Provides
	@Singleton
	PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
		return appPreferencesHelper;
	}

}
