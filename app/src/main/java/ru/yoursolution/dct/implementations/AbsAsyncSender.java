package ru.yoursolution.dct.implementations;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import ru.yoursolution.dct.utils.MailSenderClass;
import ru.yoursolution.dct.utils.utils;

/**
 * Created by Admin on 16.11.2016.
 */

public abstract class AbsAsyncSender {

    private View snackView;

    private class SendMailAsync extends AsyncTask<Object, String, Boolean> {
        //ProgressDialog WaitingDialog;
        @Override
        protected void onPreExecute() {
            utils.snackBarLong(getSnackView(), "Email sending started");
        }

        @Override
        protected void onPostExecute(Boolean result) {
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            sendMail();
            return false;
        }
    }

    private void sendMail() {
        MailSenderClass m = new MailSenderClass("roma.berendeev@gmail.com", "ZAQ!@wsx");
        //MailSenderClass m = new MailSenderClass();

        String[] toArr = {"roman-nevr@yandex.ru"};
        m.setTo(toArr);
        m.setFrom("wooo@wooo.com");
        m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
        m.setMessage("Email body.");

        try {
            //m.addAttachment("Download/15281.jpg");
            m.addAttachment(getFileName());

            if(m.send() && (getSnackView() != null)) {
                utils.snackBarLong(getSnackView(), "Email was sent successfully.");
                Log.d("MailApp", "Email was sent successfully.");
            } else {
                utils.snackBarLong(getSnackView(), "Email was not sent.");
                Log.d("MailApp", "Email was sent successfully.");
            }
        } catch(Exception e) {
            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
            Log.e("MailApp", "Could not send email", e);
            e.printStackTrace();
        }
    }

    public void sendAsync(){
        SendMailAsync sendMailAsync = new SendMailAsync();
        sendMailAsync.execute();
    }

    public void setSnackView(View snackView) {
        this.snackView = snackView;
    }

    public abstract String getFileName();

    public abstract View getSnackView();

   /* public class EmailData{
        private String[] toArr;
        private String login, password,
    }*/
}
