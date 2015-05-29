package rnu.iit.waelgroup.student.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * Created by Wael on 28/05/2015.
 */
public class SendMail {

    public static Intent createEmailIntent(Context mContext, final String toEmail,
                                           final String subject,
                                           final String message) {
        Intent sendTo = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(toEmail) +
                "?subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(message);
        Uri uri = Uri.parse(uriText);
        sendTo.setData(uri);

        List<ResolveInfo> resolveInfos =
                mContext.getPackageManager().queryIntentActivities(sendTo, 0);

        // Emulators may not like this check...
        if (!resolveInfos.isEmpty()) {
            return sendTo;
        }

        // Nothing resolves send to, so fallback to send...
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("text/plain");
        send.putExtra(Intent.EXTRA_EMAIL,
                new String[]{toEmail});
        send.putExtra(Intent.EXTRA_SUBJECT, subject);
        send.putExtra(Intent.EXTRA_TEXT, message);

        return Intent.createChooser(send, "Your Title Here");
    }
}
