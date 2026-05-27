package com.tp.sport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AbonnementTest {

    // coutTotal()

    @Test
    @DisplayName("coutTotal : 3 mois à 30€ → 90€")
    void coutTotal_3mois_30euros_returns90() {
        // ARRANGE
        AbonnementBasic abo = new AbonnementBasic("B1", LocalDate.of(2025, 1, 1), 3, 30.0);
        // ACT
        double result = abo.coutTotal();
        // ASSERT
        assertEquals(90.0, result);
    }

    @Test
    @DisplayName("coutTotal : 12 mois à 25€ → 300€")
    void coutTotal_12mois_25euros_returns300() {
        AbonnementBasic abo = new AbonnementBasic("B2", LocalDate.of(2025, 1, 1), 12, 25.0);
        assertEquals(300.0, abo.coutTotal());
    }

    @ParameterizedTest(name = "{0} mois × {1}€ = {2}€")
    @DisplayName("coutTotal : plusieurs combinaisons durée/prix")
    @CsvSource({"1, 50.0, 50.0", "6, 40.0, 240.0", "24, 20.0, 480.0"})
    void coutTotal_parametric(int duree, double prix, double attendu) {
        AbonnementBasic abo = new AbonnementBasic("BX", LocalDate.of(2025, 1, 1), duree, prix);
        assertEquals(attendu, abo.coutTotal());
    }

    // dateFin()

    @Test
    @DisplayName("dateFin : début janvier 2025 + 3 mois → 1er avril 2025")
    void dateFin_startJan2025_3mois_returnsApril() {
        AbonnementBasic abo = new AbonnementBasic("B1", LocalDate.of(2025, 1, 1), 3, 30.0);
        assertEquals(LocalDate.of(2025, 4, 1), abo.dateFin());
    }

    //  AbonnementBasic

    @Test
    @DisplayName("AbonnementBasic : n'accorde pas l'accès sauna")
    void basic_saunaNonAutorise() {
        AbonnementBasic abo = new AbonnementBasic("B1", LocalDate.of(2025, 1, 1), 1, 30.0);
        assertFalse(abo.permetAccesSauna());
    }

    @Test
    @DisplayName("AbonnementBasic : 0 crédit coach")
    void basic_zeroCreditCoach() {
        AbonnementBasic abo = new AbonnementBasic("B1", LocalDate.of(2025, 1, 1), 1, 30.0);
        assertEquals(0, abo.creditsCoachInclus());
    }

    //  AbonnementPremium

    @Test
    @DisplayName("AbonnementPremium : accorde l'accès sauna")
    void premium_saunAutorise() {
        AbonnementPremium abo = new AbonnementPremium("P1", LocalDate.of(2025, 1, 1), 1, 50.0, 5);
        assertTrue(abo.permetAccesSauna());
    }

    @Test
    @DisplayName("AbonnementPremium : retourne le bon nombre de crédits coach")
    void premium_creditsCoachInclus() {
        AbonnementPremium abo = new AbonnementPremium("P1", LocalDate.of(2025, 1, 1), 1, 50.0, 5);
        assertEquals(5, abo.creditsCoachInclus());
    }
}
