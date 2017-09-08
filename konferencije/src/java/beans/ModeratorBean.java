/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Funkcije;
import db.Konferencija;
import db.Korisnik;
import db.Predavanje;
import db.Sala;
import db.Sesija;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Drakulic
 */
@ManagedBean
@SessionScoped
public class ModeratorBean implements Serializable {
    
    
    Korisnik korisnik;
    Konferencija konferencija;
    List<Konferencija> konferencije;
    Sesija sesija;
    List<Sesija> sesije;
    List<Predavanje> predavanja;
    List<String> sale;
    String sala;
    List<String> korisnici;
    String predavac;
    
    String naslov;
    String tip;
    int trajanje;
    Date pocetak;

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }
    
    
    
    
    
    public List<String> getSale() {
        return sale;
    }

    public void setSale(List<String> sale) {
        this.sale = sale;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public List<String> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<String> korisnici) {
        this.korisnici = korisnici;
    }

    public String getPredavac() {
        return predavac;
    }

    public void setPredavac(String predavac) {
        this.predavac = predavac;
    }
    
    
    public List<Predavanje> getPredavanja() {
        return predavanja;
    }

    public void setPredavanja(List<Predavanje> predavanja) {
        this.predavanja = predavanja;
    }
    
    

    public List<Sesija> getSesije() {
        return sesije;
    }

    public void setSesije(List<Sesija> sesije) {
        this.sesije = sesije;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }
    
    
    
    public Sesija getSesija() {
        return sesija;
    }

    public void setSesija(Sesija sesija) {
        this.sesija = sesija;
    }
    
    

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(List<Konferencija> konferencije) {
        this.konferencije = konferencije;
    }
    
    
    
    public void handleFileUpload(FileUploadEvent event) {
     
     UploadedFile uploadedFile = (UploadedFile)event.getFile();

    //create an InputStream from the uploaded file
    InputStream inputStr = null;
    try {
        inputStr = uploadedFile.getInputstream();
    } catch (IOException e) {
         e.printStackTrace();
    }
   double mark=Math.random()*1000000;
    //create destination File
     ServletContext contex=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        String putanja=contex.getRealPath("/");
        int poz=putanja.lastIndexOf("konferencije");
        putanja=putanja.substring(0, poz);
      
    String destPath = putanja+"konferencije\\web\\resources\\slike\\konferencije\\"+mark+uploadedFile.getFileName();
    System.out.println(destPath.toString());
    File destFile = new File(destPath);
    Funkcije f=new Funkcije();
    System.out.println(sesija.getIdsesija());
    f.saveSlika(sesija,mark+uploadedFile.getFileName());
    //use org.apache.commons.io.FileUtils to copy the File
    try {                    
        FileUtils.copyInputStreamToFile(inputStr, destFile);
    } catch (IOException e) {
        //log error
         e.printStackTrace();
    }
     
    }
    
    
    public String moderator(Korisnik k){
        korisnik=k;
        konferencije=new ArrayList();
        konferencije.addAll(k.getKonferencijas());
        return "moderator";
    }
    
    public void findSesija(Konferencija k){
        konferencija=k;
        sesije=new ArrayList();
        
        sesije.addAll(k.getSesijas());
        Collections.sort(sesije, new Comparator<Sesija>() {
        public int compare(Sesija s1, Sesija s2) {
         if (s1.getDatum() == null || s1.getDatum()  == null)
        return 0;
        return s1.getDatum().compareTo(s2.getDatum());
             }
            });
        
    }
    
    public void staviSalu(){
        Funkcije f=new Funkcije();
        Sala s=f.getSala(sala);
        sesija.setSala(s);
        f.saveSesija(sesija);
    }
    
    
    public String uredi(Sesija s){
        sesija=s;
        Funkcije f=new Funkcije();
        korisnici=new ArrayList();
        List<Korisnik>k=f.getAllKorisnici();
        for(Korisnik ko:k){
            korisnici.add(ko.getUsername());
        }
        sale=new ArrayList();
        List<Sala>sal=f.getSale(konferencija.getMesto());
        for(Sala sa:sal){
            sale.add(sa.getNaziv());
        }
        predavanja=new ArrayList();
        predavanja.addAll(s.getPredavanjes());
        Collections.sort(predavanja, new Comparator<Predavanje>() {
        public int compare(Predavanje s1, Predavanje s2) {
         if (s1.getPocetak() == null || s1.getPocetak()  == null)
        return 0;
        return s1.getPocetak().compareTo(s2.getPocetak());
             }
            });
          System.out.println(sale);
        return "urediSesiju";
        
    }
    
    public void dodajPredavanje(){
        Funkcije f=new Funkcije();
        Korisnik p=f.getKorisnik(predavac);
        if(p==null)
        { p=new Korisnik(predavac, "1", "korisnik");
        p.setSlika("muski.png");
        f.saveKorisnik(p);}
        Predavanje predavanje=new Predavanje(p, sesija, naslov, tip, pocetak);
        predavanje.setTrajanje(trajanje);
        f.savePredavanje(predavanje);
        predavanja.add(predavanje);
        
    }
}
