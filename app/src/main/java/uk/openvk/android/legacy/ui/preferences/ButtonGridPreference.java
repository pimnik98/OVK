package uk.openvk.android.legacy.ui.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import uk.openvk.android.legacy.R;
import uk.openvk.android.legacy.ui.list.adapters.AboutAppButtonsAdapter;

public class ButtonGridPreference extends Preference {
    private String target;
    private View view;

    public ButtonGridPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonGridPreference);
        target = a.getString(R.styleable.ButtonGridPreference_target);
        a.recycle();
    }

    public ButtonGridPreference(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        this.view = view;
        linkTarget();
    }

    public void linkTarget() {
        if(target.equals("aboutApp")) {
            AboutAppButtonsAdapter adapter = new AboutAppButtonsAdapter(getContext());
            if(view != null) {
                ((GridView) view).setAdapter(adapter);
                ((GridView) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String defInstance = getContext().getResources().getString(R.string.default_instance);
                        String defInstance2 = getContext().getResources().getString(R.string.default_instance_no_https);

                        SharedPreferences global_prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String instance = global_prefs.getString("current_instance", "");

                        switch (i) {
                            case 0:
                                openWebAddress("https://github.com/openvk/mobile-android-legacy");
                                break;
                            case 1:
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                    openWebAddress(
                                            String.format("https://%s/donate", defInstance)
                                    );
                                } else {
                                    openWebAddress(
                                            String.format("https://%s/donate", defInstance2)
                                    );
                                }
                                break;
                            case 2:
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                    openWebAddress(
                                            String.format("https://%s/topic181", defInstance)
                                    );
                                } else {
                                    openWebAddress(
                                            String.format("https://%s/topic181", defInstance2)
                                    );
                                }
                                break;
                            case 3:
                                if(instance.equals(defInstance) || instance.equals(defInstance2))
                                    openWebAddress("openvk://group/club181");
                                else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                                    openWebAddress(String.format("https://%s/app", defInstance));
                                else
                                    openWebAddress(String.format("http://%s/app", defInstance2));
                                break;
                        }
                    }
                });
            }
        }
    }

    private void openWebAddress(String address) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(address));
        getContext().startActivity(i);
    }
}
