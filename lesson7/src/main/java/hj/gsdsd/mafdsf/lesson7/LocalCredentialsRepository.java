package hj.gsdsd.mafdsf.lesson7;

import java.util.HashMap;
import java.util.Map;

import hj.gsdsd.mafdsf.lesson7.executors.AppExecutors;

public class LocalCredentialsRepository
        implements CredentialsRepository {

    Map<String, String> mCredentials = new HashMap<String, String>() {{
        put("test", "test");
        put("pupkin", "qa");
        put("local", "qa");
    }};

    @Override
    public void validateCredentials(final String login, final String pass,
                                    final ValidationCallback validationCallback) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // TODO do network validation
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!mCredentials.containsKey(login)) {
                    validationCallback.onNotFound();
                } else {
                    if (mCredentials.get(login).equals(pass)) {
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                validationCallback.onSuccess();
                            }
                        });
                    } else {
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                validationCallback.onError();
                            }
                        });
                    }
                }
            }
        });
    }
}
