package ru.mail.park.android.architecturedemo.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mail.park.android.architecturedemo.network.ApiRepo;
import ru.mail.park.android.architecturedemo.network.UserApi;

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
        UserApi api = ApiRepo.from(mContext).getUserApi();
        api.getAll().enqueue(new Callback<List<UserApi.UserPlain>>() {
            @Override
            public void onResponse(Call<List<UserApi.UserPlain>> call,
                                   Response<List<UserApi.UserPlain>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UserApi.UserPlain> users = response.body();
                    if (hasUserCredentials(users, login, password)) {
                        onAuthSuccess();
                        progress.onSuccess();
                        return;
                    }
                }
                progress.onFailed();
            }

            @Override
            public void onFailure(Call<List<UserApi.UserPlain>> call, Throwable t) {
                progress.onFailed();
            }
        });
    }

    private static boolean hasUserCredentials(List<UserApi.UserPlain> users,
                                              String login,
                                              String pass) {
        for (UserApi.UserPlain user : users) {
            if (TextUtils.equals(user.name, login) && TextUtils.equals(user.password, pass)) {
                return true;
            }
        }
        return false;
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
