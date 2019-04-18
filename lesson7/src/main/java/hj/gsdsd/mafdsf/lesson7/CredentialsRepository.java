package hj.gsdsd.mafdsf.lesson7;

import android.support.annotation.MainThread;

public interface CredentialsRepository {

    void validateCredentials(String login,
                             String pass,
                             ValidationCallback validationCallback);

    interface ValidationCallback {
        @MainThread
        void onSuccess();

        @MainThread
        void onError();

        void onNotFound();
    }
}
