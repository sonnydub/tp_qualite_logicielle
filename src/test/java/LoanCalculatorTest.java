import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest {

    @Test
    void mensualitePositive() {
        LoanCalculator calc = new LoanCalculator();
        double result = calc.calculMensualite(10000, 0.05, 10);
        assertTrue(result > 0);
    }
    @Test
    void capitalNegatifDoitLeverException() {
        LoanCalculator calc = new LoanCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calc.calculMensualite(-1000, 0.05, 10));
    }

    @Test
    void tauxNegatifDoitLeverException() {
        LoanCalculator calc = new LoanCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calc.calculMensualite(1000, -0.01, 10));
    }

    @Test
    void dureeNulleDoitLeverException() {
        LoanCalculator calc = new LoanCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calc.calculMensualite(1000, 0.05, 0));
    }

    @Test
    void tauxZeroDoitRetournerCapitalDiviseParNombreDeMois() {
        LoanCalculator calc = new LoanCalculator();
        double result = calc.calculMensualite(1200, 0.0, 1);
        assertEquals(100.0, result);
    }

}

