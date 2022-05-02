import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class test {
    public static void main(String args[]) throws Exception {
        String email = askForEmail();
        String hostName = getDomain(email);
        doesEmailDomainExist(hostName);
    }

    public static boolean doesEmailDomainExist(String hostName) {
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            env.put("java.naming.provider.url", "dns://8.8.8.8/");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrsl = ctx.getAttributes(hostName, new String[] {"MX"});
            Attribute attr = attrsl.get("MX");
            if (attr != null) {
                System.out.println("MX records:");
                for (Enumeration vals = attr.getAll(); vals.hasMoreElements();) {
                    System.out.println(vals.nextElement());
                }
            }
            return true;

        } catch (Exception e ) {
            System.out.println("There seem to be no domain with the address please try again");
            return false;
        }

    }

    public static String getDomain(String email) {
        int getAtSymbol = email.indexOf("@");
        System.out.println("the domain of this user is >>> " + email.substring(getAtSymbol+1));
        return email.substring(getAtSymbol+1);
    }

    public static String askForEmail(){

        Scanner input = new Scanner(System.in);
        System.out.print("what is your email >> ");
        String email = input.nextLine();
        return email;
    }
}