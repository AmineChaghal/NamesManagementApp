package temp;

import java.util.List;

public interface DecomposeurDeNoms {
    List<Nom> construire(List<Pair<Integer, String>> donneesBrutes);
}

