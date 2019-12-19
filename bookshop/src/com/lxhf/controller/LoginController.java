package com.lxhf.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxhf.bean.Customer;
import com.lxhf.service.CustomerService;

@Controller
public class LoginController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/Login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/LoginController")
	public void login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = "";
		String validate = request.getParameter("validate");
		String truevalidate = request.getParameter("truevalidate");
		HttpSession session = request.getSession();
		//md5加密转换
		Customer customer = customerService.findOneCustomer(username, DigestUtils.md5DigestAsHex(password.getBytes()));
		if(username!=""&&password!=""&&validate!="") {
			if(validate.equalsIgnoreCase(truevalidate)) {
				if(customer != null && customer.getStatus() == false)
				{
					
					if(customerService.updateCustomerStatus(customer,true)==true)
					{	
						Integer characterid = customer.getCharacterid();
						String character = customerService.findCustomerCharacter(characterid);
						if("user".equals(character)){				
						
							session.setAttribute("customer.id",customer.getId());
							session.setAttribute("customer",customer);
							response.sendRedirect(request.getContextPath()+"/listBook");
							return;
						}
						else if("manager".equals(character)){
							session.setAttribute("customer.id",customer.getId());
							session.setAttribute("customer",customer);
							session.setAttribute("managerFlag", "true");
					
							response.sendRedirect(request.getContextPath()+"/manager");
							return;
						}
					}
					else
					{
						message = "false";
						request.setAttribute("message", message);
					}
				}
				else if(customer != null && customer.getStatus() == true) {
					message = "online";
					request.setAttribute("message", message);
				}
				else if(customer == null) {
					message = "no";
					request.setAttribute("message", message);
				}
			}
			else {
				message = "validateerror";
				request.setAttribute("message", message);
			}
		}
		else if(username==""||password==""||validate=="") {
			message = "paranull";
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("Login").forward(request, response);
		return;
	}

	
}
