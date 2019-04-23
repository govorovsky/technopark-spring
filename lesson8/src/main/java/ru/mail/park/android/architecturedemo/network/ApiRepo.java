package ru.mail.park.android.architecturedemo.network;

import android.content.Context;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.mail.park.android.architecturedemo.ApplicationModified;

public class ApiRepo {
    private final UserApi mUserApi;
    private final OkHttpClient mOkHttpClient;

    public ApiRepo() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("https")
                        .host("tp-android-demo.firebaseio.com")
                        .build())
                .client(mOkHttpClient)
                .build();

        mUserApi = retrofit.create(UserApi.class);
    }

    public UserApi getUserApi() {
        return mUserApi;
    }

    public static ApiRepo from(Context context) {
        return ApplicationModified.from(context).getApis();
    }
}

