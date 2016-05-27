package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {

	private OffersDao offersDao;
	
	@Autowired
	public void setOffersDao(OffersDao offersDao)
	{
		this.offersDao=offersDao;
	}
	public List<Offer> getCurrent()
	{
		return offersDao.getOffers();
	}
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	
	public void create(Offer offer) {
		offersDao.create(offer);
	}
	
	
	/* Method to check if offer is already present*/
	public boolean hasOffer(String name) {
		
		if(name==null) return false;
		
		List<Offer> offers = offersDao.getOffers(name);
		
		if(offers.size() == 0)
		{
			return false;
		}
		
		return true;
	}
	
	/* Method to get an offer from a username  */
	public Offer getOffer(String username) {
		
		if(username == null)
		{
			return null;
		}
		List<Offer> offers = offersDao.getOffers(username);
		
		
		if(offers.size()== 0)
		{
			return null;
		}
		
		return offers.get(0);
	}
	public void saveOrUpdate(Offer offer) {
		if(offer.getId()!=0)
		{
			System.out.println(offer.getId());
			System.out.println("when offer id is not zero");
			offersDao.update(offer);
		}
		else {
			System.out.println("When offer ID is zero");
			offersDao.create(offer);
		}
	}
	public void delete(int id) {
		offersDao.delete(id);
	}
}
