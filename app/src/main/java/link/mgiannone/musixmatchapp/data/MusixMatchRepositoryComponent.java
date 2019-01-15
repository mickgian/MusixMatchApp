package link.mgiannone.musixmatchapp.data;

import javax.inject.Singleton;

import dagger.Component;
import link.mgiannone.musixmatchapp.AppModule;
import link.mgiannone.musixmatchapp.data.repository.MusixMatchRepository;

@Singleton
@Component(modules = {
		AppModule.class,
		MusixMatchRepositoryModule.class,
		MusixMatchApiServiceModule.class,
		MusixMatchDatabaseModule.class,
		MusixMatchPrefModule.class
})
public interface MusixMatchRepositoryComponent {
	MusixMatchRepository provideMusixMatchRepository();
}
