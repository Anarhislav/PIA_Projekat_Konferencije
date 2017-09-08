/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Funkcije;
import db.Konferencija;
import db.Korisnik;
import db.Mesto;
import db.Oblast;
import db.Sesija;
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
public class NovaKonferencija implements Serializable{
    
    String naziv;
    Date datumOd;
    Date datumDo;
    String mesto;
    String oblast;
    String moderator;
String sifra;
    List<String> mesta;
    List<String> oblasti;
    List<String> korisnici;
    
    public List<String> getMesta() {
        return mesta;
    }

    public void setMesta(List<String> mesta) {
        this.mesta = mesta;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public List<String> getOblasti() {
        return oblasti;
    }

    public void setOblasti(List<String> oblasti) {
        this.oblasti = oblasti;
    }

    public List<String> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<String> korisnici) {
        this.korisnici = korisnici;
    }
    
    
    
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }
    
    
    
    
    
    public String novaKonferencija(){
        Funkcije f=new Funkcije();
        mesta=new ArrayList<>();
        oblasti=new ArrayList<>();
        korisnici=new ArrayList<>();
        
        List<Mesto> m=f.getAllMesta();
        List<Oblast> o=f.getAllOblasti();
        
        List<Korisnik> k=f.getOnlyKorisnici();
        for(Korisnik ko:k){
            korisnici.add(ko.getUsername());
        }
        for(Oblast ob:o){
            oblasti.add(ob.getOblast());
        }
        
        for(Mesto me:m){
            
            mesta.add(me.getLokacija());
        }

        System.out.println("koricnici:"+korisnici);
        return "novaKonferencija";
    }
    
   
    public String dodajKonferencija(){
       
       Funkcije f=new Funkcije();
    Korisnik korisnik=f.getKorisnik(moderator);
    Oblast o=f.getOblast(oblast);
    Mesto m=f.getMesto(mesto);
    
    Konferencija konf=new Konferencija(korisnik, m,o,sifra);
    konf.setNaziv(naziv);
    konf.setDatumOd(datumOd);
    konf.setDatumDo(datumDo);
    korisnik.setTip("moderator");
    Date datumTemp=datumOd;
    f.saveKorisnik(korisnik);
    f.saveKonferencija(konf);
    while(datumTemp.before(datumDo)||datumTemp.equals(datumDo)){
        
        Sesija s=new Sesija(konf);
        s.setDatum(datumTemp);
        f.saveSesija(s);
        datumTemp.setTime(datumTemp.getTime()+24*60*60*1000);
        
    }
    
    
       return "admin";
   }
}
