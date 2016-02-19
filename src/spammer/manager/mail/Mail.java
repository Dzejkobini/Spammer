package spammer.manager.mail;

public class Mail {
	
	private String sender;
	private String content;
	private String subject;
	
	public Mail(String sender, String subject, String content)
	{
		this.sender = sender;
		this.subject = subject;
		this.content = content;
	}
	
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getSedner()
	{
		return this.sender;
	}
	
	public String getSubject()
	{
		return this.subject;
	}
	
	public String getContent()
	{
		return this.content;
	}
}
