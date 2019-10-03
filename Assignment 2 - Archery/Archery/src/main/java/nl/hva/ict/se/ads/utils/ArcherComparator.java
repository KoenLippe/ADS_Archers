package nl.hva.ict.se.ads.utils;

import nl.hva.ict.se.ads.Archer;

import java.util.Comparator;

public class ArcherComparator implements Comparator<Archer> {

    @Override
    public int compare(Archer archer2, Archer archer1) {
        if (archer1.getTotalScore() == archer2.getTotalScore()) {
            if (archer1.getWeightedScore() == archer2.getWeightedScore()) {

                return archer1.getId() - archer2.getId();
            }

            return archer1.getWeightedScore() - archer2.getWeightedScore();
        }

        return archer1.getTotalScore() - archer2.getTotalScore();

    }
}
