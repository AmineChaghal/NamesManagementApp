package temp;

import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingDeNoms {

    private List<Pretraiteur> pretraiteurs;
    private ComparateurDeNoms comparateur;
    private GenerateurDeCandidatsALaComparaison generateur;
    private SelectionneurDeResultats selectionneur;

    public MoteurDeMatchingDeNoms(List<Pretraiteur> pretraiteurs,
                                   ComparateurDeNoms comparateur,
                                   GenerateurDeCandidatsALaComparaison generateur,
                                   SelectionneurDeResultats selectionneur) {
        this.pretraiteurs = pretraiteurs;
        this.comparateur = comparateur;
        this.generateur = generateur;
        this.selectionneur = selectionneur;
    }

    public List<ResultatDeComparaison> rechercherUnNomDansUneListe(Nom nomARechercher, List<Nom> listeOuRechercher) {
        

        // Étape 1 : prétraitement du nom à rechercher
        		
        pretraiterUnNom(nomARechercher);
        
        // Étape 2 : prétraitement de la liste
		
        for (Nom nom : listeOuRechercher) {
        	pretraiterUnNom(nom);
          }


        // Étape 3 : génération des candidats
        
        List<Nom> nomARechercherEnListe = new ArrayList<>();
        nomARechercherEnListe.add(nomARechercher);
        List<CoupleDeNoms> candidats = generateur.generer(nomARechercherEnListe,listeOuRechercher);

        // Étape 4 : comparaison 
        
        List<ResultatDeComparaison> resultats = new ArrayList<>();
        for (CoupleDeNoms candidat : candidats) {
        	ResultatDeComparaison couplePlusScore = new ResultatDeComparaison(candidat.getNom1(),candidat.getNom2(),comparateur.comparer(candidat.getNom1(), candidat.getNom2()));
        	resultats.add(couplePlusScore);
             }

     // Étape 4 : Sélection des resultats
        resultats = selectionneur.selectionner(resultats);
        
        return resultats;
    }
    
    public void comparerDeuxListes() {
        // À implémenter plus tard
    }

    public void dedupliquerUneListe() {
        // À implémenter plus tard
    }
    private void pretraiterUnNom(Nom nom) {
    	// Étape 1 : prétraitement du nom decomposé
        
        List<String> partiesNom = nom.getNomDecompose();
        for (Pretraiteur pretraiteur : pretraiteurs) {
            partiesNom = pretraiteur.traiter(partiesNom);
        }
        nom.setNomDecompose(partiesNom);
        
        // Étape 2 : prétraitement du nom complet
        
        List<String> nomComplet = new ArrayList<>();
        nomComplet.add(nom.getNomComplet());
        for (Pretraiteur pretraiteur : pretraiteurs) {
            nomComplet = pretraiteur.traiter(nomComplet);
        }
        nom.setNomComplet(nomComplet);
    }
}




/*// Étape 1.1 : prétraitement du nom decomposé
        
        List<String> partiesNom = nomARechercher.getNomDecompose();
        for (Pretraiteur pretraiteur : pretraiteurs) {
            partiesNom = pretraiteur.traiter(partiesNom);
        }
        nomARechercher.setNomDecompose(partiesNom);
        
        	// Étape 1.2 : prétraitement du nom complet
        
        List<String> nomComplet = new ArrayList<>();
        nomComplet.add(nomARechercher.getNomComplet());
        for (Pretraiteur pretraiteur : pretraiteurs) {
            nomComplet = pretraiteur.traiter(nomComplet);
        }
        nomARechercher.setNomComplet(nomComplet);
        */
