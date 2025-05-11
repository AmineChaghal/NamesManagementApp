package moteur;

import java.util.ArrayList;
import java.util.List;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.ComparateurDeNoms;
import interfaces.GenerateurDeCandidatsALaComparaison;
import interfaces.Pretraiteur;
import interfaces.SelectionneurDeResultats;

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

    
    /*La méthode rechercherUnNomDansUneListe prend en parametres un nomARechercher de type Nom et une listeOuRechercher de type liste de Nom
     * L'utilisateur entrera un String à rechercher qui sera mis en format Nom avec un id trivial
     * Le RecuperateurDeNoms recuperera depuis le fichier d'abord une liste de string avec id et regroupera ces elements pour retourner une liste de Noms  
     * Durant ces deux étapes l'initialisation de l'attribut nomDecompose de chaque Nom se fera grace à un DecomposeurDeNoms
     * Ces etapes se feront dans le main avant de passer les parametres adéquats à la méthode rechercherUnNomDansUneListe
     *  */
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
    
    public List<ResultatDeComparaison> comparerDeuxListes(List<Nom> liste1, List<Nom> liste2) {
        List<ResultatDeComparaison> resultats = new ArrayList<>();

        for (Nom nom : liste1) {
            resultats.addAll(rechercherUnNomDansUneListe(nom, liste2));
        }

        return resultats;
    }


    public List<ResultatDeComparaison> dedupliquerUneListe(List<Nom> liste) {
        List<ResultatDeComparaison> resultatsBruts = comparerDeuxListes(liste, liste);

        return resultatsBruts.stream()
            .filter(r -> !r.getNom1().getId().equals(r.getNom2().getId()))
            .toList();
    }

    
    
    /*la methode pretraiterUnNom permet de pretraiter les deux attributs NomDecompose et nomComplet d'un objet de type Nom en les faisant passer par la liste des pretraitements */
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
        nom.setNomComplet(nomComplet.getFirst());
    }
}





