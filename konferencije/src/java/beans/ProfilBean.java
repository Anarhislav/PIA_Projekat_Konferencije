/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Korisnik;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Drakulic
 */

@ManagedBean
@SessionScoped
public class ProfilBean implements Serializable {
    
    Korisnik korisnik;

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
    public String seeProfile(Korisnik k){
        korisnik =k;
        if(k.getSlika()==null){
            
            if(k.getPol().equals("muski")) k.setSlika("muski");
            else k.setSlika("zenski");
            
        }
        
        return "profil";
    }
    
    
}
