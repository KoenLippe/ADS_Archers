package nl.hva.ict.se.ads.utils;

import nl.hva.ict.se.ads.Archer;

import java.util.Comparator;

public class ArcherComparator implements Comparator<Archer> {

    @Override
    public int compare(Archer archer1, Archer archer2) {
        if (archer2.getTotalScore() == archer1.getTotalScore()) {
            if (archer2.getWeightedScore() == archer1.getWeightedScore()) {

                return archer2.getId() - archer1.getId();
            }

            return archer2.getWeightedScore() - archer1.getWeightedScore();
        }

        return archer2.getTotalScore() - archer1.getTotalScore();

    }
}
