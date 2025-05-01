package classesPorteusesDeDonnees;

import java.util.List;

public class Nom {
    private String nomComplet;
    private List<String> nomDecompose;
    private int id;
    


    public Nom(String nomComplet, List<String> nomDecompose, int id) {
        this.nomComplet = nomComplet;
        this.nomDecompose = nomDecompose;
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public List<String> getNomDecompose() {
        return nomDecompose;
    }
    
    public int getId() {
        return id;
    }

	public void setNomDecompose(List<String> partiesNom) {
		// TODO Auto-generated method stub
		
	}

	public void setNomComplet(List<String> nomComplet2) {
		// TODO Auto-generated method stub
		
	}
}

