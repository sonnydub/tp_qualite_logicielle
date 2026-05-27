package com.tp.sport;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Seance seance;
    private List<Prestation> prestations;
    private StatutReservation statut;

    public Reservation(Seance seance) {
        this.seance      = seance;
        this.prestations = new ArrayList<>();
        this.statut      = StatutReservation.CONFIRMEE;
    }

    public Seance getSeance()                    { return seance; }
    public List<Prestation> getPrestations()     { return prestations; }
    public StatutReservation getStatut()         { return statut; }

    public void ajouterPrestation(Prestation p) {
        if (p != null && statut == StatutReservation.CONFIRMEE) {
            prestations.add(p);
        }
    }

    public double coutPrestation() {
        double total = 0.0;
        for (Prestation p : prestations) {
            total += p.getPrix();
        }
        return total;
    }

    public void annuler() {
        this.statut = StatutReservation.ANNULEE;
    }

    @Override
    public String toString() {
        return "Reservation{seance=" + seance + ", prestations=" + prestations + ", statut=" + statut + ", cout=" + coutPrestation() + "}";
    }
}
