package link.mgiannone.musixmatchapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import link.mgiannone.musixmatchapp.data.ApplicationContext;
import link.mgiannone.musixmatchapp.data.PreferenceInfo;



public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


}
