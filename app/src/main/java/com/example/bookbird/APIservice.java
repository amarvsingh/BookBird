package com.example.bookbird;

import com.example.bookbird.Notification.MyResponseNotification;
import com.example.bookbird.Notification.SenderNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIservice {
    @Headers(
            {
                "Content-type:application/json",
                "Authorization:key=AAAAiFfVIPk:APA91bHgbadPOuSty2G0-6aha8089jGzBuUBIy_uHDLrVVP0fjGi7-4N05XLoy75lD7yhoib1JlKEKGJ3EWLvFjwi8qWXvnt1RgV6WWdWvVapLbiMUIN_36sWgahsT2af7CZX0C12IvB"
            }
    )

    @POST("fcm/send")
    Call<MyResponseNotification> sendNotification(@Body SenderNotification body);
}
