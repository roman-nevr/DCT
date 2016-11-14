package ru.yoursolution.dct.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MailSenderClass extends javax.mail.Authenticator {
	private String user;
	private String pass;

	private String[] to;
	private String from;

	private String _port;
	private String _sport;

	private String _host;

	private String subject;
	private String message;

	private boolean _auth;

	private boolean _debuggable;

	private Multipart _multipart;


	public MailSenderClass() {
		_host = "smtp.gmail.com"; // default smtp server
		_port = "465"; // default smtp port
		_sport = "465"; // default socketfactory port

		user = ""; // username
		pass = ""; // password
		from = ""; // email sent from
		subject = ""; // email subject
		message = ""; // email body

		_debuggable = false; // debug mode on or off - default off
		_auth = true; // smtp authentication - default on

		_multipart = new MimeMultipart();

		// There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this bit needs to be added.
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
	}

	public MailSenderClass(String user, String pass) {
		this();

		this.user = user;
		this.pass = pass;
	}

	public boolean send() throws Exception {
		Properties props = _setProperties();

		if(!user.equals("") && !pass.equals("") && to.length > 0 && !from.equals("") && !subject.equals("") && !message.equals("")) {
			Session session = Session.getDefaultInstance(props, this);

			MimeMessage msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(from));

			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				addressTo[i] = new InternetAddress(to[i]);
			}
			msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

			msg.setSubject(subject);
			msg.setSentDate(new Date());

			// setup message body
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(message);
			_multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			msg.setContent(_multipart);

			// send email
			Transport.send(msg);

			return true;
		} else {
			return false;
		}
	}

	public void addAttachment(String filename) throws Exception {
		BodyPart messageBodyPart = new MimeBodyPart();
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        DataSource source = new FileDataSource(filelocation.getAbsolutePath());
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);

        /*File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "download/15281.jpg");
        Uri path = Uri.fromFile(filelocation);*/

		_multipart.addBodyPart(messageBodyPart);

        /*
        BodyPart attachBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        attachBodyPart.setDataHandler(new DataHandler(source));
        attachBodyPart.setFileName(filename);

        _multipart.addBodyPart(attachBodyPart);
         */
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pass);
	}

	private Properties _setProperties() {
		Properties props = new Properties();

		/*props.put("mail.smtp.host", _host);

		if(_debuggable) {
			props.put("mail.debug", "true");
		}

		if(_auth) {
			props.put("mail.smtp.auth", "true");
		}

		props.put("mail.smtp.port", _port);
		props.put("mail.smtp.socketFactory.port", _sport);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");*/

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", _host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");


		return props;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}