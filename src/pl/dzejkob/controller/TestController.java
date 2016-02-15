package pl.dzejkob.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import pl.dzejkob.manager.mail.MailServer;
import pl.dzejkob.manager.mail.Mail;
import pl.dzejkob.manager.mail.MailManager;

@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		MailManager mm = MailManager.getInstance();
		System.out.println(mm.getMailAddres(MailServer.TENMINUTEMAIL_NET));
		List<Mail> mailList = mm.getMailList(MailServer.TENMINUTEMAIL_NET);
		System.out.println(mailList.get(1).getSedner());
		System.out.println(mailList.get(1).getSubject());
		System.out.println(mailList.get(1).getContent());
		return "test";
	}
}
