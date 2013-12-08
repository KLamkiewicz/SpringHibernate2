package com.example.shdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Jednorozec;
import com.example.shdemo.domain.Samochod;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class JednorozecManagerTest {
	
	@Autowired
	JednorozecManagerIn jednorozecManager;
	
	private final String IMIE_JEDNOROZCA1 = "Jarek";
	private final int rokUrodzeniaJednorozca1 = 1990;
	private final int wagaJednorozca1 = 400;
	
	private final String imieJednorozca2 = "Jakub";
	private final int rokUrodzeniaJednorozca2 = 1990;
	private final int wagaJednorozca2 = 300;
	
	private final String imieJednorozca3 = "Michal";
	private final int rokUrodzeniaJednorozca3 = 1990;
	private final int wagaJednorozca3 = 5376;
	
	private final String nazwa1 = "Fiat";
	private final int rokProdukcji1 = 1990;
	
	private final String nazwa2 = "Volvo";
	private final int rokProdukcji2 = 1966;
	
	private final String nazwa3 = "Bugatti";
	private final int rokProdukcji3 = 2008;
	
	
	public void add(){
		Jednorozec jednorozec = new Jednorozec();
		jednorozec.setImieJednorozca(IMIE_JEDNOROZCA1);
		jednorozec.setRokUrodzeniaJednorozca(rokUrodzeniaJednorozca1);
		jednorozec.setWagaJednorozca(wagaJednorozca1);
		
		Jednorozec jednorozec2 = new Jednorozec();
		jednorozec2.setImieJednorozca(imieJednorozca2);
		jednorozec2.setRokUrodzeniaJednorozca(rokUrodzeniaJednorozca2);
		jednorozec2.setWagaJednorozca(wagaJednorozca2);
		
		Jednorozec jednorozec3 = new Jednorozec();
		jednorozec3.setImieJednorozca(imieJednorozca3);
		jednorozec3.setRokUrodzeniaJednorozca(rokUrodzeniaJednorozca3);
		jednorozec3.setWagaJednorozca(wagaJednorozca3);
		
		jednorozecManager.addJednorozec(jednorozec);
		jednorozecManager.addJednorozec(jednorozec2);
		jednorozecManager.addJednorozec(jednorozec3);
	}
	
	public void addS(){
		Samochod s1 = new Samochod();
		s1.setNazwa(nazwa1);
		s1.setRokProdukcji(rokProdukcji1);
		
		Samochod s2 = new Samochod();
		s2.setNazwa(nazwa2);
		s2.setRokProdukcji(rokProdukcji2);
		
		Samochod s3 = new Samochod();
		s3.setNazwa(nazwa3);
		s3.setRokProdukcji(rokProdukcji3);
		
		jednorozecManager.addSamochod(s1);
		jednorozecManager.addSamochod(s2);
		jednorozecManager.addSamochod(s3);
	}
	
	@Test
	public void getAllJednorozce(){
		int size = jednorozecManager.getAllJednorozce().size();
		add();
		assertEquals(size + 3, jednorozecManager.getAllJednorozce().size());
	}

	@Test
	public void addJednorozec(){
		int size = jednorozecManager.getAllJednorozce().size();
		String imie = "Rafal";
		int rokU = 1999;
		int waga = 20000;
	
		Jednorozec jednorozec = new Jednorozec();
		jednorozec.setImieJednorozca(imie);
		jednorozec.setRokUrodzeniaJednorozca(rokU);
		jednorozec.setWagaJednorozca(waga);
		jednorozecManager.addJednorozec(jednorozec);
		assertEquals(size+1, jednorozecManager.getAllJednorozce().size());
		assertEquals(imie, jednorozecManager.getAllJednorozce().get(size).getImieJednorozca());
		assertEquals(rokU, jednorozecManager.getAllJednorozce().get(size).getRokUrodzeniaJednorozca());
		assertEquals(waga, jednorozecManager.getAllJednorozce().get(size).getWagaJednorozca());
	}
	
	@Test
	public void deleteJednorozec(){
		add();
		int size = jednorozecManager.getAllJednorozce().size();
		Long id3 = jednorozecManager.getAllJednorozce().get(size-1).getId();
		Long id2 = jednorozecManager.getAllJednorozce().get(size-2).getId();
		Long id1 = jednorozecManager.getAllJednorozce().get(size-3).getId();
		
		jednorozecManager.deleteJednorozecById(id2);
		assertEquals(size-1, jednorozecManager.getAllJednorozce().size());
		assertNull(jednorozecManager.getJednorozecById(id2));
		assertNotNull(jednorozecManager.getJednorozecById(id3));
	}

	@Test
	public void getJednorozecById(){
		add();
		int size = jednorozecManager.getAllJednorozce().size();
		Long id3 = jednorozecManager.getAllJednorozce().get(size-1).getId();
		Long id2 = jednorozecManager.getAllJednorozce().get(size-2).getId();
		Long id1 = jednorozecManager.getAllJednorozce().get(size-3).getId();
		String imie = "Rafal";
		int rokU = 1999;
		int waga = 20000;
	
		Jednorozec jednorozec = new Jednorozec();
		jednorozec.setImieJednorozca(imie);
		jednorozec.setRokUrodzeniaJednorozca(rokU);
		jednorozec.setWagaJednorozca(waga);
		jednorozecManager.addJednorozec(jednorozec);
		assertEquals(jednorozec.getId(), jednorozecManager.getJednorozecById(jednorozec.getId()).getId());
	}
	
	@Test
	public void updateJednorozec(){
		add();
		int size = jednorozecManager.getAllJednorozce().size();
		Jednorozec j = new Jednorozec();
		j = jednorozecManager.getAllJednorozce().get(size-1);
		String change = "Changed";
		jednorozecManager.updateJednorozec(j.getId(), change);
		assertEquals(change, jednorozecManager.getJednorozecById(j.getId()).getImieJednorozca());
		
	}

	@Test
	public void getAllSamochody(){
		int size = jednorozecManager.getAllSamochody().size();
		addS();
		assertEquals(size+3, jednorozecManager.getAllSamochody().size());
	}
	
	@Test
	public void addSamochod(){
		int size = jednorozecManager.getAllSamochody().size();
		Samochod nowyS = new Samochod();
		nowyS.setNazwa("Panda");
		nowyS.setRokProdukcji(1990);
		jednorozecManager.addSamochod(nowyS);
		assertEquals(size+1, jednorozecManager.getAllSamochody().size());
	}
	
	@Test
	public void deleteSamochod(){
		addS();
		int size = jednorozecManager.getAllSamochody().size();
		Long id3 = jednorozecManager.getAllSamochody().get(size-1).getId();
		Long id2 = jednorozecManager.getAllSamochody().get(size-2).getId();
		Long id1 = jednorozecManager.getAllSamochody().get(size-3).getId();
		
		jednorozecManager.deleteSamochodById(id2);
		assertEquals(size-1, jednorozecManager.getAllSamochody().size());
		assertNull(jednorozecManager.getSamochodById(id2));
		assertNotNull(jednorozecManager.getSamochodById(id3));
	}
	
	@Test
	public void getSamochodById(){
		addS();
		int size = jednorozecManager.getAllSamochody().size();
		Samochod s = new Samochod();
		s.setNazwa("TEST");
		s.setRokProdukcji(2011);
		jednorozecManager.addSamochod(s);
		Long sId = s.getId();
		
		assertEquals(sId, jednorozecManager.getSamochodById(sId).getId());
	}
	
	@Test
	public void updateSamochod(){
		Samochod s = new Samochod();
		s.setNazwa(nazwa1);
		s.setRokProdukcji(1990);
		String nowaNazwa = "Nowa";
		jednorozecManager.addSamochod(s);
		Long id = s.getId();
		
		jednorozecManager.updateSamochod(id, nowaNazwa);
		assertEquals(nowaNazwa, jednorozecManager.getSamochodById(id).getNazwa());
	}
	
	@Test
	public void getSamochodByNazwa(){
		addS();
		Samochod s = new Samochod();
		s.setNazwa(nazwa1);
		s.setRokProdukcji(1990);
		
		assertEquals(s.getNazwa(), jednorozecManager.getSamochodByNazwa(s.getNazwa()).getNazwa());
	}
	
	@Test
	public void addSamochodToJednorozec(){
		Jednorozec j = new Jednorozec();
		j.setImieJednorozca("adam");
		j.setRokUrodzeniaJednorozca(1990);
		j.setWagaJednorozca(200);
		Samochod s = new Samochod();
		s.setNazwa("Fiat");
		s.setRokProdukcji(1990);
		jednorozecManager.addJednorozec(j);
		int listSize = jednorozecManager.getJednorozecById(j.getId()).getListaSamochodow().size();
		jednorozecManager.addSamochod(s);
		jednorozecManager.addSamochodToJednorozec(j.getId(), s.getId());
		
		assertEquals(s.getId(), jednorozecManager.getJednorozecById(j.getId()).getListaSamochodow().get(listSize).getId());
	}
	
	public void getAllSamochodyFromJednorozec(){
		add();
		addS();
		int size = jednorozecManager.getAllJednorozce().size();
		int sizeS = jednorozecManager.getAllSamochody().size();
		Long j1id = jednorozecManager.getAllJednorozce().get(size-1).getId();
		Long j2id = jednorozecManager.getAllJednorozce().get(size-2).getId();
		
		Long s1id = jednorozecManager.getAllSamochody().get(sizeS-1).getId();
		Long s2id = jednorozecManager.getAllSamochody().get(sizeS-2).getId();
		Long s3id = jednorozecManager.getAllSamochody().get(sizeS-3).getId();
		
		int iloscSamochodow = jednorozecManager.getAllSamochodyFromJednorozec(j1id).size();
		
		jednorozecManager.addSamochodToJednorozec(j1id, s1id);
		jednorozecManager.addSamochodToJednorozec(j1id, s2id);
		jednorozecManager.addSamochodToJednorozec(j1id, s3id);
		
		assertEquals(iloscSamochodow+3, jednorozecManager.getAllSamochodyFromJednorozec(j1id).size());
	}
	
	public void deleteSamochodFromJednorozec(){
		add();
		addS();
		int size = jednorozecManager.getAllJednorozce().size();
		int sizeS = jednorozecManager.getAllSamochody().size();
		Long j1id = jednorozecManager.getAllJednorozce().get(size-1).getId();
		Long j2id = jednorozecManager.getAllJednorozce().get(size-2).getId();
		
		Long s1id = jednorozecManager.getAllSamochody().get(sizeS-1).getId();
		Long s2id = jednorozecManager.getAllSamochody().get(sizeS-2).getId();
		Long s3id = jednorozecManager.getAllSamochody().get(sizeS-3).getId();
		
		jednorozecManager.addSamochodToJednorozec(j1id, s1id);
		jednorozecManager.addSamochodToJednorozec(j1id, s2id);
		jednorozecManager.addSamochodToJednorozec(j1id, s3id);
		
		int iloscSamochodow = jednorozecManager.getAllSamochodyFromJednorozec(j1id).size();
		
		jednorozecManager.deleteSamochodFromJednorozec(j1id, s2id );
		assertEquals(iloscSamochodow - 1, jednorozecManager.getAllSamochodyFromJednorozec(j1id).size());
	}
	
	
}
