package com.tp.sport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Seance seance;
    private Prestation sauna;
    private Prestation coach;

    @BeforeEach
    void setUp() {
        seance = new Seance(1, "BodyPump", LocalDateTime.of(2026, 6, 1, 18, 0), 10);
        sauna  = new Prestation("SAUNA", "Accès sauna", 5.80);
        coach  = new Prestation("COACH", "Cours perso",  150.50);
    }

    //  Statut initial

    @Test
    @DisplayName("Nouvelle réservation → statut CONFIRMEE")
    void newReservation_shouldBeConfirmee() {
        Reservation r = new Reservation(seance);
        assertEquals(StatutReservation.CONFIRMEE, r.getStatut());
    }

    @Test
    @DisplayName("Nouvelle réservation → liste de prestations vide")
    void newReservation_prestationsListEmpty() {
        Reservation r = new Reservation(seance);
        assertTrue(r.getPrestations().isEmpty());
    }

    // ajouterPrestation()

    @Test
    @DisplayName("ajouterPrestation : ajoute la prestation à la liste")
    void ajouterPrestation_shouldAddToList() {
        Reservation r = new Reservation(seance);
        r.ajouterPrestation(sauna);
        assertEquals(1, r.getPrestations().size());
        assertTrue(r.getPrestations().contains(sauna));
    }

    @Test
    @DisplayName("ajouterPrestation null → ne plante pas et liste inchangée")
    void ajouterPrestation_null_shouldNotThrow() {
        Reservation r = new Reservation(seance);
        assertDoesNotThrow(() -> r.ajouterPrestation(null));
        assertTrue(r.getPrestations().isEmpty());
    }

    @Test
    @DisplayName("ajouterPrestation après annulation → ignorée")
    void ajouterPrestation_afterAnnulation_ignored() {
        Reservation r = new Reservation(seance);
        r.annuler();
        r.ajouterPrestation(sauna);
        assertTrue(r.getPrestations().isEmpty());
    }

    //  coutPrestation()
    @Test
    @DisplayName("coutPrestation sans prestation → 0.0")
    void coutPrestation_noPrestations_returns0() {
        Reservation r = new Reservation(seance);
        assertEquals(0.0, r.coutPrestation());
    }

    @Test
    @DisplayName("coutPrestation sauna(5.80) + coach(150.50) → 156.30")
    void coutPrestation_sauna_coach_returns156_30() {
        Reservation r = new Reservation(seance);
        r.ajouterPrestation(sauna);
        r.ajouterPrestation(coach);
        assertEquals(156.30, r.coutPrestation(), 0.001);
    }

    // annuler()

    @Test
    @DisplayName("annuler → statut passe à ANNULEE")
    void annuler_shouldChangeStatutAnnulee() {
        Reservation r = new Reservation(seance);
        r.annuler();
        assertEquals(StatutReservation.ANNULEE, r.getStatut());
    }
}
