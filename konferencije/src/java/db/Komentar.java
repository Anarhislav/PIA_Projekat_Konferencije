package db;
// Generated Jul 7, 2017 7:01:22 PM by Hibernate Tools 4.3.1



/**
 * Komentar generated by hbm2java
 */
public class Komentar  implements java.io.Serializable {


     private Integer idkomentar;
     private Korisnik korisnik;
     private Predavanje predavanje;
     private String komentar;
     private int ocena;

    public Komentar() {
    }

    public Komentar(Korisnik korisnik, Predavanje predavanje, String komentar, int ocena) {
       this.korisnik = korisnik;
       this.predavanje = predavanje;
       this.komentar = komentar;
       this.ocena = ocena;
    }
   
    public Integer getIdkomentar() {
        return this.idkomentar;
    }
    
    public void setIdkomentar(Integer idkomentar) {
        this.idkomentar = idkomentar;
    }
    public Korisnik getKorisnik() {
        return this.korisnik;
    }
    
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    public Predavanje getPredavanje() {
        return this.predavanje;
    }
    
    public void setPredavanje(Predavanje predavanje) {
        this.predavanje = predavanje;
    }
    public String getKomentar() {
        return this.komentar;
    }
    
    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    public int getOcena() {
        return this.ocena;
    }
    
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }




}


