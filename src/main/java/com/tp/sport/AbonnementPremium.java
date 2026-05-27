package com.tp.sport;

import java.time.LocalDate;

public class AbonnementPremium extends Abonnement {
    private int creditsCoach;

    public AbonnementPremium(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel, int creditsCoach) {
        super(reference, dateDebut, dureeMois, prixMensuel);
        this.creditsCoach = creditsCoach;
    }

    public int getCreditsCoach()                   { return creditsCoach; }
    public void setCreditsCoach(int creditsCoach)  { this.creditsCoach = creditsCoach; }

    @Override
    public boolean permetAccesSauna() { return true; }

    @Override
    public int creditsCoachInclus()   { return creditsCoach; }
}
