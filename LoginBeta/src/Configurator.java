/**
 Name: Kenja Palmer
 Date: 5/5-5/17
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class Configurator {

    private static String bot_Email = "aba";
    private static String password = "adam123";
    private static String smtp_Host = "smtp.gmail.com";
    private static String smtp_Trust = "smtp.gmail.com";
    private static String smtp_port = "587";
    private static String smtp_ssl_protocols = "TLSv1.2";

    public static boolean createConfig() throws IOException {
        File config = new File("Email Config.properties");

        if (config.exists()) {
            setConfig();
            return true;
        }else {
            createFile();
            return false;
        }

    }

    public static void createFile() throws IOException {
        File config = new File("Email Config.properties");
        config.createNewFile();
        writeFile();
    }

    public static void writeFile() {
        try {
            FileWriter config = new FileWriter("Email Config.properties");

            config.write("# Config file for sending out emails. All properties will have an effect on this program so make sure to test it before implementing" + System.getProperty("line.separator"));
            config.write(System.getProperty("line.separator"));
            config.write("# The email being used to send out messages" + System.getProperty("line.separator"));
            config.write("Bot_Email = " + getBot_Email() + System.getProperty("line.separator") );

            config.write("# The password for the email account" + System.getProperty("line.separator"));
            config.write("password = " + getPassword() + System.getProperty("line.separator"));

            config.write("# The host of the email address" + System.getProperty("line.separator"));
            config.write("mail.smtp.host = " + getSmtp_Host() + System.getProperty("line.separator"));

            config.write("# allow the program to trust the email address host" + System.getProperty("line.separator"));
            config.write( "mail.smtp.ssl.trust = " + getSmtp_Trust() +  System.getProperty("line.separator"));

            config.write("# the port the email address uses" + System.getProperty("line.separator"));
            config.write("mail.smtp.port = " + getSmtp_port() + System.getProperty("line.separator"));

            config.write("# the computer TLSv protocol." + System.getProperty("line.separator"));
            config.write("mail.smtp.ssl.protocols = " + getSmtp_ssl_protocols() + System.getProperty("line.separator"));

            config.flush();
            config.close();

        } catch (Exception e) {
            System.out.println("Failed to write config file");
        }
    }

    public static void setConfig() throws IOException {
        FileInputStream propsInput = new FileInputStream("Email Config.properties");
        FileWriter writer = new FileWriter("Email Config.properties", true);
        Properties prop = new Properties();
        prop.load(propsInput);


        //setPassword(prop.getProperty("password"));
        setPassword(prop.getProperty("password"));
        writer.append(prop.getProperty("password"));
        setBot_Email(prop.getProperty("Bot_Email"));
        setSmtp_Host(prop.getProperty("mail.smtp.host"));
        setSmtp_Trust(prop.getProperty("mail.smtp.ssl.trust"));
        setSmtp_port(prop.getProperty("mail.smtp.port"));
        setSmtp_ssl_protocols(prop.getProperty("mail.smtp.ssl.protocols"));

    }

    public static String getBot_Email() {
        return bot_Email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getSmtp_Host() {
        return smtp_Host;
    }

    public static String getSmtp_Trust() {
        return smtp_Trust;
    }

    public static String getSmtp_port() {
        return smtp_port;
    }

    public static String getSmtp_ssl_protocols() {
        return smtp_ssl_protocols;
    }

    public static void setBot_Email(String bot_Email) {
        Configurator.bot_Email = bot_Email;
    }

    public static void setPassword(String password) {
        Configurator.password = password;
    }

    public static void setSmtp_Host(String smtp_Host) {
        Configurator.smtp_Host = smtp_Host;
    }

    public static void setSmtp_Trust(String smtp_Trust) {
        Configurator.smtp_Trust = smtp_Trust;
    }

    public static void setSmtp_port(String smtp_port) {
        Configurator.smtp_port = smtp_port;
    }

    public static void setSmtp_ssl_protocols(String smtp_ssl_protocols) {
        Configurator.smtp_ssl_protocols = smtp_ssl_protocols;
    }


}