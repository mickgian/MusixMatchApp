package link.mgiannone.musixmatchapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import com.squareup.leakcanary.LeakCanary;

import link.mgiannone.musixmatchapp.data.DaggerMusixMatchRepositoryComponent;
import link.mgiannone.musixmatchapp.data.MusixMatchPrefModule;
import link.mgiannone.musixmatchapp.data.MusixMatchRepositoryComponent;
import timber.log.Timber;

public class AndroidApplication extends Application {

  private MusixMatchRepositoryComponent musixMatchRepositoryComponent;
  private static Context context;

  @Override
  public void onCreate() {
    super.onCreate();

    AndroidApplication.context = getApplicationContext();

    initializeDependencies();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      Stetho.initializeWithDefaults(this);
    }

    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    LeakCanary.install(this);
  }

  private void initializeDependencies() {
    musixMatchRepositoryComponent = DaggerMusixMatchRepositoryComponent.builder()
        .appModule(new AppModule(this))
        .musixMatchPrefModule(new MusixMatchPrefModule(this))
        .build();
  }

  public MusixMatchRepositoryComponent getMusixMatchRepositoryComponent() {
    return musixMatchRepositoryComponent;
  }

  //to get application context from any class
  public static Context getAppContext() {
    return AndroidApplication.context;
  }
}
