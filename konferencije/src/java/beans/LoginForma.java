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
import db.Prijava;
import db.PrijavaPredavanje;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Drakulic
 */
@ManagedBean(name = "loginForma")
@SessionScoped
public class LoginForma implements Serializable {
    private boolean daLiJeAdmin=false;
    private Funkcije helper;
    private String username = "";
    private String password = "";
    private Korisnik korisnik;
    private String password1;
    private String password2;
    private String oldPassword;
    private List<Konferencija> konferencije;
    private List<Predavanje> predavanja;
    private List<Korisnik> korisnici;
    private Konferencija konferencija;
    private String traziKorisnika;

    public String getTraziKorisnika() {
        return traziKorisnika;
    }

    public void setTraziKorisnika(String traziKorisnika) {
        this.traziKorisnika = traziKorisnika;
    }
    
    
    
    
    
    
    
    public boolean isDaLiJeAdmin() {
        return daLiJeAdmin;
    }

    public void setDaLiJeAdmin(boolean daLiJeAdmin) {
        this.daLiJeAdmin = daLiJeAdmin;
    }
    
    
    public void nadjiKorisnika(){
        
        Funkcije f=new Funkcije();
        if(traziKorisnika==null) traziKorisnika="";
        korisnici=f.getKorisnici(traziKorisnika);
        
        
    }
    
    
    
    
    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }
    private String sifraKonf;

    public String getSifraKonf() {
        return sifraKonf;
    }

    public void setSifraKonf(String sifraKonf) {
        this.sifraKonf = sifraKonf;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public List<Predavanje> getPredavanja() {
        return predavanja;
    }

    public void setPredavanja(List<Predavanje> predavanja) {
        this.predavanja = predavanja;
    }

    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(List<Konferencija> konferencije) {
        this.konferencije = konferencije;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getUsername() {
        return username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public LoginForma() {

        username = "";
        password = "";
        helper = new Funkcije();

    }

    public String mojaAgenda() {
        predavanja = new ArrayList();
        Funkcije f=new Funkcije();
        korisnik=f.getKorisnik(korisnik.getUsername());
        List<PrijavaPredavanje> pp = new ArrayList();
        pp.addAll(korisnik.getPrijavaPredavanjes());

        for (PrijavaPredavanje p : pp) {

            predavanja.addAll(p.getSesija().getPredavanjes());
        }
        System.out.println(predavanja);

        return "mojaAgenda";
    }
    
    public boolean daLiJeAdmin(){
        return korisnik.getTip().equals("admin");
    }
    
    
    public boolean daLiJeModerator() {
        if (korisnik.getTip().equals("moderator")) {
            return true;
        } else {
            return false;
        }

    }
    
    
    
    
    public String login() {

        ServletContext contex = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        System.out.println(contex.getRealPath("/"));
        String adresa = "korisnik";
        korisnik = helper.getKorisnik(username);

        if (korisnik == null) {
            adresa = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "ne postoji korisnik sa tim korisnickim imenom"));
            username = "";
        } else {

            if (korisnik.getTip().equals("admin")) {
                adresa = "admin";
                daLiJeAdmin=true;
            }

            System.out.println(korisnik.getUsername());
            if (!korisnik.getLozinka().equals(password)) {
                adresa = null;
                korisnik = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "pogresna sifra"));
            }
        }
        if (adresa != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "dobro dosli" + korisnik.getIme()));
        }
        return adresa;
    }

    public String startRegister() {
        return "registerForm";
    }

    public String changePassword() {
        String adresa = "korisnik";
        System.out.println(oldPassword);
        System.out.println(korisnik.getLozinka());
        if (korisnik.getTip().equals("admin")) {
            adresa = "admin";
        }
        if (korisnik.getLozinka().equals(oldPassword)) {
            if (password1.equals(password2)) {

                korisnik.setLozinka(password1);
                helper.saveKorisnik(korisnik);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "uspesno ste promenili sifru"));
                return adresa;
            } else {
                System.out.println("sifra mora da bude ista");
                adresa = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "sifra mora da bude ista"));
            }
        } else {
            System.out.println("nije dobra stara sifra");
            adresa = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "nije dobra stara sifra"));
        }

        return adresa;

    }

    public boolean imaLiKorisnika() {
        if (korisnik == null) {
            return false;
        } else {
            return true;
        }
    }


    public String naPrijavu(Konferencija k) {
        konferencija = k;
        
        Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, -3);
            dt = c.getTime();
            System.out.println(dt);
            boolean prijavljen=false;
            List<Prijava> svePrijaveKonferencije=new ArrayList();
            
            svePrijaveKonferencije.addAll(korisnik.getPrijavas());
            System.out.println(svePrijaveKonferencije);
            for(Prijava prijava:svePrijaveKonferencije){
                if(prijava.getKonferencija().getNaziv().equals(konferencija.getNaziv())){
                    
                    prijavljen=true;break;
                }
                 
            }
            
            
            if(konferencija.getDatumDo().after(dt)){
            
            if (!prijavljen) {
                
                return "prijava";

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "korisnik je vec prijavljen"));
            }}else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "datum za prijavu je istekao"));
            }
           
            
        
          return "";
    }

    public String prijava() {
        helper = new Funkcije();
        if (korisnik == null) {
            return "registerForm";
        } else {

            if (konferencija.getSifra().equals(sifraKonf)) {
                Prijava prijava = new Prijava(konferencija, korisnik);
                korisnik.getPrijavas().add(prijava);
                helper.snimiPrijava(prijava);
            } else {

                System.out.println("pogresna pristupna sifra");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "pogresna pristupna sifra"));
            }

            return "korisnik";

        }

    }

    public String logOut() {
        username = "";
        password = "";
        korisnik = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "pocetna";
    }

    public String mojeKonferencije() {
        List<Prijava> p = new ArrayList();
        konferencije = new ArrayList();
        p.addAll(korisnik.getPrijavas());
        System.out.println(konferencije);
        for (Prijava pr : p) {
            konferencije.add(pr.getKonferencija());
            System.out.println(pr.getKonferencija().getNaziv());
        }
        return "mojeKonferencije";
    }

    public String mojePoruke() {
        Funkcije f = new Funkcije();
        korisnici = f.getAllKorisnici();

        return "poruke";
    }

    public String promeniSifru() {
        return "promenaSifre";
    }

    public String goHome() {
        return "korisnik";
    }
}
