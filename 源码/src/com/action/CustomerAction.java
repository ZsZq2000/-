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
	//����洢�Ự�Ľṹ�壬�˴�Ϊ�洢�������ַ�����ӳ��ṹ 
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
	
	//�ο�ע��
	public String customerRegister() {
		ActionContext context = ActionContext.getContext(); //��ȡ������
		String errorRegister = null; //�洢ע�������Ϣ
		session = (Map<String, Object>) context.getSession(); //��ȡ�Ự
		//�����û����Ƿ��Ѵ���
		if(customerService.checkCustomer(customer.getCusername())) {
			//����ڸ��û��򽫴�����Ϣ����Ự�в�����ERROR��Ӧҳ��
			errorRegister = "�Ѵ��ڸ��û�";
			if(session.get("errorRegister") != null) {
				session.replace("errorRegister", errorRegister);
			}else {
				session.put("errorRegister", errorRegister);
			}
			return ERROR;
		}else {
			if((new CheckMessage()).isUserName(customer.getCusername()) == false) {
				errorRegister = "�û�����ʽ����";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			if((new CheckMessage()).isPassword(customer.getCpassword()) == false) {
				errorRegister = "�����ʽ����";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			if((new CheckMessage()).isPhoneNum(customer.getCphone()) == false) {
				errorRegister = "�ֻ��Ÿ�ʽ����";
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
	//�˿͵�¼
	public String customerLogin() {
		ActionContext context = ActionContext.getContext(); //��ȡ������
		session = (Map<String, Object>) context.getSession(); //��ȡ�Ự
		String errorLogin = null; //���õ�¼������Ϣ�洢����
		//��鵱ǰ�û��Ƿ����
		if(customerService.checkCustomer(customer.getCusername())) {
			//�����ڣ����ȡ���û�
			Customers user = customerService.CustomerByUsername(customer.getCusername());
			//������������Ƿ����û�����һ��
			if(user.getCpassword().equals(customer.getCpassword())) {
				//һ�£����ڻỰ�б���ù˿͵ĵ�¼״̬
				session.put("Cusername", customer.getCusername());
				return SUCCESS; //��ת��¼�ɹ�ҳ��
			}
			//��������򽫶�Ӧ������Ϣ�������Ự�У�����ת����ҳ��
			errorLogin = "����������";
			session.put("errorLogin", errorLogin);
			return ERROR;
		}else {
			//����û������ڣ��򱣴���Ӧ������Ϣ���Ự�У�����ת���������
			errorLogin = "�����ڸ��û�";
			session.put("errorLogin", errorLogin);
			return ERROR;
		}
	}
	//ע����¼
	public String customerOutside() {
		ActionContext context = ActionContext.getContext(); //��ȡ������
		session = (Map<String, Object>) context.getSession(); //��ȡ�Ự
		session.remove("Cusername"); //�ӻỰ���Ƴ��û���¼״̬
		//��Ӧ������ʾ��Ϣ��¼��ɾ��
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
	//�鿴������Ϣ
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
	//�û��޸�����
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
	//�û��޸���ϵ��ʽ
	public String customerPhone() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String errorUpdate = null;
		String cusername = (String) session.get("Cusername");
		Customers customertemp = customerService.CustomerByUsername(cusername);
		if((new CheckMessage()).isPhoneNum(customer.getCphone()) == false) {
			errorUpdate = "�ֻ������ʽ����";
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
	//�û��޸�Ĭ�ϵ�ַ
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
