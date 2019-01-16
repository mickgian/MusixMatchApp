package link.mgiannone.musixmatchapp.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleRegistry;
import androidx.appcompat.app.AppCompatActivity;
import link.mgiannone.musixmatchapp.AndroidApplication;
import link.mgiannone.musixmatchapp.R;
import link.mgiannone.musixmatchapp.data.MusixMatchRepositoryComponent;
import link.mgiannone.musixmatchapp.ui.chart.ChartActivity;


public class BaseActivity extends AppCompatActivity {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected MusixMatchRepositoryComponent getMusixMatchRepositoryComponent() {
        return ((AndroidApplication) getApplication()).getMusixMatchRepositoryComponent();
    }

//  protected MusixMatchRepositoryComponent getLoginRepositoryComponent() {
//    return ((AndroidApplication) getApplication()).getMusixMatchRepositoryComponent();
//  }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    public void actionBarSetup(Context context) {

        ActionBar mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View actionBar = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) actionBar.findViewById(R.id.title_text);

        if (context instanceof ChartActivity) {
            mTitleTextView.setText(getString(R.string.chart_activity_title));
        }
//      else if (context instanceof LoginActivity) {
//	  	mTitleTextView.setText(getString(R.string.login_activity_title));
//      }

        mActionBar.setCustomView(actionBar);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) actionBar.getParent()).setContentInsetsAbsolute(0, 0);

    }
}
