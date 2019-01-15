package link.mgiannone.musixmatchapp.ui.chart;

import dagger.Component;
import link.mgiannone.musixmatchapp.data.MusixMatchRepositoryComponent;
import link.mgiannone.musixmatchapp.ui.base.ActivityScope;
import link.mgiannone.musixmatchapp.util.schedulers.SchedulerModule;

@ActivityScope
@Component(modules = {ChartPresenterModule.class, SchedulerModule.class}, dependencies = {
		MusixMatchRepositoryComponent.class
})
public interface ChartComponent {
	void inject(ChartActivity chartActivity);
}
