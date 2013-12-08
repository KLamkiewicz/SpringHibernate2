package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Jednorozec;
import com.example.shdemo.domain.Samochod;

public interface JednorozecManagerIn {

	void addJednorozec(Jednorozec jednorozec);
	List<Jednorozec> getAllJednorozce();
	void deleteJednorozec(Jednorozec jednorozec);
	void deleteJednorozecById(long id);
	void updateJednorozec(long id, String imie);
	Jednorozec getJednorozecById(long id);
	//
	List<Samochod> getAllSamochody();
	void addSamochod(Samochod samochod);
	void deleteSamochodById(Long id);
	Samochod getSamochodById(Long id);
	void updateSamochod(Long id, String nowaNazwa);
	Samochod getSamochodByNazwa(String nazwa);
	
	void addSamochodToJednorozec(Long jId, Long sId);
	List<Samochod> getAllSamochodyFromJednorozec(Long id);
	void deleteSamochodFromJednorozec(Long jId, Long sId);
}
