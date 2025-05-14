package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
// Importations des classes du projet
import classesConcretes.*;
import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.*;
import moteur.MoteurDeMatchingDeNoms;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static Map<String, String> configuration = new HashMap<>();
	private static final String CONFIG_FILE_PATH = "config.txt";

	// Options disponibles (basées sur les classes concrètes du projet)
	private static final String[] PRETRAITEMENT_OPTIONS = { "AucunPretraiteur", "PretraiteurConversionMinuscules",
			"PretraiteurSuppressionAccents", "PretraiteurSuppressionPonctuation", "PretraiteurSoundex"};
	private static final String[] GENERATEUR_CANDIDATS_OPTIONS = { "GenerateurPrimitif",
			"GenerateurParLongueurNomComplet", "GenerateurParNbrPartiesNomDecompose", "GenerateurParPartiesDeNom",
			"GenerateurTroisAleatoires" };
	private static final String[] COMPARATEUR_NOMS_OPTIONS = { "ComparateurDeNomsComplets", "ComparateurDeNomsRandom",
			"ComparateurDifferentesCombinaisonsDuNomDecompose" };
	// Si ComparateurDifferentesCombinaisonsDuNomDecompose est choisi, on proposera
	// ces comparateurs de chaînes
	private static final String[] COMPARATEUR_CHAINES_OPTIONS = { "ComparateurEgaliteExacte",
			"ComparateurJaroWinkler"};
	private static final String[] SELECTIONNEUR_OPTIONS = { "SelectionneurAvecSeuil", "SelectionneurDesNMeilleurs",
			"SelectionneurNeutre" };

	private static MoteurDeMatchingDeNoms moteur;

	public static void main(String[] args) {
		chargerConfiguration();
		initialiserValeursParDefautSiManquantes();
		moteur = instancierMoteurAvecConfig();

		boolean quitter = false;
		while (!quitter) {
			afficherMenuPrincipal();
			int choix = obtenirChoixUtilisateur(1, 5);

			switch (choix) {
			case 1:
				effectuerRecherche();
				break;
			case 2:
				comparerDeuxListes();
				break;
			case 3:
				dedupliquerListe();
				break;
			case 4:
				configurerParametres();
				moteur = instancierMoteurAvecConfig(); // Recharger le moteur avec la nouvelle config
				break;
			case 5:
				quitter = true;
				System.out.println("Programme terminé.");
				break;
			default:
				System.out.println("Choix invalide. Veuillez réessayer.");
			}
			if (!quitter) {
				System.out.println("\nAppuyez sur Entrée pour continuer...");
				scanner.nextLine();
			}
		}
		scanner.close();
	}

	private static void initialiserValeursParDefautSiManquantes() {
		configuration.putIfAbsent("pretraitements", PRETRAITEMENT_OPTIONS[0]); // Peut être une liste séparée par des
																				// virgules
		configuration.putIfAbsent("generateur_candidats", GENERATEUR_CANDIDATS_OPTIONS[0]);
		configuration.putIfAbsent("comparateur_noms", COMPARATEUR_NOMS_OPTIONS[0]);
		// comparateur_chaines est pertinent seulement si un comparateur de noms
		// spécifique est choisi
		configuration.putIfAbsent("comparateur_chaines", COMPARATEUR_CHAINES_OPTIONS[0]); // Par défaut pour
																							// ComparateurDifferentesCombinaisonsDuNomDecompose
		configuration.putIfAbsent("selectionneur", SELECTIONNEUR_OPTIONS[0]);
		configuration.putIfAbsent("selectionneur_valeur", "0.8"); // Seuil par défaut pour SelectionneurAvecSeuil ou N
																	// pour DesNMeilleurs

	}

	private static MoteurDeMatchingDeNoms instancierMoteurAvecConfig() {
		// 1. Prétraiteurs
		List<Pretraiteur> pretraiteursList = new ArrayList<>();
		String[] configuredPretraitements = configuration.get("pretraitements").split(",");
		for (String pretraitementName : configuredPretraitements) {
			switch (pretraitementName.trim()) {
			case "AucunPretraiteur":
				pretraiteursList.add(new AucunPretraiteur());
				break;
			case "PretraiteurConversionMinuscules":
				pretraiteursList.add(new PretraiteurConversionMinuscules());
				break;
			case "PretraiteurSuppressionAccents":
				pretraiteursList.add(new PretraiteurSuppressionAccents());
				break;
			case "PretraiteurSuppressionPonctuation":
				pretraiteursList.add(new PretraiteurSuppressionPonctuation());
				break;
			case "PretraiteurSoundex":
				pretraiteursList.add(new PretraiteurSoundex());
				break;

			default:
				System.out.println("Prétraitement inconnu: " + pretraitementName + ". Ignoré.");
			}
		}
		if (pretraiteursList.isEmpty()) { // Assurer au moins un prétraiteur (AucunPretraiteur par défaut)
			pretraiteursList.add(new AucunPretraiteur());
			System.out.println("Aucun prétraitement valide configuré, utilisation d'AucunPretraiteur.");
		}

		// 2. Générateur de Candidats
		GenerateurDeCandidatsALaComparaison generateur;
		switch (configuration.get("generateur_candidats")) {
		case "GenerateurPrimitif":
			generateur = new GenerateurPrimitif();
			break;
		case "GenerateurParLongueurNomComplet":
			generateur = new GenerateurParLongueurNomComplet(0);
			break;
		case "GenerateurParNbrPartiesNomDecompose":
			generateur = new GenerateurParNbrPartiesNomDecompose(0);
			break;
		case "GenerateurParPartiesDeNom":
			generateur = new GenerateurParPartiesDeNom();
			break;
		case "GenerateurTroisAleatoires":
			generateur = new GenerateurTroisAleatoires();
			break;
		default:
			generateur = new GenerateurPrimitif();
			System.out.println("Générateur inconnu, utilisation de GenerateurPrimitif.");
		}

		// 3. Comparateur de Noms (et de chaînes si applicable)
		ComparateurDeNoms comparateurNoms = null;
		String nomComparateurNoms = configuration.get("comparateur_noms");
		if ("ComparateurDeNomsComplets".equals(nomComparateurNoms)) {
			List<ComparateurDeChaines> comparateursChaines = new ArrayList<>();
			switch (configuration.get("comparateur_chaines")) {
			case "ComparateurEgaliteExacte":
				comparateursChaines.add(new ComparateurEgaliteExacte());
				break;
			case "ComparateurJaroWinkler":
				comparateursChaines.add(new ComparateurJaroWinkler());
				break;

			default:
				comparateursChaines.add(new ComparateurEgaliteExacte());
				System.out.println("Comparateur de chaînes inconnu, utilisation de ComparateurEgaliteExacte.");
			}

			comparateurNoms = new ComparateurDeNomsComplets(comparateursChaines);
		} else if ("ComparateurDeNomsRandom".equals(nomComparateurNoms)) {
			comparateurNoms = new ComparateurDeNomsRandom();
		} else if ("ComparateurDifferentesCombinaisonsDuNomDecompose".equals(nomComparateurNoms)) {
			List<ComparateurDeChaines> comparateursChaines = new ArrayList<>();
			switch (configuration.get("comparateur_chaines")) {
			case "ComparateurEgaliteExacte":
				comparateursChaines.add(new ComparateurEgaliteExacte());
				break;
			case "ComparateurJaroWinkler":
				comparateursChaines.add(new ComparateurJaroWinkler());
				break;

			default:
				comparateursChaines.add(new ComparateurEgaliteExacte());
				System.out.println("Comparateur de chaînes inconnu, utilisation de ComparateurEgaliteExacte.");
			}

			comparateurNoms = new ComparateurDifferentesCombinaisonsDuNomDecompose(comparateursChaines);
		} /*
			 * else { List<ComparateurDeChaines> comparateursChaines; switch
			 * (configuration.get("comparateur_chaines")) { case "ComparateurEgaliteExacte":
			 * comparateursChaines.add(new ComparateurEgaliteExacte()); break; case
			 * "ComparateurEquals": comparateursChaines.add(new ComparateurEquals()); break;
			 * case "ComparateurJaroWinkler": comparateursChaines.add(new
			 * ComparateurJaroWinkler()) ; break; case "ComparateurLevenshtein":
			 * comparateursChaines.add(new ComparateurLevenshtein()) ; break; default:
			 * comparateursChaines.add(new ComparateurEgaliteExacte()); System.out.
			 * println("Comparateur de chaînes inconnu, utilisation de ComparateurEgaliteExacte."
			 * ); } comparateurNoms = new ComparateurDeNomsComplets(comparateursChaines);
			 * System.out.
			 * println("Comparateur de noms inconnu, utilisation de ComparateurDeNomsComplets."
			 * ); }
			 */

		// 4. Sélectionneur de Résultats
		SelectionneurDeResultats selectionneur;
		String nomSelectionneur = configuration.get("selectionneur");
		String valeurSelectionneurStr = configuration.get("selectionneur_valeur");
		if ("SelectionneurAvecSeuil".equals(nomSelectionneur)) {
			try {
				float seuil = Float.parseFloat(valeurSelectionneurStr);
				selectionneur = new SelectionneurAvecSeuil(seuil);
			} catch (NumberFormatException e) {
				selectionneur = new SelectionneurNeutre();
				System.out.println(
						"Valeur de seuil invalide (doit être un nombre flottant), utilisation de SelectionneurNeutre.");
			}
		} else if ("SelectionneurDesNMeilleurs".equals(nomSelectionneur)) {
			try {
				int n = Integer.parseInt(valeurSelectionneurStr);
				selectionneur = new SelectionneurDesNMeilleurs(n);
			} catch (NumberFormatException e) {
				selectionneur = new SelectionneurNeutre();
				System.out.println(
						"Valeur pour N meilleurs invalide (doit être un entier), utilisation de SelectionneurNeutre.");
			}
		} else if ("SelectionneurNeutre".equals(nomSelectionneur)) {
			selectionneur = new SelectionneurNeutre();
		} else {
			selectionneur = new SelectionneurNeutre();
			System.out.println("Sélectionneur inconnu, utilisation de SelectionneurNeutre.");
		}

		// La structure d'index n'est pas encore utilisée pour instancier le moteur.
		System.out.println("Moteur instancié avec la configuration actuelle.");
		return new MoteurDeMatchingDeNoms(pretraiteursList, comparateurNoms, generateur, selectionneur);
	}

	private static void afficherMenuPrincipal() {
		System.out.println("\n--- Menu Principal ---");
		System.out.println("1. Effectuer une recherche");
		System.out.println("2. Comparer deux listes");
		System.out.println("3. Dédupliquer une liste");
		System.out.println("4. Configurer les paramètres");
		System.out.println("5. Quitter");
		System.out.print("Votre choix : ");
	}

	private static List<Nom> chargerNomsDepuisCSV(String cheminFichier) {
		List<Nom> noms = new ArrayList<>();
		// Utilisation de RecuperateurDeNomsDepuisCSV qui gère la décomposition.
		// Il faut lui passer un DecomposeurDeNoms. Pour l'instant, on utilise
		// DecomposeurAvecSeparateur par défaut.
		// Ce décomposeur pourrait aussi être configurable.
		DecomposeurDeNoms decomposeur = new DecomposeurAvecSeparateur(); // Exemple de séparateurs
		RecuperateurDeNoms recuperateur = new RecuperateurDeNomsDepuisCSV(cheminFichier, decomposeur);
		try {
			noms = recuperateur.recuperer();
			if (noms.isEmpty() && !cheminFichier.isEmpty()) {
				System.out.println("Attention: Le fichier " + cheminFichier
						+ " a été lu mais est vide ou ne contient pas de noms valides.");
			}
		} catch (Exception e) { // RecuperateurDeNoms.recuperer() ne déclare pas d'exception, mais la lecture de
								// fichier peut en jeter.
			System.err.println("Erreur lors du chargement du fichier CSV: " + cheminFichier + " - " + e.getMessage());
			return new ArrayList<>(); // Retourner une liste vide en cas d'erreur pour éviter NullPointerException
		}
		return noms;
	}

	private static void afficherResultats(List<ResultatDeComparaison> resultats, String typeOperation) {
		System.out.println("\n--- Résultats pour " + typeOperation + " ---");
		if (resultats == null || resultats.isEmpty()) {
			System.out.println("Aucun résultat trouvé/correspondance détectée selon la configuration actuelle.");
		} else {
			for (ResultatDeComparaison res : resultats) {
				System.out.println("  Nom 1: " + res.getNom1().getNomComplet() + " (ID: " + res.getNom1().getId()
						+ ") | Nom 2: " + res.getNom2().getNomComplet() + " (ID: " + res.getNom2().getId()
						+ ") | Score: " + String.format("%.4f", res.getScore()));
			}
		}
	}

	private static void effectuerRecherche() {
		System.out.println("\n--- Effectuer une recherche ---");
		System.out.print("Saisir le nom complet à rechercher : ");
		String nomCompletRecherche = scanner.nextLine();

		// Utilisation d'un décomposeur (configurable à terme si besoin)
		DecomposeurDeNoms decomposeur = new DecomposeurAvecSeparateur();
		List<String> nomDecomposeRecherche = decomposeur.construire(nomCompletRecherche);
		Nom nomARechercher = new Nom(nomCompletRecherche, nomDecomposeRecherche, "0"); // ID 0 pour le nom recherché

		System.out.print("Fournir le chemin du fichier CSV contenant la liste : ");
		String cheminFichierCsv = scanner.nextLine();
		List<Nom> listeOuRechercher = chargerNomsDepuisCSV(cheminFichierCsv);

		if (listeOuRechercher.isEmpty() && !cheminFichierCsv.isEmpty()) {
			// Message déjà affiché par chargerNomsDepuisCSV si le fichier est vide après
			// lecture
			// ou si erreur de chargement.
			// On peut ajouter un message si la liste est vide après un chargement réussi
			// mais fichier vide.
			// System.out.println("La liste de noms chargée est vide.");
			// return;
		} else if (listeOuRechercher.isEmpty() && cheminFichierCsv.isEmpty()) {
			System.out.println("Aucun fichier CSV fourni.");
			return;
		}

		System.out.println("Recherche de \"" + nomCompletRecherche + "\" dans \"" + cheminFichierCsv + "\"...");
		List<ResultatDeComparaison> resultats = moteur.rechercherUnNomDansUneListe(nomARechercher, listeOuRechercher);
		afficherResultats(resultats, "Recherche");
	}

	private static void comparerDeuxListes() {
		System.out.println("\n--- Comparer deux listes ---");
		System.out.print("Fournir le chemin du premier fichier CSV : ");
		String cheminFichier1 = scanner.nextLine();
		List<Nom> liste1 = chargerNomsDepuisCSV(cheminFichier1);

		System.out.print("Fournir le chemin du second fichier CSV : ");
		String cheminFichier2 = scanner.nextLine();
		List<Nom> liste2 = chargerNomsDepuisCSV(cheminFichier2);

		if ((liste1.isEmpty() && !cheminFichier1.isEmpty()) || (liste2.isEmpty() && !cheminFichier2.isEmpty())) {
			// Message déjà géré par chargerNomsDepuisCSV
			// return;
		} else if (liste1.isEmpty() || liste2.isEmpty()) {
			System.out.println("Une des listes (ou les deux) est vide. Impossible de comparer.");
			return;
		}

		System.out.println("Comparaison de \"" + cheminFichier1 + "\" et \"" + cheminFichier2 + "\"...");
		List<ResultatDeComparaison> resultats = moteur.comparerDeuxListes(liste1, liste2);
		afficherResultats(resultats, "Comparaison de deux listes");
	}

	private static void dedupliquerListe() {
		System.out.println("\n--- Dédupliquer une liste ---");
		System.out.print("Fournir le chemin du fichier CSV à traiter : ");
		String cheminFichier = scanner.nextLine();
		List<Nom> listeADedupliquer = chargerNomsDepuisCSV(cheminFichier);

		if (listeADedupliquer.isEmpty() && !cheminFichier.isEmpty()) {
			// Message géré
			// return;
		} else if (listeADedupliquer.isEmpty()) {
			System.out.println("La liste à dédupliquer est vide.");
			return;
		}

		System.out.println("Déduplication de \"" + cheminFichier + "\"...");
		List<ResultatDeComparaison> resultats = moteur.dedupliquerUneListe(listeADedupliquer);
		afficherResultats(resultats, "Déduplication");
	}

	private static void configurerParametres() {
		boolean retourMenuPrincipal = false;
		while (!retourMenuPrincipal) {
			System.out.println("\n--- Configurer les paramètres ---");
			afficherConfigurationActuelle();
			System.out.println("1. Choisir les prétraitements (Actuel: " + configuration.get("pretraitements") + ")");
			System.out.println(
					"3. Choisir une mesure de comparaison (Actuel: " + configuration.get("comparateur_noms") + ")");
			if (configuration.get("comparateur_noms").equals("ComparateurDifferentesCombinaisonsDuNomDecompose")) {
				System.out.println("   3b. Choisir comparateur de chaînes pour noms décomposés (Actuel: "
						+ configuration.get("comparateur_chaines") + ")");
			}
			System.out.println(
					"4. Définir le sélectionneur et son paramètre (Actuel: " + configuration.get("selectionneur")
							+ " avec valeur " + configuration.get("selectionneur_valeur") + ")");
			System.out.println("5. Choisir un générateur de candidats (Actuel: "
					+ configuration.get("generateur_candidats") + ")");
			System.out.println("6. Retour au menu principal");
			System.out.print("Votre choix : ");

			int choixConfig = obtenirChoixUtilisateur(1, 6);
			switch (choixConfig) {
			case 1:
				choisirPretraitements();
				break;
			case 3:
				choisirComparateurNoms();
				break;
			case 4:
				choisirSelectionneur();
				break;
			case 5:
				choisirOptionSimple("generateur_candidats", "générateur de candidats", GENERATEUR_CANDIDATS_OPTIONS);
				break;
			case 6:
				retourMenuPrincipal = true;
				break;
			default:
				System.out.println("Choix invalide.");
			}
			if (!retourMenuPrincipal)
				sauvegarderConfiguration();
		}
	}

	private static void choisirPretraitements() {
		System.out.println("\n--- Choisir un ou plusieurs prétraitements ---");
		System.out.println("Prétraitements actuels: " + configuration.get("pretraitements"));
		List<String> pretraitementChoisis = new ArrayList<>(
				Arrays.asList(configuration.get("pretraitements").split(",")));

		for (int i = 0; i < PRETRAITEMENT_OPTIONS.length; i++) {
			System.out.println((i + 1) + ". " + PRETRAITEMENT_OPTIONS[i]
					+ (pretraitementChoisis.contains(PRETRAITEMENT_OPTIONS[i]) ? " (Sélectionné)" : ""));
		}
		System.out.println((PRETRAITEMENT_OPTIONS.length + 1) + ". Valider les sélections");
		System.out.println((PRETRAITEMENT_OPTIONS.length + 2) + ". Annuler");

		boolean finSelectionPretraitement = false;
		while (!finSelectionPretraitement) {
			System.out.print("Choisir un numéro pour ajouter/retirer, valider ou annuler: ");
			int choix = obtenirChoixUtilisateur(1, PRETRAITEMENT_OPTIONS.length + 2);
			if (choix >= 1 && choix <= PRETRAITEMENT_OPTIONS.length) {
				String selected = PRETRAITEMENT_OPTIONS[choix - 1];
				if (pretraitementChoisis.contains(selected)) {
					pretraitementChoisis.remove(selected);
					System.out.println(selected + " retiré.");
				} else {
					pretraitementChoisis.add(selected);
					System.out.println(selected + " ajouté.");
				}
			} else if (choix == PRETRAITEMENT_OPTIONS.length + 1) {
				if (pretraitementChoisis.isEmpty()) {
					// Au moins AucunPretraiteur si la liste est vide
					pretraitementChoisis.add(PRETRAITEMENT_OPTIONS[0]);
				}
				configuration.put("pretraitements", String.join(",", pretraitementChoisis));
				System.out.println("Prétraitements mis à jour : " + configuration.get("pretraitements"));
				finSelectionPretraitement = true;
			} else { // Annuler
				System.out.println("Modification des prétraitements annulée.");
				finSelectionPretraitement = true;
			}
		}
	}

	private static void choisirComparateurNoms() {
		choisirOptionSimple("comparateur_noms", "mesure de comparaison de noms", COMPARATEUR_NOMS_OPTIONS);
		if (configuration.get("comparateur_noms").equals("ComparateurDifferentesCombinaisonsDuNomDecompose")) {
			System.out.println(
					"Le comparateur 'ComparateurDifferentesCombinaisonsDuNomDecompose' nécessite un comparateur de chaînes.");
			choisirOptionSimple("comparateur_chaines", "comparateur de chaînes", COMPARATEUR_CHAINES_OPTIONS);
		}
	}

	private static void choisirSelectionneur() {
		System.out.println("\n--- Choisir le sélectionneur de résultats ---");
		for (int i = 0; i < SELECTIONNEUR_OPTIONS.length; i++) {
			System.out.println((i + 1) + ". " + SELECTIONNEUR_OPTIONS[i]);
		}
		System.out.println((SELECTIONNEUR_OPTIONS.length + 1) + ". Annuler");
		System.out.print("Votre choix de sélectionneur : ");
		int choixSel = obtenirChoixUtilisateur(1, SELECTIONNEUR_OPTIONS.length + 1);

		if (choixSel <= SELECTIONNEUR_OPTIONS.length) {
			String selectionneurChoisi = SELECTIONNEUR_OPTIONS[choixSel - 1];
			configuration.put("selectionneur", selectionneurChoisi);
			if ("SelectionneurAvecSeuil".equals(selectionneurChoisi)) {
				System.out.print("Entrez la valeur du seuil (ex: 0.8) : ");
				while (!scanner.hasNextDouble()) {
					System.out.print("Valeur invalide. Entrez un double pour le seuil: ");
					scanner.next();
				}
				double seuil = scanner.nextDouble();
				scanner.nextLine(); // Consomme newline
				configuration.put("selectionneur_valeur", String.valueOf(seuil));
			} else if ("SelectionneurDesNMeilleurs".equals(selectionneurChoisi)) {
				System.out.print("Entrez le nombre de meilleurs résultats (N) : ");
				while (!scanner.hasNextInt()) {
					System.out.print("Valeur invalide. Entrez un entier pour N: ");
					scanner.next();
				}
				int n = scanner.nextInt();
				scanner.nextLine(); // Consomme newline
				configuration.put("selectionneur_valeur", String.valueOf(n));
			} else { // SelectionneurNeutre ou autre sans valeur spécifique
				configuration.put("selectionneur_valeur", "N/A");
			}
			System.out.println("Sélectionneur mis à jour: " + selectionneurChoisi + " avec valeur: "
					+ configuration.get("selectionneur_valeur"));
		} else {
			System.out.println("Modification du sélectionneur annulée.");
		}
	}

	private static void choisirOptionSimple(String cleConfig, String nomOption, String[] optionsDisponibles) {
		System.out.println("\n--- Choisir " + nomOption + " ---");
		for (int i = 0; i < optionsDisponibles.length; i++) {
			System.out.println((i + 1) + ". " + optionsDisponibles[i]);
		}
		System.out.println((optionsDisponibles.length + 1) + ". Annuler");
		System.out.print("Votre choix : ");
		int choix = obtenirChoixUtilisateur(1, optionsDisponibles.length + 1);
		if (choix <= optionsDisponibles.length) {
			configuration.put(cleConfig, optionsDisponibles[choix - 1]);
			System.out.println(nomOption + " mis à jour : " + configuration.get(cleConfig));
		} else {
			System.out.println("Modification annulée.");
		}
	}

	private static void afficherConfigurationActuelle() {
		System.out.println("Configuration actuelle :");
		if (configuration.isEmpty()) {
			System.out.println("  Aucune configuration chargée ou définie.");
			return;
		}
		System.out.println("  - Prétraitements: " + configuration.getOrDefault("pretraitements", "N/A"));
		System.out.println("  - Générateur de candidats: " + configuration.getOrDefault("generateur_candidats", "N/A"));
		System.out
				.println("  - Mesure de comparaison de noms: " + configuration.getOrDefault("comparateur_noms", "N/A"));
		if (configuration.getOrDefault("comparateur_noms", "")
				.equals("ComparateurDifferentesCombinaisonsDuNomDecompose")) {
			System.out.println("    - Comparateur de chaînes (pour noms décomposés): "
					+ configuration.getOrDefault("comparateur_chaines", "N/A"));
		}
		System.out.println("  - Sélectionneur: " + configuration.getOrDefault("selectionneur", "N/A") + " (Valeur: "
				+ configuration.getOrDefault("selectionneur_valeur", "N/A") + ")");
	}

	private static void chargerConfiguration() {
		try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				String[] parts = ligne.split("=", 2);
				if (parts.length == 2) {
					configuration.put(parts[0].trim(), parts[1].trim());
				}
			}
			System.out.println("Configuration chargée depuis " + CONFIG_FILE_PATH);
		} catch (IOException e) {
			System.out.println("Aucun fichier de configuration (" + CONFIG_FILE_PATH
					+ ") trouvé ou erreur de lecture. Utilisation des valeurs par défaut.");
		}
	}

	private static void sauvegarderConfiguration() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
			for (Map.Entry<String, String> entry : configuration.entrySet()) {
				writer.write(entry.getKey() + "=" + entry.getValue());
				writer.newLine();
			}
			System.out.println("Configuration sauvegardée dans " + CONFIG_FILE_PATH);
		} catch (IOException e) {
			System.err.println("Erreur lors de la sauvegarde de la configuration : " + e.getMessage());
		}
	}

	private static int obtenirChoixUtilisateur(int min, int max) {
		int choix = -1;
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.print("Veuillez entrer un nombre valide : ");
				scanner.next();
				scanner.nextLine(); // Consommer le reste de la ligne invalide
				continue;
			}
			choix = scanner.nextInt();
			scanner.nextLine(); // Consommer le newline après nextInt()
			if (choix >= min && choix <= max) {
				break;
			}
			System.out
					.print("Choix hors limites (" + choix + "). Veuillez choisir entre " + min + " et " + max + " : ");
		}
		return choix;
	}
}
