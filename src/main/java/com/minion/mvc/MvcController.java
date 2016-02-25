package com.minion.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.minion.service.MinionServiceException;
import com.minion.service.User;

@Controller
public class MvcController {

	@Autowired
	User userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {

		if (session.getAttribute("empId") == null) {
			model.addAttribute("errorMessage", "");
			return "login";
		} else {
			model.addAttribute("empId", session.getAttribute("empId"));
			model.addAttribute("password", session.getAttribute("password"));
			return "home";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(@RequestParam("empId") String empId, @RequestParam("password") String password,
			Model model, HttpSession session) {

		if (session.getAttribute("empId") == null) {
			try {
				userService.authenticate(empId, password);
				session.setAttribute("empId", empId);
				session.setAttribute("password", password);
				return new ModelAndView("home", "model", model);
			} catch (MinionServiceException ex) {
				model.addAttribute("errorMessage", "Invalid Login credentials");
				return new ModelAndView("login", "model", model);
			}

		} else {
			model.addAttribute("empId", session.getAttribute("empId"));
			model.addAttribute("password", session.getAttribute("password"));
			return new ModelAndView("home", "model", model);
		}
	}

	@RequestMapping("/home")
	public String home(Model model, HttpSession session) {
		if (session.getAttribute("empId") == null) {
			return "login";
		} else {
			return "home";
		}
	}
}
