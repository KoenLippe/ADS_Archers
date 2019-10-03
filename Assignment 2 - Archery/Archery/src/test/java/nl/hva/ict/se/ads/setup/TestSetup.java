package nl.hva.ict.se.ads.setup;

import nl.hva.ict.se.ads.Archer;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TestSetup {

    public List<Archer> archers;


    @BeforeEach
    public void makeArchers() {
        archers = Archer.generateArchers(3);
    }


}
