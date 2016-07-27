package com.rxsampleapp;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.rxandroidnetworking.RxANRequest;
import com.rxandroidnetworking.RxAndroidNetworking;

import org.json.JSONArray;
import org.json.JSONObject;

import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by Prashant Gupta on 25-07-2016.
 */
public class RxTestActivity extends AppCompatActivity {

    private static final String TAG = RxTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
    }

    public void getAllUsers(View view) {
        RxAndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getJsonArrayObservable()
                .subscribeOn(Schedulers.computation())
                .subscribe(new Observer<JSONArray>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onComplete Detail : getAllUsers completed");
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (error instanceof ANError) {
                            ANError anError = (ANError) error;
                            if (anError.getErrorCode() != 0) {
                                // received ANError from server
                                // error.getErrorCode() - the ANError code from server
                                // error.getErrorBody() - the ANError body from server
                                // error.getErrorDetail() - just a ANError detail
                                Log.d(TAG, "onError errorCode : " + anError.getErrorCode());
                                Log.d(TAG, "onError errorBody : " + anError.getErrorBody());
                                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            }
                        } else {
                            Log.d(TAG, "onError errorMessage : " + error.getMessage());
                        }
                    }

                    @Override
                    public void onNext(JSONArray jsonArray) {
                        Log.d(TAG, "onResponse array : " + jsonArray.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void getAnUser(View view) {
        RxAndroidNetworking.get(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_OBJECT)
                .addPathParameter("userId", "1")
                .setTag(this)
                .setPriority(Priority.LOW)
                .setUserAgent("getAnUser")
                .build()
                .getJsonObjectObservable()
                .subscribeOn(Schedulers.computation())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onComplete Detail : getAnUser completed");
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (error instanceof ANError) {
                            ANError anError = (ANError) error;
                            if (anError.getErrorCode() != 0) {
                                // received ANError from server
                                // error.getErrorCode() - the ANError code from server
                                // error.getErrorBody() - the ANError body from server
                                // error.getErrorDetail() - just a ANError detail
                                Log.d(TAG, "onError errorCode : " + anError.getErrorCode());
                                Log.d(TAG, "onError errorBody : " + anError.getErrorBody());
                                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            }
                        } else {
                            Log.d(TAG, "onError errorMessage : " + error.getMessage());
                        }
                    }

                    @Override
                    public void onNext(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public void checkForHeaderGet(View view) {

        RxANRequest.GetRequestBuilder getRequestBuilder = new RxANRequest.GetRequestBuilder(ApiEndPoint.BASE_URL + ApiEndPoint.CHECK_FOR_HEADER);

        getRequestBuilder.addHeaders("token", "1234")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getJsonObjectObservable()
                .subscribeOn(Schedulers.computation())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onComplete Detail : checkForHeaderGet completed");
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (error instanceof ANError) {
                            ANError anError = (ANError) error;
                            if (anError.getErrorCode() != 0) {
                                // received ANError from server
                                // error.getErrorCode() - the ANError code from server
                                // error.getErrorBody() - the ANError body from server
                                // error.getErrorDetail() - just a ANError detail
                                Log.d(TAG, "onError errorCode : " + anError.getErrorCode());
                                Log.d(TAG, "onError errorBody : " + anError.getErrorBody());
                                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            }
                        } else {
                            Log.d(TAG, "onError errorMessage : " + error.getMessage());
                        }
                    }

                    @Override
                    public void onNext(JSONObject response) {
                        Log.d(TAG, "onResponse object : " + response.toString());
                        Log.d(TAG, "onResponse isMainThread : " + String.valueOf(Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }
}
