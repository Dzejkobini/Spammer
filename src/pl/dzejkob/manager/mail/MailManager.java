package pl.dzejkob.manager.mail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

@Singleton
public class MailManager {

	private static MailManager instance = null;
	
	private WebClient webClient = new WebClient(BrowserVersion.CHROME);
	
	private MailManager(){
		this.webClient.getOptions().setThrowExceptionOnScriptError(false);
		this.webClient.getOptions().setCssEnabled(false);
		this.webClient.getOptions().setJavaScriptEnabled(false);
	}
	
	public String getMailAddres(String mailLink) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		
		final HtmlPage index = webClient.getPage(mailLink);

		if(mailLink.equals(MailServer.TENMINUTEMAIL_NET))
		{
			DomElement mailContainer = index.getElementById("fe_text");
			return mailContainer.getAttribute("value");
		}
		else if(mailLink.equals(MailServer.TENMINUTEMAIL_COM))
		{
			DomElement mailContainer = index.getElementById("addyForm:addressSelect");
			return mailContainer.getAttribute("value");	
		}
		else
		{
			return "";
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Mail> getMailList(String mailLink) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		final HtmlPage index = webClient.getPage(mailLink);
		final HtmlTable table = index.getHtmlElementById("maillist");
		List<HtmlTableRow> rows = new ArrayList(Arrays.asList(Arrays.copyOfRange(table.getRows().toArray(), 1, table.getRows().toArray().length)));
		List<Mail> mailList = new ArrayList<Mail>();
		
		for (final HtmlTableRow row : rows) {
			String sender = row.getCells().get(0).asText();
			String subject = row.getCells().get(1).asText();
			
		    for (final HtmlTableCell cell : row.getCells()) {
		    	
		        if(cell.hasChildNodes())
		        {
		        	for (DomNode node : cell.getChildNodes())
		        	{
		        		if(node.hasAttributes())
		        		{
		        			if(node.getAttributes().getNamedItem("href") != null)
		        			{
		        				
		        				HtmlPage mailPage = webClient.getPage(mailLink + node.getAttributes().getNamedItem("href").getNodeValue());
		        				DomElement mailContainer = mailPage.getElementById("tabs-3");	        	
		        				mailList.add(new Mail(sender, subject, mailContainer.asText()));	        				
		        			}
		        		}
		        	}
		        }
		    }
		}
		
		return mailList;
	}
	
	public static MailManager getInstance() {
		if(instance == null) {
			instance = new MailManager();
		}
		return instance;
	}
	
}
