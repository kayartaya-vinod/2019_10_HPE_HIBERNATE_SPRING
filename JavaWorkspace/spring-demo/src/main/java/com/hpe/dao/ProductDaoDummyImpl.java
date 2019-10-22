package com.hpe.dao;

public class ProductDaoDummyImpl implements ProductDao {

	@Override
	public long count() throws DaoException {
		return 1000000;
	}

}
