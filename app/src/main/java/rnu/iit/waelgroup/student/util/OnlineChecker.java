package rnu.iit.waelgroup.student.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Wael on 01/06/2015.
 */
public class OnlineChecker {
    public  boolean isOnline(Context c){
        ConnectivityManager cm = (ConnectivityManager)
                c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo !=null && netInfo.isConnectedOrConnecting()){
            return  true;
        }else {
            return false;
        }
    }

}
