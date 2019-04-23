package ru.mail.park.android.architecturedemo;

import android.app.Application;
import android.text.TextUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import ru.mail.park.android.architecturedemo.repo.AuthRepo;

public class LoginViewModel extends AndroidViewModel {

    private LoginData mLastLoginData = new LoginData("", "");

    private MutableLiveData<LoginState> mLoginState = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginState.setValue(LoginState.NONE);
    }

    public void login(String login, String password) {
        LoginData last = mLastLoginData;
        LoginData loginData = new LoginData(login, password);
        mLastLoginData = loginData;

        if (!loginData.isValid()) {
            mLoginState.postValue(LoginState.ERROR);
        } else if (last != null && last.equals(loginData)) {
            if (mLoginState.getValue() == LoginState.NONE) {
                requestLogin(loginData);
            }
        } else if (mLoginState.getValue() != LoginState.IN_PROGRESS) {
            requestLogin(loginData);
        }
    }

    private void requestLogin(final LoginData loginData) {
        mLoginState.postValue(LoginState.IN_PROGRESS);
        new AuthRepo(getApplication()).login(loginData.getLogin(), loginData.getPassword(), new AuthRepo.LoginProgress() {
            @Override
            public void onSuccess() {
                mLoginState.postValue(LoginState.SUCCESS);
            }

            @Override
            public void onFailed() {
                mLoginState.postValue(LoginState.FAILED);
            }
        });
    }

    public LiveData<LoginState> getProgress() {
        return mLoginState;
    }

    enum LoginState {
        NONE,
        ERROR,
        IN_PROGRESS,
        SUCCESS,
        FAILED
    }

    public static class LoginData {
        private final String mLogin;
        private final String mPassword;

        public LoginData(String login, String password) {
            mLogin = login;
            mPassword = password;
        }

        public String getLogin() {
            return mLogin;
        }

        public String getPassword() {
            return mPassword;
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(mLogin) && !TextUtils.isEmpty(mPassword);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LoginData loginData = (LoginData) o;
            return Objects.equals(mLogin, loginData.mLogin) &&
                    Objects.equals(mPassword, loginData.mPassword);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mLogin, mPassword);
        }
    }
}
