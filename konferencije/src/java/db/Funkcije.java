/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Drakulic
 */
public class Funkcije implements Serializable {
    
    HibernateUtil util;
     Session session=null;
    public Funkcije(){
        this.session=HibernateUtil.getSessionFactory().getCurrentSession();
   
    }
    
   
    
    
    public boolean saveKorisnik(Korisnik korisnik){
        
        Boolean added=true;
        
         session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(korisnik);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
            added=false;   
        }finally{
              if(session.isOpen())
             session.close();
         }
      return added; 
        
    }
   public Korisnik getKorisnik(String username){
        Korisnik korisnik=null;  
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Korisnik.class);
        c.add(Restrictions.eq("username",username));
        korisnik=(Korisnik) c.uniqueResult();      
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
             if(session.isOpen())
             session.close();
         }
        return korisnik;        
    }

    public List<Konferencija> getAllKonferencije() {
        List<Konferencija> k=null;
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Konferencija.class);
        c.addOrder(Order.asc("datumOd"));
        k= c.list();
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return k;
    }

    public List<Oblast> getAllOblasti() {
        List<Oblast> k=null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        try{
         
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Oblast.class);
        
        k= c.list();
        session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
             if(session.isOpen())
             session.close();
         }
        return k;    }

    public List<Konferencija> filterKonferencije(String naziv, Date datumOd, Date datumDo, List<String> izabraneOblasti) {
            if(naziv==null)naziv="";
                              
            if(izabraneOblasti==null) izabraneOblasti=new ArrayList();
                                  
            Oblast eir=null;
            if(izabraneOblasti.contains("elektrotehnika i racunarstvo"))
            eir=getOblast("elektrotehnika i racunarstvo");
             
            Oblast arh=null;
            if(izabraneOblasti.contains("arhitektura"))
            arh=getOblast("arhitektura");
            
            Oblast gig=null;
            if(izabraneOblasti.contains("gradjevina i geodezija"))
            gig=getOblast("gradjevina i geodezija");
            
            Oblast mii=null;
            if(izabraneOblasti.contains("masinstvo i industrisko inzinjerstvo"))
            mii=getOblast("masinstvo i industrisko inzinjerstvo");
            
            Oblast med=null;
            if(izabraneOblasti.contains("medicina"))
            med=getOblast("medicina");
            
            Oblast fih=null;
            if(izabraneOblasti.contains("fizicke i hemijske nauke"))
            fih=getOblast("fizicke i hemijske nauke");
            
            Oblast bio=null;
            if(izabraneOblasti.contains("bioloske nauke"))
            bio=getOblast("bioloske nauke");
            
            Oblast zas=null;
            if(izabraneOblasti.contains("zastita zivotne sredine"))
            zas=getOblast("zastita zivotne sredine");
            
        List<Konferencija> konf=null;
        session=HibernateUtil.getSessionFactory().getCurrentSession();
        try{
        
        session.beginTransaction(); 
        Criteria criteria=session.createCriteria(Konferencija.class);
        
        if(!"".equals(naziv)){
            criteria.add(Restrictions.eq("naziv", naziv));
        }
        
        
           if(datumOd!=null&&datumDo!=null){
           System.out.println(datumOd);
           System.out.println(datumDo);
               
            criteria.add(Restrictions.ge("datumDo", datumOd));
            criteria.add(Restrictions.le("datumOd", datumDo));
          
        }  
           
         System.out.println("++++++++++++++++++++++++++"); 
         
        if(izabraneOblasti.size()!=0){
        if(eir!=null)
            System.out.println(eir.getOblast());
        if(arh!=null)
            System.out.println(arh.getOblast());
        if(gig!=null)
            System.out.println(gig.getOblast());
        if(mii!=null)
            System.out.println(mii.getOblast());
        if(med!=null)
            System.out.println(med.getOblast());
        if(fih!=null)
            System.out.println(fih.getOblast());
        if(bio!=null)
            System.out.println(bio.getOblast());
        if(zas!=null)
            System.out.println(zas.getOblast());
        
        
           criteria.add(Restrictions.disjunction(Restrictions.eq("oblast", eir),Restrictions.eq("oblast", arh),Restrictions.eq("oblast", gig),Restrictions.eq("oblast", mii),Restrictions.eq("oblast", med),Restrictions.eq("oblast", fih),Restrictions.eq("oblast", bio),Restrictions.eq("oblast", zas)));
        
        }
         konf=criteria.list(); 
        session.getTransaction().commit();
       
         }catch(Exception e){
            e.printStackTrace();
               
        }finally{
             if(session.isOpen())
             session.close();
         }
        return konf;
    }

    public void saveSesija(Sesija s) {
        
        session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(s);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         }
    }

    public void snimiPrijava(Konferencija konf,Korisnik k) {
        session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        System.out.println(session.createSQLQuery("insert into prijava values ("+konf.getIdkonf()+",'"+k.getUsername()+"')").toString());
        session.createSQLQuery("insert into prijava values ("+konf.getIdkonf()+",'"+k.getUsername()+"')").executeUpdate();
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         }
    }

    public void savePrijavaPredavanja(PrijavaPredavanje prijava) {
 session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(prijava);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         }    }

    public  void snimiPrijava(Prijava prijava) { 
        
session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(prijava);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         } 
         
    }

    
    public void saveKomentar(Komentar k) {
        
        session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(k);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         }  
    }

    public List<Korisnik> getAllKorisnici() {
        List<Korisnik> k=new ArrayList<>();
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Korisnik.class);
        
        k= c.list();
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return k;
    }

    public void savePoruka(Poruka p) {

        
         session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(p);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         } 
    }

    public List<Mesto> getAllMesta() {

        List<Mesto> k=new ArrayList<>();
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Mesto.class);
        
        k= c.list();
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return k;
        
        
    }

    public List<Korisnik> getOnlyKorisnici() {
        
        List<Korisnik> k=new ArrayList<>();
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Korisnik.class);
         c.add(Restrictions.ne("tip", "admin"));
        k= c.list();
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return k;
        
    }

    public Oblast getOblast(String oblast) {
        
        Oblast o=null;  
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Oblast.class);
        c.add(Restrictions.eq("oblast",oblast));
        o=(Oblast) c.uniqueResult();      
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
             if(session.isOpen())
             session.close();
         }
        return o;  
        
    }

    public Mesto getMesto(String mesto) {
        Mesto m=null;  
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Mesto.class);
        c.add(Restrictions.eq("lokacija",mesto));
        m=(Mesto) c.uniqueResult();      
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
             if(session.isOpen())
             session.close();
         }
        return  m; 
              
    }

    public void saveKonferencija(Konferencija konf) {
        session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(konf);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         }  
        
    }

    public List<Sala> getSale(Mesto mesto) {
        
        List<Sala> k=new ArrayList<>();
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Sala.class);
         c.add(Restrictions.eq("mesto", mesto));
        k= c.list();
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return k;
        
    }

    public Sala getSala(String sala) {
        
        
        Sala m=null;  
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Sala.class);
        c.add(Restrictions.eq("naziv",sala));
        
        m=(Sala) c.uniqueResult();      
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
             if(session.isOpen())
             session.close();
         }
        return  m; 
        
    }

    public void savePredavanje(Predavanje predavanje) {
        
        session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
        session.beginTransaction(); 
        session.saveOrUpdate(predavanje);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         }  
        
        
    }

    public void saveSlika(Sesija sesija, String fileName) {
       session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
             Galerija g=new Galerija(sesija, fileName);
        session.beginTransaction(); 
        session.saveOrUpdate(g);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         } 
    }

    public void deleteKonferencija(Konferencija konf) {
        session=HibernateUtil.getSessionFactory().getCurrentSession();
         try{
             
        session.beginTransaction(); 
        session.delete(konf);
        session.getTransaction().commit();
        
        }catch(Exception e){
            e.printStackTrace();
               
        }finally{
              if(session.isOpen())
             session.close();
         } 
    }

    public List<Korisnik> getKorisnici(String traziKorisnika) {
       
        List<Korisnik> k=new ArrayList<>();
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Korisnik.class);
        c.add(Restrictions.ilike("username", traziKorisnika+"%"));
        k= c.list();
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return k;
        
        
    }

    public List<Poruka> getDopisivanjeZa(Korisnik ja, Korisnik k) {
        List<Poruka> poruka=new ArrayList();
        try{
         session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction(); 
        Criteria c=session.createCriteria(Poruka.class);
       // String upit="Select Poruka where ( posiljalac='"+ja.getUsername()+"' and primalac='"+k.getUsername()+"') or ( posiljalac='"+k.getUsername()+"' and primalac='"+ja.getUsername()+"');";
       // System.out.println(upit);
        c.add(Restrictions.or(Restrictions.and(Restrictions.eq("korisnikByPosiljalac", ja),Restrictions.eq("korisnikByPrimalac", k)),Restrictions.and(Restrictions.eq("korisnikByPosiljalac", k),Restrictions.eq("korisnikByPrimalac", ja))));
       // c.add(Restrictions.eq("korisnikByPosiljalac", ja));
        //c.add(Restrictions.eq("korisnikByPrimalac", k));
        poruka= c.list();
        System.out.println(poruka);
       // Criteria c1=session.createCriteria(Poruka.class);
        //c1.add(Restrictions.eq("korisnikByPosiljalac", k));
       //c1.add(Restrictions.eq("korisnikByPrimalac", ja));
       //poruka.addAll(c1.list());
        System.out.println(poruka);
        session.getTransaction().commit();
       }catch(Exception e){
            e.printStackTrace();
               
        }finally{
            if(session.isOpen())
             session.close();
         }
        
        return poruka;
    }
    
}
