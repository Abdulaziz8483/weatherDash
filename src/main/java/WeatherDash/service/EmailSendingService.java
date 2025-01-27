package WeatherDash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {
    @Value("${spring.mail.username}")
    private String fromAccount;
   @Autowired
    private JavaMailSender mailSender;


   public void sendRegistrationEmail(String email,Integer profileId) {
       String subject = "Complete Registration";
       String body = "Weather Dash Registration Confirmation"+profileId;
       sendEmail(email, subject, body);
   }


    public void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAccount);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
