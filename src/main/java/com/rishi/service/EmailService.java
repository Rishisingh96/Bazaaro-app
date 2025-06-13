package com.rishi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage; // Correct import for MimeMessage
import lombok.RequiredArgsConstructor; // Keep if you're using Lombok for constructor
import org.springframework.mail.MailException;
// import org.springframework.mail.MailMessage; // REMOVE THIS IMPORT - NOT NEEDED
import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMailMessage; // REMOVE THIS IMPORT - NOT NEEDED
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
// @RequiredArgsConstructor // You can use this instead of manually writing the constructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    // If using @RequiredArgsConstructor, you don't need this explicit constructor
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationOtpEmail(String userEmail,
                                         String otp, String subject,
                                         String text) throws MessagingException {
        try {
            // 1. Create a MimeMessage directly (no cast needed)
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            // 2. Use MimeMessageHelper with the MimeMessage
            // The 'true' argument indicates a multipart message (for HTML, attachments)
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, true, "utf-8"
            );

            // 3. Set email properties
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true); // Set 'true' if your 'text' contains HTML
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom("rishicoding9838@gmail.com"); // IMPORTANT: Set a 'from' address

            // 4. Send the MimeMessage using JavaMailSender
            javaMailSender.send(mimeMessage);

            System.out.println("Email sent successfully to: " + userEmail);

        } catch (jakarta.mail.MessagingException e) { // Catch jakarta.mail.MessagingException directly
            e.printStackTrace();
            throw new MessagingException("Failed to send email: " + e.getMessage(), e);
        } catch (MailException e) { // Catch Spring's MailException for broader issues
            e.printStackTrace();
            throw new MessagingException("Failed to send email due to Spring Mail issue: " + e.getMessage(), e);
        }
        // This line below is now redundant as the actual sending happens above
        // System.out.println("Sending OTP: " + otp + " to email: " + userEmail);
    }
}



/*package com.rishi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationOtpEmail(String userEmail,
                                         String otp, String subject,
                                         String text ) throws MessagingException {
        // Logic to send email using javaMailSender
       try{
            // Create a simple email message
           MailMessage mimeMessage = (MailMessage) javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                   (MimeMessage) mimeMessage, "utf-8"
           );
           mimeMessageHelper.setSubject(subject);
           mimeMessageHelper.setText(text);
           mimeMessageHelper.setTo(userEmail);
           javaMailSender.send((MimeMessage) mimeMessage);
           System.out.println("Email sent successfully");

        } catch (MailException e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send email", e);
            // Handle exceptions (e.g., log them, rethrow, etc.)
        }
        // This is a placeholder for the actual email sending logic
        System.out.println("Sending OTP: " + otp + " to email: " + userEmail);
    }
}
*/