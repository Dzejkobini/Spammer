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

		if(mailLink.equals(Mail.TENMINUTEMAIL_NET))
		{
			DomElement mailContainer = index.getElementById("fe_text");
			return mailContainer.getAttribute("value");
		}
		else if(mailLink.equals(Mail.TENMINUTEMAIL_COM))
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
	public void getMailList(String mailLink) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		final HtmlPage index = webClient.getPage(mailLink);
		final HtmlTable table = index.getHtmlElementById("maillist");
		List<HtmlTableRow> rows = new ArrayList(Arrays.asList(Arrays.copyOfRange(table.getRows().toArray(), 1, table.getRows().toArray().length)));

		for (final HtmlTableRow row : rows) {
		    System.out.println("Found row");
		    for (final HtmlTableCell cell : row.getCells()) {
		        System.out.println("   Found cell: " + cell.asText());
		        if(cell.hasChildNodes())
			        if(cell.getFirstElementChild().hasAttribute("href"))
			        	System.out.println("   Found cell: " + cell.getFirstElementChild().getAttribute("href"));
		    }
		}
	}
	
	public static MailManager getInstance() {
		if(instance == null) {
			instance = new MailManager();
		}
		return instance;
	}
	
}
