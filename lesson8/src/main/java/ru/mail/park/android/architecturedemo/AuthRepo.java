package ru.mail.park.android.architecturedemo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class AuthRepo {

    public static final String IS_LOGGED_IN = "isLoggedIn";
    private final Context mContext;

    public AuthRepo(Context context) {
        mContext = context;
    }

    public boolean isLoggedIn() {
        return PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(IS_LOGGED_IN, false);
    }

    public void login(final String login, final String password, final LoginProgress progress) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.equals(login, "test") && TextUtils.equals(password, "test")) {
                    onAuthSuccess();
                    progress.onSuccess();
                } else {
                    progress.onFailed();
                }
            }
        }, 3000);
    }

    private void onAuthSuccess() {
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .putBoolean(IS_LOGGED_IN, true)
                .apply();
    }

    public interface LoginProgress {
        void onSuccess();
        void onFailed();
    }
}
