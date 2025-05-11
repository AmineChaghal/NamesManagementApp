package classesPorteusesDeDonnees;

public class ResultatDeComparaison implements Comparable<ResultatDeComparaison> {
    private Nom nom1;
    private Nom nom2;
    private double score;

    public ResultatDeComparaison(Nom nom1, Nom nom2, double score) {
        this.nom1 = nom1;
        this.nom2 = nom2;
        this.score = score;
    }

    public Nom getNom1() {
        return nom1;
    }

    public Nom getNom2() {
        return nom2;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(ResultatDeComparaison other) {
        // Tri décroissant (les scores les plus élevés en premier)
        return Double.compare(other.score, this.score);
    }
}
