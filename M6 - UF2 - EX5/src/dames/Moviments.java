package dames;
// Generated 27/02/2020 16:49:18 by Hibernate Tools 4.3.1



/**
 * Moviments generated by hbm2java
 */
public class Moviments  implements java.io.Serializable {


     private Integer id;
     private Damas damas;
     private int numMoviments;
     private String moviment;

    public Moviments() {
    }

    public Moviments(Damas damas, int numMoviments, String moviment) {
       this.damas = damas;
       this.numMoviments = numMoviments;
       this.moviment = moviment;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Damas getDamas() {
        return this.damas;
    }
    
    public void setDamas(Damas damas) {
        this.damas = damas;
    }
    public int getNumMoviments() {
        return this.numMoviments;
    }
    
    public void setNumMoviments(int numMoviments) {
        this.numMoviments = numMoviments;
    }
    public String getMoviment() {
        return this.moviment;
    }
    
    public void setMoviment(String moviment) {
        this.moviment = moviment;
    }




}


