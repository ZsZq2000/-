package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ImportDao;
import com.entity.Imports;

@Service("importService")
@Transactional
public class ImportService {
	@Resource ImportDao importDao;
	public void insertImport(String susername, String bnumber, Double idiscount,
			Integer inumgoods) {
		Imports imports = new Imports();
		imports.setShops(susername);
		imports.setBooks(bnumber);
		imports.setIdiscount(idiscount);
		imports.setInumgoods(inumgoods);
		importDao.addImport(imports);
	}
	
	public List<Imports> BookByShop(String susername){
		return importDao.getByShop(susername);
	}
	
	public List<Imports> ShopByBook(String bnumber){
		return importDao.getByBook(bnumber);
	}
	
	public Imports oneImports(String susername, String bnumber) {
		return importDao.getByBookandShop(susername, bnumber);
	}
	
	public void changeGoodsNumber(String susername, String bnumber, Integer num) {
		Imports imports = importDao.getByBookandShop(susername, bnumber);
		imports.setInumgoods(imports.getInumgoods() + num);
		importDao.updateImport(imports);
	}
	
	public void saveImport(Imports imports) {
		importDao.updateImport(imports);
	}
}
