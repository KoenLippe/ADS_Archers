package nl.hva.ict.se.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 * <p>
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer {
    public static int MAX_ARROWS = 3;
    public static int MAX_ROUNDS = 10;
    private static Random randomizer = new Random();
    private static int last_ID = 0;
    private final int id; // Once assigned a value is not allowed to change.
    private final int STARTING_ID = 135788;
    private int[][] roundPoints;


    private String firstname, lastname;


    /**
     * Constructs a new instance of bowman and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword.Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName  the archers surname.
     */
    Archer(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.roundPoints = new int[MAX_ROUNDS][MAX_ARROWS];


        if (last_ID == 0) {
            this.id = STARTING_ID;
            last_ID = STARTING_ID;
        } else {
            this.id = ++last_ID;
        }
    }

    /**
     * This methods creates a List of archers.
     *
     * @param nrOfArchers the number of archers in the list.
     * @return
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname());
            letArcherShoot(archer, nrOfArchers % 100 == 0);
            archers.add(archer);
        }
        return archers;

    }

    /**
     * This methods creates a Iterator that can be used to generate all the required archers. If you implement this
     * method it is only allowed to create an instance of Archer inside the next() method!
     *
     * <b>THIS METHODS IS OPTIONAL</b>
     *
     * @param nrOfArchers the number of archers the Iterator will create.
     * @return
     */
    public static Iterator<Archer> generateArchers(long nrOfArchers) {
        return null;
    }

    private static void letArcherShoot(Archer archer, boolean isBeginner) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootArrows(isBeginner ? 0 : 1));
        }
    }

    private static int[] shootArrows(int min) {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shoot(min);
        }
        return points;
    }

    private static int shoot(int min) {
        return Math.max(min, randomizer.nextInt(11));
    }

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round  the round for which to register the points.
     * @param points the points shot during the round.\
     * @author Yan Lanna Alexandre & Koen Lippe
     */
    public void registerScoreForRound(int round, int[] points) {

        for (int i = 0; i < points.length; i++) {
            this.roundPoints[round][i] = points[i];
        }
    }


    /**
     * Adds up all scores and returns it
     *
     * @return Total score over all rounds
     * @author Yan Lanna Alexandre
     */
    public int getTotalScore() {
        int totalScore = 0;

        for (int ronde = 0; ronde < MAX_ROUNDS; ronde++) {
            for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
                totalScore += roundPoints[ronde][arrow];
            }
        }

        return totalScore;
    }

    /**
     * Rerturns weighted score as explained in assignment
     *
     * @return weighted score
     * @author Yan Lanna Alexandre & Koen Lippe
     */
    public int getWeightedScore() {
        int total = 0;


        for (int ronde = 0; ronde < MAX_ROUNDS; ronde++) {
            for (int point = 0; point < MAX_ARROWS; point++) {
                if (roundPoints[ronde][point] == 0) {
                    // Is zero
                    total -= 7;
                } else {
                    total += roundPoints[ronde][point] + 1;
                }

            }
        }

        return total;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d (%d/%d) %s %s",
                this.id,
                this.getTotalScore(),
                this.getWeightedScore(),
                this.firstname,
                this.lastname);
    }






}
