package hj.gsdsd.mafdsf.lesson7;

public interface LoginPresenter {

    void onSingInClicked(String login, String pass);

    void onViewDestroyed();

    interface View {

        void showProgress();

        void hideProgress();

        void showError(String login);

        void showNextScreen();
    }
}
