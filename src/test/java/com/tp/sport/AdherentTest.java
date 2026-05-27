package com.tp.sport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdherentTest {

    private Adherent adherent;
    private Seance seanceFuture;
    private Seance seancePassee;

    @BeforeEach
    void setUp() {
        AbonnementBasic abo = new AbonnementBasic("B1", LocalDate.of(2025, 1, 1), 12, 30.0);
        adherent     = new Adherent(1, "Alice", abo, null);
        seanceFuture = new Seance(1, "Yoga",     LocalDateTime.now().plusDays(7),  20);
        seancePassee = new Seance(2, "BodyPump", LocalDateTime.now().minusDays(7), 10);
    }

    // BUG 2 vérifié : nom correctement initialisé

    @Test
    @DisplayName("Adherent : nom correctement initialisé")
    void adherent_nomCorrectementInitialise() {
        assertEquals("Alice", adherent.getNom());
    }

    //  depensesTotales()

    @Test
    @DisplayName("depensesTotales : aucune réservation → 0.0")
    void depensesTotales_noResa_returnsZero() {
        assertEquals(0.0, adherent.depensesTotales());
    }

    @Test
    @DisplayName("depensesTotales : une réservation CONFIRMEE avec prestations → somme correcte")
    void depensesTotales_oneConfirmed_returnsTotal() {
        Reservation r = adherent.reserver(seanceFuture);
        r.ajouterPrestation(new Prestation("SAUNA", "Sauna", 5.80));
        r.ajouterPrestation(new Prestation("COACH", "Coach", 150.50));
        assertEquals(156.30, adherent.depensesTotales(), 0.001);
    }

    @Test
    @DisplayName("depensesTotales : une réservation ANNULEE → non comptée")
    void depensesTotales_annulee_notCounted() {
        Reservation r = adherent.reserver(seanceFuture);
        r.ajouterPrestation(new Prestation("SAUNA", "Sauna", 5.80));
        r.annuler();
        assertEquals(0.0, adherent.depensesTotales());
    }

    @Test
    @DisplayName("depensesTotales : mixte CONFIRMEE + ANNULEE → seules les confirmées comptent")
    void depensesTotales_mixedStatuts_onlyConfirmed() {
        Reservation r1 = adherent.reserver(seanceFuture);
        r1.ajouterPrestation(new Prestation("SAUNA", "Sauna", 10.0));

        Reservation r2 = adherent.reserver(seancePassee);
        r2.ajouterPrestation(new Prestation("COACH", "Coach", 50.0));
        r2.annuler();

        assertEquals(10.0, adherent.depensesTotales(), 0.001);
    }

    // reservationsFutures()

    @Test
    @DisplayName("reservationsFutures : retourne uniquement les séances futures")
    void reservationsFutures_returnsFutureOnly() {
        adherent.reserver(seanceFuture);
        adherent.reserver(seancePassee);

        List<Reservation> futures = adherent.reservationsFutures();

        assertEquals(1, futures.size());
        assertEquals(seanceFuture, futures.get(0).getSeance());
    }

    @Test
    @DisplayName("reservationsFutures : aucune réservation → liste vide")
    void reservationsFutures_noResa_returnsEmpty() {
        assertTrue(adherent.reservationsFutures().isEmpty());
    }

    //  reserver()

    @Test
    @DisplayName("reserver : crée une réservation CONFIRMEE et l'ajoute à la liste")
    void reserver_shouldCreateConfirmedReservation() {
        Reservation r = adherent.reserver(seanceFuture);
        assertEquals(StatutReservation.CONFIRMEE, r.getStatut());
        assertEquals(1, adherent.getReservations().size());
    }
}
