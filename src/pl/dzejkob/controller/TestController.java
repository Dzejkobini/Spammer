package pl.dzejkob.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;


@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() throws FailingHttpStatusCodeException, MalformedURLException, IOException{

		return "test";
	}
}
