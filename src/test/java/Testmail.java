import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class Testmail {

    public static void main(String arg[]) throws  Exception{
        System.out.println("TEst");
        Email email = new SimpleEmail();
        email.setHostName("smtp.strato.de");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("info@zowiac.eu", "A4yimdjYk2ySX9q"));
        email.setSSLOnConnect(true);
        email.setFrom("info@zowiac.eu");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("ajdin.skaljic@googlemail.com");
        email.send();


    }
}

