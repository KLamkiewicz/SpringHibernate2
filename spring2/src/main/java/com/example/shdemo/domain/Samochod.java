package com.example.shdemo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "samochod.getAll", query = "Select s from Samochod s"),
	@NamedQuery(name = "samochod.byId", query = "Select s from Samochod s where s.id = :id")
})
public class Samochod {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nazwa;
	private int rokProdukcji;
	
	public Samochod(){
		
	}
	
	public Samochod(String nazwa, int rokProdukcji){
		this.nazwa = nazwa;
		this.rokProdukcji = rokProdukcji;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getRokProdukcji() {
		return rokProdukcji;
	}

	public void setRokProdukcji(int rokProdukcji) {
		this.rokProdukcji = rokProdukcji;
	}
	
	
}