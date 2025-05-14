package test.classesConcretes;

import classesConcretes.*;
import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import interfaces.GenerateurDeCandidatsALaComparaison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateurDeCandidatsALaComparaisonTest {

	public static void main(String[] args) {
        // Données de test communes
       
// Noms et listes de noms
Nom nom1 = new Nom("jean dupont", Arrays.asList("jean", "dupont"), "1");
Nom nom2 = new Nom("jeanne durand", Arrays.asList("jeanne", "durand"), "2");
Nom nom3 = new Nom("pierre martin", Arrays.asList("pierre", "martin"), "3");
Nom nom4 = new Nom("jean dupont", Arrays.asList("jean", "dupont"), "4"); 
Nom nom5 = new Nom("paul jacques louis", Arrays.asList("paul", "jacques", "louis"), "5"); 
Nom nom6 = new Nom("anne marie", Arrays.asList("anne", "marie"), "6");
Nom nom7 = new Nom("sophie lambert", Arrays.asList("sophie", "lambert"), "7");
Nom nom8 = new Nom("thomas leroy", Arrays.asList("thomas", "leroy"), "8");
Nom nom9 = new Nom("camille petit", Arrays.asList("camille", "petit"), "9");
Nom nom10 = new Nom("lucie moreau", Arrays.asList("lucie", "moreau"), "10");
Nom nom11 = new Nom("nicolas bernard", Arrays.asList("nicolas", "bernard"), "11");
Nom nom12 = new Nom("emilie laurent", Arrays.asList("emilie", "laurent"), "12"); 
Nom nom13 = new Nom("alexandre roux", Arrays.asList("alexandre", "roux"), "13");
Nom nom14 = new Nom("julie michel", Arrays.asList("julie", "michel"), "14");
Nom nom15 = new Nom("david lefebvre", Arrays.asList("david", "lefebvre"), "15");
Nom nom16 = new Nom("caroline girard", Arrays.asList("caroline", "girard"), "16");
Nom nom17 = new Nom("jean pierre martin", Arrays.asList("jean", "pierre", "martin"), "17"); 
Nom nom18 = new Nom("marie claire dupont", Arrays.asList("marie", "claire", "dupont"), "18"); 
Nom nom19 = new Nom("luc henri paul durand", Arrays.asList("luc", "henri", "paul", "durand"), "19"); 
Nom nom20 = new Nom("emilie rose leclerc", Arrays.asList("emilie", "rose", "leclerc"), "20"); 
Nom nom21 = new Nom("jean dupont", Arrays.asList("jean", "dupont"), "21"); 
Nom nom22 = new Nom("pierre martin", Arrays.asList("pierre", "martin"), "22"); 
Nom nom23 = new Nom("anne marie", Arrays.asList("anne", "marie"), "23"); 
Nom nom24 = new Nom("thomas leroy", Arrays.asList("thomas", "leroy"), "24"); 
Nom nom25 = new Nom("antoine blanc", Arrays.asList("antoine", "blanc"), "25");
Nom nom26 = new Nom("laura simon", Arrays.asList("laura", "simon"), "26");
Nom nom27 = new Nom("pierre louis vincent", Arrays.asList("pierre", "louis", "vincent"), "27"); 
Nom nom28 = new Nom("marie jeanne lefevre", Arrays.asList("marie", "jeanne", "lefevre"), "28"); 
Nom nom29 = new Nom("alexandre thomas", Arrays.asList("alexandre", "thomas"), "29"); 
Nom nom30 = new Nom("lucie dupont", Arrays.asList("lucie", "dupont"), "30"); 

       // Liste 1 - Tous les 30 noms
List<Nom> liste1 = Arrays.asList(
    nom1, nom2, nom3, nom4, nom5, nom6, nom7, nom8, nom9, nom10,
    nom11, nom12, nom13, nom14, nom15, nom16, nom17, nom18, nom19, nom20,
    nom21, nom22, nom23, nom24, nom25, nom26, nom27, nom28, nom29, nom30
);

// Liste 2 - 15 premiers noms
List<Nom> liste2 = Arrays.asList(
    nom1, nom2, nom3, nom4, nom5, nom6, nom7, nom8, nom9, nom10,
    nom11, nom12, nom13, nom14, nom15
);

// Liste 3 - 15 derniers noms
List<Nom> liste3 = Arrays.asList(
    nom16, nom17, nom18, nom19, nom20, nom21, nom22, nom23, nom24,
    nom25, nom26, nom27, nom28, nom29, nom30
);
List<Nom> listeVide = new ArrayList<>();

        System.out.println("--- Test GenerateurPrimitif ---");
        testGenerateur(new GenerateurPrimitif(), liste1, liste1, "GenerateurPrimitif");
        testGenerateur(new GenerateurPrimitif(), liste2, listeVide, "GenerateurPrimitif (liste B vide)");
        testGenerateur(new GenerateurPrimitif(), listeVide, liste3, "GenerateurPrimitif (liste A vide)");
        testGenerateur(new GenerateurPrimitif(), listeVide, listeVide, "GenerateurPrimitif (listes A et B vides)");
       

      

        System.out.println("\n--- Test GenerateurParLongueurNomComplet ---");
        // Ce générateur ne devrait coupler que des noms de même longueur de nom complet
        testGenerateur(new GenerateurParLongueurNomComplet(3), liste1, liste1, "GenerateurParLongueurNomComplet(3)");
        testGenerateur(new GenerateurParLongueurNomComplet(3), liste2, listeVide, "GenerateurParLongueurNomComplet(3) (liste B vide)");
        testGenerateur(new GenerateurParLongueurNomComplet(3), listeVide, liste3, "GenerateurParLongueurNomComplet(3) (liste A vide)");
        testGenerateur(new GenerateurParLongueurNomComplet(3), listeVide, listeVide, "GenerateurParLongueurNomComplet(3) (listes A et B vides)");
        
        
        System.out.println("\n--- Test GenerateurParNbrPartiesNomDecompose ---");
        testGenerateur(new GenerateurParNbrPartiesNomDecompose(1), liste1, liste1, "GenerateurParNbrPartiesNomDecompose()");
        testGenerateur(new GenerateurParNbrPartiesNomDecompose(1), liste2, listeVide, "GenerateurParNbrPartiesNomDecompose() (liste B vide)");
        testGenerateur(new GenerateurParNbrPartiesNomDecompose(1), listeVide, liste3, "GenerateurParNbrPartiesNomDecompose() (liste A vide)");
        testGenerateur(new GenerateurParNbrPartiesNomDecompose(1), listeVide, listeVide, "GenerateurParNbrPartiesNomDecompose() (listes A et B vides)");

        System.out.println("\n--- Test GenerateurParPartiesDeNom ---");
        // Ce générateur devrait coupler des noms partageant au moins une partie de nom
        testGenerateur(new GenerateurParPartiesDeNom(), liste1, liste1, "GenerateurParPartiesDeNom(1)");
        testGenerateur(new GenerateurParPartiesDeNom(), liste2, listeVide, "GenerateurParPartiesDeNom(1) (liste B vide)");
        testGenerateur(new GenerateurParPartiesDeNom(), listeVide, liste3, "GenerateurParPartiesDeNom(1) (liste A vide)");
        testGenerateur(new GenerateurParPartiesDeNom(), listeVide, listeVide, "GenerateurParPartiesDeNom(1) (listes A et B vides)");
       
    }

	private static void testGenerateur(GenerateurDeCandidatsALaComparaison generateur, List<Nom> liste1,
			List<Nom> liste2, String nomGenerateur) {
		System.out.println("Testant " + nomGenerateur + " avec liste1 (taille " + liste1.size() + ") et liste2 (taille "
				+ liste2.size() + ")");
		List<CoupleDeNoms> candidats = generateur.generer(liste1, liste2);
		if (candidats != null) {
			System.out.println("  Nombre de candidats générés: " + candidats.size());
			// On pourrait ajouter des assertions plus spécifiques ici si le comportement
			// est déterministe
			// Par exemple, pour GenerateurPrimitif, on attend liste1.size() * liste2.size()
			// candidats.
			if (generateur instanceof GenerateurPrimitif) {
				if (candidats.size() == liste1.size() * liste2.size()) {
					System.out.println("    OK: Nombre de candidats attendu pour GenerateurPrimitif.");
				} else {
					System.err.println("    ERREUR: Nombre de candidats inattendu pour GenerateurPrimitif. Attendu: "
							+ (liste1.size() * liste2.size()) + ", Obtenu: " + candidats.size());
				}
			}
			for (CoupleDeNoms couple : candidats) {
				if (couple.getNom1() == null || couple.getNom2() == null) {
					System.err.println("    ERREUR: Un couple contient un nom null.");
					break;
				}
			}
		} else {
			System.err.println("  ERREUR: La liste de candidats retournée est null.");
		}
	}

	
	}

