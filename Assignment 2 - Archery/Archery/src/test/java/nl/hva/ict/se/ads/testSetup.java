package nl.hva.ict.se.ads;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class testSetup {

    public List<Archer> archers;


    @BeforeEach
    public void makeArchers() {
        archers = Archer.generateArchers(3);
    }
}
