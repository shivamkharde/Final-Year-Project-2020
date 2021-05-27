package com.shivamkharde.finalyearbe2020.utilities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.ByteArrayOutputStream;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationServiceUtility extends NotificationListenerService {

    Context c;

    @Override
    public void onCreate() {
        super.onCreate();
        c = getApplicationContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        String pack = sbn.getPackageName();
        String ticker ="";
        if(sbn.getNotification().tickerText != null){
            ticker = sbn.getNotification().tickerText.toString();
        }

        Bundle extraInfo = sbn.getNotification().extras;
        System.out.println(extraInfo.toString());
        String title = extraInfo.getCharSequence("android.title").toString();
        String text = extraInfo.getCharSequence("android.text").toString();
        int id1 = extraInfo.getInt(Notification.EXTRA_SMALL_ICON);
        Bitmap id = sbn.getNotification().largeIcon;


        Log.i("MSG","Package: "+pack);
        Log.i("MSG","ticker: "+ticker);
        Log.i("MSG","Title: "+title);
        Log.i("MSG","Text: "+text);

        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package",pack);
        msgrcv.putExtra("ticker",ticker);
        msgrcv.putExtra("title",title);
        msgrcv.putExtra("text",text);

        if(id != null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            id.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArr = stream.toByteArray();
            msgrcv.putExtra("icon",byteArr);
        }
        LocalBroadcastManager.getInstance(c).sendBroadcast(msgrcv);

    }


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i("MSG","Notifications Removed");
    }
}
