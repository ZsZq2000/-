package com.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.entity.Shops;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ShopService;

@Controller
@Scope("prototype")
public class ShopAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired ShopService shopService;
	Shops shop = new Shops();
	private Map<String, Object> session;
	Shops shops;
	
	public Shops getShop() {
		return shop;
	}
	public void setShop(Shops shop) {
		this.shop = shop;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//��Աע��
	public String shopRegister() {
		ActionContext context = ActionContext.getContext();
		String errorRegister = null;
		session = (Map<String, Object>) context.getSession();
		if(shopService.checkShop(shop.getSusername())) {
			errorRegister = "�Ѵ��ڸ��û�";
			if(session.get("errorRegister") != null) {
				session.replace("errorRegister", errorRegister);
			}else {
				session.put("errorRegister", errorRegister);
			}
			return ERROR;
		}else {
			if((new CheckMessage()).isUserName(shop.getSusername()) == false) {
				errorRegister = "�û�����ʽ����";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			if((new CheckMessage()).isPassword(shop.getSpassword()) == false) {
				errorRegister = "�����ʽ����";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			if((new CheckMessage()).isPhoneNum(shop.getSphone()) == false) {
				errorRegister = "�ֻ��Ÿ�ʽ����";
				if(session.get("errorRegister") != null) {
					session.replace("errorRegister", errorRegister);
				}else {
					session.put("errorRegister", errorRegister);
				}
				return ERROR;
			}
			shopService.shopRegister(shop.getSusername(), shop.getSname(),
					shop.getSpassword(), shop.getSsex(), shop.getSphone(),
					shop.getSshopname(), shop.getSlocal());
			if(session.get("Susername") != null) {
				session.replace("Susername", shop.getSusername());
			}else {
				session.put("Susername", shop.getSusername());
			}
			return SUCCESS;
		}
	}
	//��Ա��¼
	public String shopLogin() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String errorLogin = null;
		if(shopService.checkShop(shop.getSusername())) {
			Shops user = shopService.ShopByUsername(shop.getSusername());
			if(user.getSpassword().equals(shop.getSpassword())) {
				if(session.get("Susername") != null) {
					session.replace("Susername", shop.getSusername());
				}else {
					session.put("Susername", shop.getSusername());
				}
				return SUCCESS;
			}
			errorLogin = "����������";
			session.put("errorLogin", errorLogin);
			return ERROR;
		}else {
			errorLogin = "�����ڸ��û�";
			session.put("errorLogin", errorLogin);
			return ERROR;
		}
	}
	
	public String shopOutside() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		session.remove("Susername");
		if(session.get("errorLogin") != null) {
			session.remove("errorLogin");
		}
		if(session.get("errorRegister") != null) {
			session.remove("errorRegister");
		}
		if(session.get("errorUpdate") != null) {
			session.remove("errorUpdate");
		}
		if(session.get("errorBook") != null) {
			session.remove("errorBook");
		}
		if(session.get("table") != null) {
			session.remove("table");
		}
		if(session.get("errorTable") != null) {
			session.remove("errorTable");
		}
		return SUCCESS;
	}
	
	//�鿴������Ϣ
	public String viewShopMessage() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		if(session.get("errorUpdate") != null) {
			session.remove("errorUpdate");
		}
		String susername = (String) session.get("Susername");
		shop = shopService.ShopByUsername(susername);
		return SUCCESS;
	}
	
	public String shopPassword() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String susername = (String) session.get("Susername");
		Shops shoptemp = shopService.ShopByUsername(susername);
		shoptemp.setSpassword(shop.getSpassword());
		shopService.saveShop(shoptemp);
		shop = shoptemp;
		return SUCCESS;
	}
	
	public String shopPhone() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String errorUpdate = null;
		String susername = (String) session.get("Susername");
		Shops shoptemp = shopService.ShopByUsername(susername);
		if((new CheckMessage()).isPhoneNum(shop.getSphone()) == false) {
			errorUpdate = "�ֻ������ʽ����";
			if(session.get("errorUpdate") != null) {
				session.replace("errorUpdate", errorUpdate);
			}else {
				session.put("errorUpdate", errorUpdate);
			}
			shop = shoptemp;
			return ERROR;
		}else {
			if(session.get("errorUpdate") != null) {
				session.remove("errorUpdate");
			}
		}
		shoptemp.setSphone(shop.getSphone());
		shopService.saveShop(shoptemp);
		shop = shoptemp;
		return SUCCESS;
	}
	
	public String shopShopname() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String susername = (String) session.get("Susername");
		Shops shoptemp = shopService.ShopByUsername(susername);
		shoptemp.setSshopname(shop.getSshopname());
		shopService.saveShop(shoptemp);
		shop = shoptemp;
		return SUCCESS;
	}
	
	public String shopLocal() {
		ActionContext context = ActionContext.getContext();
		session = (Map<String, Object>) context.getSession();
		String susername = (String) session.get("Susername");
		Shops shoptemp = shopService.ShopByUsername(susername);
		shoptemp.setSlocal(shop.getSlocal());
		shopService.saveShop(shoptemp);
		shop = shoptemp;
		return SUCCESS;
	}

}
