package com.service;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dao.ShopDao;
import com.entity.Shops;

@Service("shopService")
@Transactional
public class ShopService implements ServiceOperator {
	@Resource private ShopDao shopDao;
	
	public Boolean checkShop(String susername) {
		return shopDao.getByUsername(susername) != null; 
	}
	
	public void shopRegister(String susername, String sname, String spassword, 
			String ssex, String sphone, String sshopname, String slocal) {
		if(this.checkShop(susername) == false)
		{
			Shops shop = new Shops();
			shop.setSusername(susername);
			shop.setSpassword(spassword);
			shop.setSname(sname);
			shop.setSsex(ssex);
			shop.setSphone(sphone);
			shop.setSshopname(sshopname);
			shop.setSlocal(slocal);
			shopDao.addShop(shop);
		}else {
			System.out.println("该用户名已存在！");
		}
	}
	
	public void ChangePassword(String susername, String spassword) {
		Shops shop = shopDao.getByUsername(susername);
		shop.setSpassword(spassword);
		shopDao.updateShop(shop);
		List<Shops> shops = shopDao.getUserList();
		Iterator<Shops> it = shops.iterator();
		while(it.hasNext()) {
			shop = it.next();
			System.out.println(shop.getSusername()+" "+shop.getSpassword());
		}
	}
	
	public Shops ShopByUsername(String susername) {
		return shopDao.getByUsername(susername);
	}
	
	public void saveShop(Shops shop) {
		shopDao.updateShop(shop);
	}
	
}
