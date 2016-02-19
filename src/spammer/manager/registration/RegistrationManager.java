package spammer.manager.registration;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spammer.manager.mail.Mail;

public class RegistrationManager {

	private boolean isActivationCodeRetrieved = false;
	private String activationCode;
	
	/**
	 * Konstruktor
	 */
	public RegistrationManager()
	{
		//NOP
	}

	/**
	 * Pobranie kodu aktywuj¹cego.
	 * @param mailList
	 * TODO w przysz³osci zmiana modyfikatora na private
	 */
	public void retrieveActivationCode(List<Mail> mailList)
	{
		String pattern = "(?:kod aktywacyjny:\\s*)(.*)";
		Pattern r = Pattern.compile(pattern);
	
		for(Mail mail: mailList)
		{
			if(mail.getSedner().equals("Plemiona<noreply@plemiona.pl>"))
			{
				Matcher matcher = r.matcher(mail.getContent());
				if(matcher.find())
				{
					this.activationCode = matcher.group(1);
					this.isActivationCodeRetrieved = true;
				}
			}
		}
		this.isActivationCodeRetrieved = false;
	}
}
