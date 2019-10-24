package com.hpe.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LocalProductDaoBean {
	
	private HibernateTemplate ht;
	public LocalProductDaoBean(HibernateTemplate ht) {
		this.ht = ht;
	}
	
	public ProductDao getObject() {
		
		ProductDaoHibernateImpl dao = new ProductDaoHibernateImpl();
		dao.setTemplate(ht);
		
		return dao;
	}
}
