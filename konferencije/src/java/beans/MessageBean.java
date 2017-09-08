/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Funkcije;
import db.Korisnik;
import db.Poruka;
import db.Sesija;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class MessageBean implements Serializable {
    
    Korisnik sentTo;
    String text;
    List<Poruka> poruke;
    Korisnik ja;
    boolean novaPoruke=false;
    public Korisnik getSentTo() {
        return sentTo;
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public boolean isNovaPoruke() {
        return novaPoruke;
    }

    public void setNovaPoruke(boolean novaPoruke) {
        this.novaPoruke = novaPoruke;
    }

 
    public void setSentTo(Korisnik sentTo) {
        this.sentTo = sentTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    public void increment() {
       
        Funkcije f=new Funkcije();
        poruke=f.getDopisivanjeZa(ja,sentTo);
    }
    
    
    public void novaPoruka(Korisnik k,Korisnik ja){
        this.ja=ja;
         System.out.println("idemo");
        Funkcije f=new Funkcije();
        novaPoruke=true;        
        sentTo=k;
        
        System.out.println(k);
         System.out.println(ja);
        poruke=new ArrayList();
        
        poruke=f.getDopisivanjeZa(ja,k);
        
        Collections.sort(poruke, new Comparator<Poruka>() {
        public int compare(Poruka s1, Poruka s2) {
         if (s1.getDatum() == null || s1.getDatum()  == null)
        return 0;
        return s1.getDatum().compareTo(s2.getDatum());
             }
            });
        
        for(Poruka p:poruke){
            System.out.println(p.getText());
        }
        
        
    }
    
    public void mojePoruke(Korisnik k){
        novaPoruke=false;
        poruke=new ArrayList<>();        
        poruke.addAll(k.getPorukasForPrimalac());
        
        System.out.println(poruke);
    }
    
    public void posalji(Korisnik korisnik){
        
        Poruka p=new Poruka(korisnik, sentTo, new Date(), text);
        poruke.add(p);
        Funkcije f=new Funkcije();
        f.savePoruka(p);
        text="";
    }
    
}
