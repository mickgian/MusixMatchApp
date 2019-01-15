package link.mgiannone.musixmatchapp.ui.chart;

import dagger.Module;
import dagger.Provides;

@Module
public class ChartPresenterModule {
	private ChartContract.View view;

	public ChartPresenterModule(ChartContract.View view) {
		this.view = view;
	}

	@Provides
	public ChartContract.View provideView() {
		return view;
	}
}
