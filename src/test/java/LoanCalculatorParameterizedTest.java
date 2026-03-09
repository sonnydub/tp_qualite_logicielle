import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorParameterizedTest {

    @ParameterizedTest
    @CsvSource({
            "1000, 0.05, 5",
            "5000, 0.03, 10",
            "10000, 0.04, 20"
    })
    void mensualiteToujoursPositive(double capital, double taux, int duree) {
        LoanCalculator calc = new LoanCalculator();
        double result = calc.calculMensualite(capital, taux, duree);
        assertTrue(result > 0);
    }
}
