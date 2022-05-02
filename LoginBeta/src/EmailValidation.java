import javax.mail.internet.InternetAddress;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class EmailValidation {

    // use the java mail library to check if an email address is formatted correctly
    public static boolean isValidEmail(String email) {
        // check the formatting of an email for example an email address should have name@domain. if it doesn't have any of these it becomes false
        boolean isValid = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception e) {
            System.out.println("This is not a valid email");
            isValid = false;
        }
        return isValid;
    }

    // gets user domain of email. the domain is usually the @gmail.com part
    public static String getDomain(String email) {
        // get the index of an @ symbol.
        int getAtSymbol = email.indexOf("@");
        System.out.println("the domain of this user is >>> " + email.substring(getAtSymbol+1));
        // returns the domain name without the @.
        return email.substring(getAtSymbol+1);
    }

    /*
     * Email validation.
     * This method connects to MX to see if the domain of the email address is real.
     * if it pass a try statement & return a true & allow for the email to be sent out
     * if it fails then the catch statement makes this false.
     * this is not full proof to stop mistake & spammers, it is only meant to try & help.
     * the last validation is a verification email.
     */
    public static boolean doesEmailDomainExist(String hostName) {
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            env.put("java.naming.provider.url", "dns://8.8.8.8/");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrsl = ctx.getAttributes(hostName, new String[] {"MX"});
            Attribute attr = attrsl.get("MX");

            return true;

        } catch (Exception e ) {
            System.out.println("There seem to be no domain with the address please try again");
            return false;
        }

    }

}
