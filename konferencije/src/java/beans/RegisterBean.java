/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Funkcije;
import db.Korisnik;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Drakulic
 */
@ManagedBean
@RequestScoped
public class RegisterBean implements Serializable{
    
    String ime;
    String prezime;
    String username;
    String password1;
    String password2;
    String institucija;
    String majica;
    String pol;
    String linkedin;
    String email;
    String slika;
    String oldpassword;
private UploadedFile file;






    

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }
    
    
    
    
    
   

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public String getMajica() {
        return majica;
    }

    public void setMajica(String majica) {
        this.majica = majica;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    
    
    public String registerUser(){
         
        if(password1.equals(password2)){
        Korisnik k=new Korisnik(username, password1,"korisnik");
        
        k.setIme(ime);
        k.setEmail(email);
        k.setMajca(majica);
        k.setPrezime(prezime);
        k.setInstitucija(institucija);
        k.setLinkedin(linkedin);
        k.setPol(pol);
        
       if(file==null){
            if("muski".equals(pol))
                slika="muski.png";
            else
                slika="zenski.png";
        
       }else{
           
           UploadedFile uploadedFile = file;

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
      
    String destPath = putanja+"konferencije\\web\\resources\\slike\\profilne\\"+mark+uploadedFile.getFileName();
    System.out.println(destPath.toString());
    File destFile = new File(destPath);
    
    k.setSlika(mark+uploadedFile.getFileName());
    
    //use org.apache.commons.io.FileUtils to copy the File
    try {                    
        FileUtils.copyInputStreamToFile(inputStr, destFile);
    } catch (IOException e) {
        //log error
         e.printStackTrace();
    } 
           
           
           
       }
       

        Funkcije f=new Funkcije();
        if(f.saveKorisnik(k))
        
        return "pocetna";
        else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "korisnik sa tim username vec postoji"));

            return "";
        }
        }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "mila moj sifre ti se ne poklapaju"));

        return "";
    }
    
    
}
