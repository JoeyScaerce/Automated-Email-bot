/**
Name: Kenja Palmer
Date: 4/18
*/
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

    public static void main(String[] args) {
        String useremail;
        String hostName;

        // validation process. if it fails a validation check then it should prompt the user to put in there email again.
        do {
            useremail = askForEmail();
            hostName = EmailValidation.getDomain(useremail);
        }while (!EmailValidation.isValidEmail(useremail) || !EmailValidation.doesEmailDomainExist(hostName));

        // after all checks this will send the email out to the user.
        sendingEmail(useremail);
    }

    // gets the user email from a prompt
    public static String askForEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("what is your email >> ");
        String email = input.nextLine();
        System.out.println(email);
        return email;
    }

    // method for sending out the email
    // has to be hidden for personal security.
    public static void sendingEmail(String useremail) {
        final String user = "";
        final  String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(useremail));
            msg.setSubject("test message");
            msg.setText("Hello if you are not the creator of this sry for the inconvenience. im working on a program :)");

            Transport.send(msg);
            System.out.println("A msg has been sent to this email address " + useremail);
        }catch (Exception  e) {
            System.out.println("an error has accord");
        }
    }

}
