/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Funkcije;
import db.Konferencija;
import db.Korisnik;
import db.Oblast;
import db.Poruka;
import db.Prijava;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Drakulic
 */
@ManagedBean
@SessionScoped
public class Filter implements Serializable{
    private Funkcije helper;
      private List<Konferencija> konferencije;
    private List<String> sveKonferencije;
    private Date datumOd;
    private Date datumDo;
    private String naziv;
    private List<Oblast> oblasti;
    private List<String> izabraneOblasti;
    private List<Oblast> iOblasti;
    public List<String> getIzabraneOblasti() {
        return izabraneOblasti;
    }

    public void setIzabraneOblasti(List<String> izabraneOblasti) {
        this.izabraneOblasti = izabraneOblasti;
    }
    
    
    
    public String otkazi(Korisnik korisnik,Konferencija konf){
        
        System.out.println("otkazi");
        try{
        konferencije.remove(konf);
        Funkcije helper=new Funkcije();
        helper.deleteKonferencija(konf);
        List<Prijava> prijave=new ArrayList();
        if(konf.getPrijavas()!=null)
        if(konf.getPrijavas().size()>0){
        prijave.addAll(konf.getPrijavas());
        
        for(Prijava p:prijave){
            Poruka otkazivanje=new Poruka(korisnik, p.getKorisnik(), new Date(), konf.getNaziv()+" se otkazuje");
            helper.savePoruka(otkazivanje);
        }
        }}catch(Exception e){
            e.printStackTrace();
               
        }
        return "";
    }
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    
    
    
    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(List<Konferencija> konferencije) {
        this.konferencije = konferencije;
    }

    public List<String> getSveKonferencije() {
        return sveKonferencije;
    }

    public void setSveKonferencije(List<String> sveKonferencije) {
        this.sveKonferencije = sveKonferencije;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public List<Oblast> getOblasti() {
        return oblasti;
    }

    public void setOblasti(List<Oblast> oblasti) {
        this.oblasti = oblasti;
    }
    
    
    public Filter(){
        oblasti=new ArrayList();
        sveKonferencije=new ArrayList();
        helper=new Funkcije();
         
         konferencije=helper.getAllKonferencije();
         oblasti=helper.getAllOblasti();
        for(Konferencija k:konferencije){
            
            sveKonferencije.add(k.getNaziv());  
            System.out.println(k.getNaziv());
        }
         
    }
    
     public List<String> autoComplit(String query){
         System.out.println(query);
        List<String> rezult=new ArrayList();
        for(String k:sveKonferencije){
            if(k.startsWith(query)){
                rezult.add(k);
                System.out.println(k);
            }
        }
        return rezult;
    }
     
    public String findLogo(Konferencija konf){
        
        switch(konf.getOblast().getIdoblast()){
        
            case 2: return "elektrotehnika";
            case 3: return "arhitektura";
            case 4: return "gradjevina";
            case 5: return "masinstvo";
            case 6: return "medicina";
            case 7: return "hemija";
            case 8: return "biologija";
            case 9: return "zastita";
   
    }
       return ""; 
    } 
    public void sveKonferencije(){
        helper=new Funkcije();
        konferencije=helper.getAllKonferencije();
    }
    public void filtriraj(){
       
        helper=new Funkcije();
        
        konferencije=helper.filterKonferencije(naziv,datumOd,datumDo,izabraneOblasti);
    }
    
}


