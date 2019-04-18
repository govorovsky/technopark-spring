package hj.gsdsd.mafdsf.lesson7.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import hj.gsdsd.mafdsf.lesson7.CredentialsRepository;
import hj.gsdsd.mafdsf.lesson7.LoginPresenter;

public class LoginPresenterImpl implements LoginPresenter {

    @Nullable
    private View mView;
    private CredentialsRepository mRepository;
    private String mLogin;

    public LoginPresenterImpl(@Nullable View view,
                              @NonNull CredentialsRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void onSingInClicked(String login, String pass) {
        mLogin = login;
        mView.showProgress();
        mRepository.validateCredentials(login, pass, new CredentialsCallback());
    }

    @Override
    public void onViewDestroyed() {
        mView = null;
    }


    private class CredentialsCallback implements
            CredentialsRepository.ValidationCallback {

        @Override
        public void onSuccess() {
            if (mView != null) {
                mView.hideProgress();
                mView.showNextScreen();
            }
        }

        @Override
        public void onError() {
            if (mView != null) {
                mView.hideProgress();
                mView.showError(mLogin);
            }
        }

        @Override
        public void onNotFound() {
            throw new IllegalStateException();
        }
    }
}
