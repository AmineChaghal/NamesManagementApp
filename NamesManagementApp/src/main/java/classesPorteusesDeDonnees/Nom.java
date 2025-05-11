package classesPorteusesDeDonnees;

import java.util.List;

public class Nom {
    private String nomComplet;
    private List<String> nomDecompose;
    private String id;
    


    public Nom(String nomComplet, List<String> nomDecompose, String id) {
        this.nomComplet = nomComplet;
        this.nomDecompose = nomDecompose;
        this.id = id;
    }

    public Nom(String id2, String nomComplet2) {
    	this.nomComplet = nomComplet2;
        this.id = id2;
	}

	public String getNomComplet() {
        return nomComplet;
    }

    public List<String> getNomDecompose() {
        return nomDecompose;
    }
    
    public String getId() {
        return id;
    }

	

	public void setNomComplet(String nomComplet2) {
		this.nomComplet = nomComplet2;
		
	}

	public void setNomDecompose(List<String> nomDecompose) {
		this.nomDecompose = nomDecompose;
	}
}

