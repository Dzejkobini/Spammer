package pl.dzejkob.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import pl.dzejkob.manager.mail.Mail;
import pl.dzejkob.manager.mail.MailManager;


@Controller
@RequestMapping(value = "/console", method = RequestMethod.GET)
public class ConsoleController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView defaultResponse(){
		
		return new ModelAndView("console", "output", "Console View");
	}
	
	@RequestMapping(params={"getMailAddress"}, method = RequestMethod.GET)
	public ModelAndView retrieveMailAddress(@RequestParam(value = "getMailAddress", required=false) String param) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		return new ModelAndView("console", "output", MailManager.getInstance().getMailAddres());
	}
	
	@RequestMapping(params={"getMailList"}, method = RequestMethod.GET)
	public ModelAndView retrieveMailList(@RequestParam(value = "getMailList", required=false) String param) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		List<Mail> mailList = MailManager.getInstance().getMailList();
		StringBuilder listContent = new StringBuilder();
		for(Mail mail: mailList)
		{
			listContent.append("Sender: <br />" + mail.getSedner() + "<br />");
			listContent.append("Subject: <br />" + mail.getSubject() + "<br />");
			listContent.append("Content: <br />" + mail.getContent() + "<br />");
		}
		System.out.println(listContent.toString());
		return new ModelAndView("console", "output", listContent.toString());
	}
	
}
