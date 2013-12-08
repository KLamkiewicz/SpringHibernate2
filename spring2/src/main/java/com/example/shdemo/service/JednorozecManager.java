package com.example.shdemo.service;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Jednorozec;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Samochod;

@Component
@Transactional
public class JednorozecManager implements JednorozecManagerIn{

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addJednorozec(Jednorozec jednorozec) {
		jednorozec.setId(null);
		sessionFactory.getCurrentSession().persist(jednorozec);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Jednorozec> getAllJednorozce() {
		return sessionFactory.getCurrentSession().getNamedQuery("jednorozec.getAll").list();
	}

	@Override
	public void deleteJednorozec(Jednorozec jednorozec) {
		jednorozec = (Jednorozec) sessionFactory.getCurrentSession().get(Jednorozec.class, jednorozec.getId());
		sessionFactory.getCurrentSession().delete(jednorozec);
		
	}

	@Override
	public Jednorozec getJednorozecById(long id) {
		return (Jednorozec) sessionFactory.getCurrentSession().getNamedQuery("jednorozec.byId").setLong("id", id).uniqueResult();
	}

	@Override
	public void updateJednorozec(long id, String imie) {
		Jednorozec j = new Jednorozec();
		 j = (Jednorozec) sessionFactory.getCurrentSession().getNamedQuery("jednorozec.byId").setLong("id", id).uniqueResult();
		 j.setImieJednorozca(imie);
	}

	@Override
	public void deleteJednorozecById(long id) {
		Jednorozec j = new Jednorozec();
		j = (Jednorozec) sessionFactory.getCurrentSession().get(Jednorozec.class, id);
		sessionFactory.getCurrentSession().delete(j);
	}

	@Override
	public List<Samochod> getAllSamochody() {
		return sessionFactory.getCurrentSession().getNamedQuery("samochod.getAll").list();
	}

	@Override
	public void addSamochod(Samochod samochod) {
		samochod.setId(null);
		sessionFactory.getCurrentSession().persist(samochod);
	}

	@Override
	public void deleteSamochodById(Long id) {
		Samochod s = new Samochod();
		s = (Samochod) sessionFactory.getCurrentSession().get(Samochod.class, id);
		sessionFactory.getCurrentSession().delete(s);
	}

	@Override
	public Samochod getSamochodById(Long id) {
		return (Samochod) sessionFactory.getCurrentSession().getNamedQuery("samochod.byId").setLong("id", id).uniqueResult();
	}

	@Override
	public void updateSamochod(Long id, String nowaNazwa) {
		 Samochod j = new Samochod();
		 j = (Samochod) sessionFactory.getCurrentSession().getNamedQuery("samochod.byId").setLong("id", id).uniqueResult();
		 j.setNazwa(nowaNazwa);;
		
	}

	@Override
	public Samochod getSamochodByNazwa(String nazwa) {
		Samochod s = new Samochod();
		List<Samochod> lS = new ArrayList<Samochod>();
		lS = sessionFactory.getCurrentSession().getNamedQuery("samochod.getAll").list();
		
		for(Samochod x : lS){
			if(x.getNazwa().equals(nazwa)){
				return x;
			}
		}
		return null;
	}

	@Override
	public void addSamochodToJednorozec(Long jId, Long sId) {
		Jednorozec j = new Jednorozec();
		Samochod s = new Samochod();
		j = (Jednorozec) sessionFactory.getCurrentSession().getNamedQuery("jednorozec.byId").setLong("id", jId).uniqueResult();
		s = (Samochod) sessionFactory.getCurrentSession().getNamedQuery("samochod.byId").setLong("id", sId).uniqueResult();
		j.getListaSamochodow().add(s);
	}

	@Override
	public List<Samochod> getAllSamochodyFromJednorozec(Long id) {
		List<Samochod> ls = new ArrayList<Samochod>();
		Jednorozec j = new Jednorozec();
		j = (Jednorozec) sessionFactory.getCurrentSession().getNamedQuery("jednorozec.byId").setLong("id", id).uniqueResult();
		ls = j.getListaSamochodow();
		return ls;
	}

	@Override
	public void deleteSamochodFromJednorozec(Long jId, Long sId) {
		Jednorozec j = new Jednorozec();
		Samochod s = new Samochod();
		j = (Jednorozec) sessionFactory.getCurrentSession().getNamedQuery("jednorozec.byId").setLong("id", jId).uniqueResult();
		s = (Samochod) sessionFactory.getCurrentSession().getNamedQuery("samochod.byId").setLong("id", sId).uniqueResult();
		int counter = 0;
		for(Samochod l : j.getListaSamochodow()){
			if(l.getId()==sId){
				j.getListaSamochodow().remove(l);
			}
			counter++;
		}
	}

	
}
