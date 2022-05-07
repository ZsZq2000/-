package com.action;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.entity.Customers;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CustomerService;

@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired private CustomerService customerService;
	private Customers customer = new Customers();
	//定义存储会话的结构体，此处为存储对象与字符串的映射结构 
	private Map<String, Object> session;
	
	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//游客注册
	public String customerRegister() {
		ActionContext context = ActionContext.getContext(); //获取上下文
		String errorRegister = null; //存储注册错误信息
		session = (Map<String, Object>) context.getSession(); //获取会话
		//检查该用户名是否已存在
		if(customerService.checkCustomer(customer.getCusername())) {
			//如存在该用户则将错误信息存入会话中并返回ERROR对应页面
			errorRegister = "已存在该用户";
			if(session.get("errorRegister") != null) {
				session.replace("errorRegister", errorRegister);
			}else {
				session.put("errorRegister", errorRegister);
			}
			return ERROR;
		}else {
			if((new CheckMessage()).isUserName(customer.getCusername()) == false) {
				errorRegister = "用户名格式错误";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			if((new CheckMessage()).isPassword(customer.getCpassword()) == false) {
				errorRegister = "密码格式错误";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			if((new CheckMessage()).isPhoneNum(customer.getCphone()) == false) {
				errorRegister = "手机号格式错误";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			customerService.Register(customer.getCusername(), customer.getCname(),
					customer.getCpassword(), customer.getCsex(), customer.getCphone(),
					customer.getClocal());
			session.put("Cusername", customer.getCusername());
			return SUCCESS;
		}
	}
	//顾客登录
	public String customerLogin() {
		ActionContext context = ActionContext.getContext(); //获取上下文
		session = (Map<String, Object>) context.getSession(); //获取会话
		String errorLogin = null; //设置登录错误信息存储变量
		//检查当前用户是否存在
		if(customerService.checkCustomer(customer.getCusername())) {
			//若存在，则获取该用户
			Customers user = customerService.CustomerByUsername(customer.getCusername());
			//检测输入密码是否与用户密码一致
			if(user.getCpassword().equals(customer.getCpassword())) {
				//一致，则将在会话中保存该顾客的登录状态
				session.put("Cusername", customer.getCusername());
				return SUCCESS; //跳转登录成功页面
			}
			//密码错误则将对应错误信息保存至会话中，并跳转错误页面
			errorLogin = "输出密码错误";
			session.put("errorLogin", errorLogin);
			return ERROR;
		}else {
			//如该用户不存在，则保存相应错误信息至会话中，并跳转至错误界面
			errorLogin = "不存在该用户";
			session.put("errorLogin", errorLogin);
			return ERROR;
		}
	}
	//注销登录
	public String customerOutside() {
		ActionContext context = ActionContext.getContext(); //获取上下文
		session = (Map<String, Object>) context.getSession(); //获取会话
		session.remove("Cusername"); //从会话中移除用户登录状态
		//相应错误提示信息记录的删除
		if(session.get("errorLogin") != null) {
			session.remove("errorLogin");
		}
		if(session.get("errorRegister") != null) {
			session.remove("errorRegister");
		}
		if(session.get("errorUpdate") != null) {
			session.remove("errorUpdate");
		}
		return SUCCESS;
	}
	//查看个人信息
	public String viewCustomerMessage() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		if(session.get("errorUpdate") != null) {
			session.remove("errorUpdate");
		}
		String cusername = (String) session.get("Cusername");
		customer = customerService.CustomerByUsername(cusername);
		return SUCCESS;
	}
	//用户修改密码
	public String customerPassword() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String cusername = (String) session.get("Cusername");
		Customers customertemp = customerService.CustomerByUsername(cusername);
		customertemp.setCpassword(customer.getCpassword());
		customerService.saveCustomer(customertemp);
		customer = customertemp;
		return SUCCESS;
	}
	//用户修改联系方式
	public String customerPhone() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String errorUpdate = null;
		String cusername = (String) session.get("Cusername");
		Customers customertemp = customerService.CustomerByUsername(cusername);
		if((new CheckMessage()).isPhoneNum(customer.getCphone()) == false) {
			errorUpdate = "手机号码格式错误";
			if(session.get("errorUpdate") != null) {
				session.replace("errorUpdate", errorUpdate);
			}else {
				session.put("errorUpdate", errorUpdate);
			}
			customer = customertemp;
			return ERROR;
		}else {
			if(session.get("errorUpdate") != null) {
				session.remove("errorUpdate");
			}
		}
		customertemp.setCphone(customer.getCphone());
		customerService.saveCustomer(customertemp);
		customer = customertemp;
		return SUCCESS;
	}
	//用户修改默认地址
	public String customerLocal() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String cusername = (String) session.get("Cusername");
		Customers customertemp = customerService.CustomerByUsername(cusername);
		customertemp.setClocal(customer.getClocal());
		customerService.saveCustomer(customertemp);
		customer = customertemp;
		return SUCCESS;
	}
	
}
