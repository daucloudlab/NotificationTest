package kz.abcsoft.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private final int NOTIFY_ID = 101 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Context context = getApplicationContext() ;

        Intent notificationIntent = new Intent(context, MainActivity.class) ;
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT) ;
        Resources res = context.getResources() ;

        Notification.Builder builder = new Notification.Builder(context) ;
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res,R.drawable.hangrycat))
                .setTicker(res.getString(R.string.warning))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(res.getString(R.string.notifytitle))
                .setContentText(res.getString(R.string.notifytext)) ;

        Notification notification = builder.build() ;
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE) ;
        notificationManager.notify(NOTIFY_ID, notification);
    }

    public void notificationClick(View v){
        Context context = getApplicationContext() ;
        Resources res = context.getResources() ;
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://django.daulab.org")) ;
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT) ;
        Notification.Builder builder = new Notification.Builder(context) ;
        builder.setContentIntent(contentIntent)
                .setContentTitle("abcsoft")
                .setContentText("Зайди в daulab.org")
                .setSmallIcon(R.drawable.pinkhellokitty)
//                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.til))
                .setDefaults(Notification.DEFAULT_SOUND) //|Notification.DEFAULT_VIBRATE
                .setProgress(100, 50, false)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setTicker("Бұл тесттік хабарлама") ;
        Notification notification = builder.build() ;
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE) ;
        notificationManager.notify(102, notification) ;
    }
}
