package com.github.quadflask.react.navermap.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


@ReactModule(name = GpsUtilsModule.NAME)
public class GpsUtilsModule extends ReactContextBaseJavaModule {

    public static final String NAME = "GpsUtilsModule";
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    public GpsUtilsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }


    @ReactMethod
    public boolean isDeviceGPSOn(Promise promise) {
        final Activity activity = getCurrentActivity();
        LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        boolean isProvider = manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        promise.resolve(isProvider);
        return isProvider;
    }

    @ReactMethod
    public void checkDeviceGPS(Promise promise) {

        final Activity activity = getCurrentActivity();

        if (activity != null) {
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);
            Task<LocationSettingsResponse> result =
                    LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build());

            result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>()
            {
                @Override
                public void onComplete(Task<LocationSettingsResponse> task)
                {
                    try {
                        LocationSettingsResponse response = task.getResult(ApiException.class);
                    }
                    catch (ApiException exception)
                    {
                        switch (exception.getStatusCode())
                        {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    ResolvableApiException resolvable = (ResolvableApiException) exception;
                                    resolvable.startResolutionForResult(
                                            activity,
                                            REQUEST_CHECK_SETTINGS);
                                }
                                catch (ClassCastException e)
                                {
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                                break;
                        }
                    }
                }
            });

        }

    }


    void onActivityResult(int requestCode, int resultCode, Intent data) {


    }

    @Override
    public String getName() {
        return GpsUtilsModule.NAME;
    }
}