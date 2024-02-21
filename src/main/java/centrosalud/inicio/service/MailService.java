/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package centrosalud.inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    
    @Autowired
    private JavaMailSender javaMail;
    
    @Value("${spring.mail.username}")
    private String userName;
    
    @Override
    public void sendMail(String email, int code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(userName);
        msg.setTo(email);
        msg.setSubject("Codigo de vrificación");
        msg.setText("Su codigo de verificacion es: " + code);
        javaMail.send(msg);
    }
    
}
