package uk.openvk.android.legacy.ui.preferences;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import uk.openvk.android.legacy.BuildConfig;
import uk.openvk.android.legacy.R;

public class HeaderPreference extends Preference {

    public HeaderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderPreference(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView versionInfo = view.findViewById(R.id.app_version_text);
        if(versionInfo != null)
            versionInfo.setText(
                    getContext().getResources().getString(
                            R.string.app_version_text,
                            BuildConfig.VERSION_NAME, BuildConfig.GITHUB_COMMIT
                    )
            );
    }
}
