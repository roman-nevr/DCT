package ru.yoursolution.dct.utils;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Admin on 16.11.2016.
 */


public class IntentEmailSender {

    private ArrayList<String> recipients = new ArrayList<>();
    private String subject;
    private String message;
    private String path;
    private Activity activity;

    public IntentEmailSender(Activity activity, String path){
        this.path = path;
        this.subject = "test";
        this.message = "test";
        this.recipients.add("roman-nevr@yandex.ru");
        this.activity = activity;
    }

    public void send(){
        String[] recipientsArray = new String[recipients.size()];
        recipients.toArray(recipientsArray);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipientsArray); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");

        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), path);
        Uri path = Uri.fromFile(filelocation);
        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
        activity.startActivity(Intent.createChooser(emailIntent , "Send email..."));
    }


}
