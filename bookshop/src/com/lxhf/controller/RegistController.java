package com.lxhf.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxhf.bean.Customer;
import com.lxhf.service.CustomerService;


@Controller
public class RegistController {

	@Autowired
	CustomerService customerService;
	@RequestMapping("/Regist")
	public String regist() {
		return "regist";
	}
	
	@RequestMapping("/RegistController")
	public void Regist(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String message = null;
			String username = request.getParameter("username");
			Customer customer = customerService.findCustomerByUserName(username);
			if(customer != null){
				message = "exist";
				request.setAttribute("message", message);
			}else{
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");
				String phonenum = request.getParameter("phonenum");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				String validate = request.getParameter("validate");
				String truevalidate = request.getParameter("truevalidate");
				if(password!=""&&nickname!=""&&phonenum!=""&&email!=""&&address!=""&&(phonenum.length()==11)&&validate.equalsIgnoreCase(truevalidate)){
					Customer customer1 = new Customer();
					customer1.setUsername(username);
					//md5加密
					customer1.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
					customer1.setNickname(nickname);
					customer1.setPhonenum(phonenum);
					customer1.setEmail(email);
					customer1.setAddress(address);
					customer1.setStatus(false);
					customerService.addCustomer(customer1);
					response.sendRedirect(request.getContextPath()+"/listBook");
					return;
				}
				else if(phonenum!=""&&(phonenum.length()!=11)) {
					message = "phoneillegal";
					request.setAttribute("message", message);
				}
				else if(password!=""&&nickname!=""&&phonenum!=""&&email!=""&&address!=""&&(phonenum.length()==11)&&!validate.equalsIgnoreCase(truevalidate)) {
					message = "validateerror";
					request.setAttribute("message", message);
				}
				else if(password==""||nickname==""||phonenum==""||email==""||address==""||validate==""){
					message = "paranull";
					request.setAttribute("message", message);
				}
			}
			request.getRequestDispatcher("Regist").forward(request, response);
			return;
	}
}
	

