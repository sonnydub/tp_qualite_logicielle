package com.tp.sport;

import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private int id;
    private String nom;
    private Abonnement abonnement;
    private List<Reservation> reservations;

    public Adherent(int id, String nom, Abonnement abonnement, List<Reservation> reservations) {
        this.id          = id;
        this.nom         = nom;
        this.abonnement  = abonnement;
        this.reservations = new ArrayList<>();
    }

    public int getId()                        { return id; }
    public String getNom()                    { return nom; }
    public Abonnement getAbonnement()         { return abonnement; }
    public List<Reservation> getReservations(){ return reservations; }

    public Reservation reserver(Seance s) {
        Reservation r = new Reservation(s);
        reservations.add(r);
        return r;
    }

    public double depensesTotales() {
        double total = 0.0;
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE) {
                total += r.coutPrestation();
            }
        }
        return total;
    }

    public List<Reservation> reservationsFutures() {
        List<Reservation> futures = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getSeance().getDateHeure().isAfter(java.time.LocalDateTime.now())) {
                futures.add(r);
            }
        }
        return futures;
    }

    @Override
    public String toString() {
        return "Adherent{id=" + id + ", nom='" + nom + "', abonnement=" + abonnement + ", nbReservations=" + reservations.size() + "}";
    }
}
