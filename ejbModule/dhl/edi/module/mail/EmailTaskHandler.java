package dhl.edi.module.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.log4j.Logger;

import dhl.edi.module.util.PropertiesLoad;


public class EmailTaskHandler
{
	public static final Logger LOGGER = Logger.getLogger(EmailTaskHandler.class);
    public static final String MAIL_SESSION_JNDI_NAME = "java:jboss/mail/Coretronic";
    
    
    /** Mail sender. */
    public static final String DEFAULT_SENDER = "edi@coretronic.com";
    public static final String SENDER;
    static
	{
    	SENDER = initSender();
	}
       
    private Session mailSession;
    
    /**
     * Constructs a new EmailTaskHandler with the default name.
     * @throws NamingException 
     */
	public EmailTaskHandler() throws NamingException
	{
	
		/* Set mail setting. */
		try
		{
			mailSession = (Session) new InitialContext().lookup(MAIL_SESSION_JNDI_NAME); // via JBoss mail session.
		}
		catch (NamingException e)
		{
			throw e;
		}
	}
	
	/**
	 * 
	 * @see http://blog.moove-it.com/accessing-custom-properties-from-jboss-as/
	 * @return
	 */
	private static  String initSender()
	{
		String sender;

		//Properties properties = PropertiesLoad.getProperties();
			
		//sender = properties.getProperty("mail.sender", DEFAULT_SENDER);
		sender = DEFAULT_SENDER;

		return sender;
	}
	      
    public void sendHtmlMail(String body, String subject, List<String> toMailList, List<String> ccMailList, List<String> bccMailList) throws MessagingException
	{
    	if(toMailList == null && ccMailList == null){
    		LOGGER.debug("No email address can be sent");
    		return;
    	}
    	
		try
		{
			MimeMessage m = new MimeMessage(mailSession);
			Address fromAddress = new InternetAddress(SENDER);

			m.setFrom(fromAddress);

			//add to mail address
			if(toMailList != null && toMailList.size()>0){
				
				for(String temp:toMailList){
					m.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(temp));
				}		
			}
			
			//add cc mail address
			if(ccMailList != null && ccMailList.size()>0){
				
				for(String temp:ccMailList){
					m.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(temp));
				}		
			}
			
			//add bcc mail address
			if(bccMailList != null && bccMailList.size()>0){
				
				for(String temp:bccMailList){
					m.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(temp));
				}		
			}
			
			m.setSubject(subject, "UTF-8");
			m.setSentDate(new java.util.Date());
			m.setContent(body, "text/html; charset=UTF-8");
			Transport.send(m);
			
			LOGGER.debug("Send Mail: subject: " + subject);

		}
		catch (javax.mail.MessagingException e)
		{
			throw e;
		}
	}
    
    public void sendMailWithAttachment(String body, String subject, List<String> toMailList, List<String> ccMailList, List<String> bccMailList, List<File> fileList) throws MessagingException
	{
    	if(toMailList == null && ccMailList == null){
    		LOGGER.debug("No email address can be sent");
    		return;
    	}
    	
		try
		{
			MimeMessage m = new MimeMessage(mailSession);
			Address fromAddress = new InternetAddress(SENDER);

			m.setFrom(fromAddress);

			//add to mail address
			if(toMailList != null && toMailList.size()>0){
				
				for(String temp:toMailList){
					m.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(temp));
				}		
			}
			
			//add cc mail address
			if(ccMailList != null && ccMailList.size()>0){
				
				for(String temp:ccMailList){
					m.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(temp));
				}		
			}
			
			//add bcc mail address
			if(bccMailList != null && bccMailList.size()>0){
				
				for(String temp:bccMailList){
					m.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(temp));
				}		
			}
			
			BodyPart messageBody = new MimeBodyPart();     
            messageBody.setContent(body, "text/html; charset=UTF-8");
            Multipart multipart = new MimeMultipart(); 
            multipart.addBodyPart(messageBody);
            
            MimeBodyPart attachmentBodyPart = null;
            DataSource source = null;
            
            for(File file: fileList){
            	
            	attachmentBodyPart = new MimeBodyPart();
            	source = new FileDataSource(file);
            	attachmentBodyPart.setDataHandler(new DataHandler(source));
            	attachmentBodyPart.setFileName(file.getName());
            	
            	multipart.addBodyPart(attachmentBodyPart);
            }
            
            m.setContent(multipart);          
			m.setSubject(subject, "UTF-8");
			m.setSentDate(new java.util.Date());
			Transport.send(m);
			
			LOGGER.debug("Send Mail: subject: " + subject);

		}
		catch (javax.mail.MessagingException e)
		{
			throw e;
		}
	}
    
    //Split mail list string by ";"
    public List<String> splitMailListString(String mailListString){
    	
    	if(mailListString==null)
    		return null;
    	else {
    		
    		ArrayList<String> mailList = new ArrayList<String>();
			String[] mailArray = mailListString.trim().split(",");
			
			for(int i=0; i<=mailArray.length-1 ; i++){
				
				if(mailArray!=null){
					mailList.add(mailArray[i].trim());
				}
				
			}
			
			return mailList;
		}
    }
    
  
    
}

