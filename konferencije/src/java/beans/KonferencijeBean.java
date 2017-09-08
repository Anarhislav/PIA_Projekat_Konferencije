/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Funkcije;
import db.Galerija;
import db.Komentar;
import db.Konferencija;
import db.Korisnik;
import db.Predavanje;
import db.Prijava;
import db.PrijavaPredavanje;
import db.Sesija;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Drakulic
 */
@ManagedBean
@SessionScoped
public class KonferencijeBean implements Serializable{
    
    Konferencija konferencija;
    List<Sesija> sesije;
    List<Predavanje> predavanja;
    List<String> slike;
    List<Komentar> komentari;
    List<Korisnik> posetici;
    Sesija s;
    Predavanje predavanje=null;
    String komentar;
    int ocena;

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    
    
  
    public Predavanje getPredavanje() {
        return predavanje;
    }

    public void setPredavanje(Predavanje predavanje) {
        this.predavanje = predavanje;
    }
    

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public List<Korisnik> getPosetici() {
        return posetici;
    }

    public void setPosetici(List<Korisnik> posetici) {
        this.posetici = posetici;
    }

    
    
    
    
    
    public List<String> getSlike() {
        return slike;
    }

    public void setSlike(List<String> slike) {
        this.slike = slike;
    }

    public Sesija getS() {
        return s;
    }

    public void setS(Sesija s) {
        this.s = s;
    }
    
    
    
    public List<Sesija> getSesije() {
        return sesije;
    }

    public void setSesije(List<Sesija> sesije) {
        this.sesije = sesije;
    }

    public List<Predavanje> getPredavanja() {
        return predavanja;
    }

    public void setPredavanja(List<Predavanje> predavanja) {
        this.predavanja = predavanja;
    }
    
    
    public void getSesija(Sesija s){
        
    }
    
    
    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }
    
    public void findSesijas(Konferencija k){
        sesije=new ArrayList();
        sesije.addAll(k.getSesijas());
        Collections.sort(sesije, new Comparator<Sesija>() {
        public int compare(Sesija s1, Sesija s2) {
         if (s1.getDatum() == null || s1.getDatum()  == null)
        return 0;
        return s1.getDatum().compareTo(s2.getDatum());
             }
            });
        
        System.out.println(sesije);
   
    }
    
    public String dodajUAgendu(Korisnik k){
        System.out.println(k.getUsername());
        
        boolean dodato=false;
            List<PrijavaPredavanje> svePrijave=new ArrayList();
            
            svePrijave.addAll(k.getPrijavaPredavanjes());
            for(PrijavaPredavanje p:svePrijave){
                if(p.getSesija().getIdsesija()==s.getIdsesija()){
                    dodato=true;
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "ovo je vec dodato u agendu")); 
                }
            }
            
            
        if(!dodato){
        PrijavaPredavanje prijava=new PrijavaPredavanje(k, s);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "dodato u agendu"));
        Funkcije f=new Funkcije();
        f.savePrijavaPredavanja(prijava);
        }
        return "";
    }
    
    public void komentarisi(Korisnik korisnik){
        Komentar k=new Komentar(korisnik, predavanje, komentar, ocena);
        if(komentari==null) komentari=new ArrayList();
        komentari.add(k);
        predavanje.getKomentars().add(k);
        Funkcije f=new Funkcije();
        f.saveKomentar(k);
         komentari=new ArrayList();
        komentari.addAll(predavanje.getKomentars());
    }
    
    
    public boolean imaLiPredavanje(){
        if(predavanje==null)return false;
        return true;
    }
    
    public String deteljnoSesija(Sesija s){
        this.s=s;
        List<Galerija> g=new ArrayList();
        slike=new ArrayList();
        g.addAll(s.getGalerijas());
        for(Galerija gal:g){
            slike.add(gal.getSlika());
        }
        
        
        predavanja=new ArrayList();
        predavanja.addAll(s.getPredavanjes());
        System.out.println("detalji sesije");
        return "detaljiSesija";
    }
    
    public void seePredavanje(Predavanje p){
        predavanje=p;
        komentari=new ArrayList();
        
        komentari.addAll(predavanje.getKomentars());
        
        System.out.println("KOMENTARI SU"+komentari);
        
    }
    
    public String pregledPostioca(Konferencija konf){
        konferencija=konf;
        
        posetici=new ArrayList();
        List<Prijava> p =new ArrayList();
        p.addAll(konf.getPrijavas());
        
        for(Prijava pr:p){
            posetici.add(pr.getKorisnik());
        }
        return "pregledPosetioca";
    }
    
    
    
}
