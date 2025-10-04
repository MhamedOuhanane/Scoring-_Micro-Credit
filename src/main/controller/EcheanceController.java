package main.controller;

import main.service.interfaces.EcheanceService;
import main.service.interfaces.PersonService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EcheanceController {
    private final EcheanceService echeanceService;
    private final PersonService personService;

    public EcheanceController(EcheanceService echeanceService, PersonService personService) {
        this.echeanceService = echeanceService;
        this.personService = personService;
    }

    public void scheduleTraitementDate() {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        long initialDelay = this.initialeDelay();
        long period = TimeUnit.DAYS.toSeconds(1);

        schedule.scheduleAtFixedRate(() -> {
            try {
                this.echeanceService.traitementEcheance();
                this.personService.traitmentAnciennete();
            } catch (RuntimeException e) {
                System.out.println("❌ Erreur: " + e.getMessage());
            }
        }, initialDelay, period, TimeUnit.SECONDS);
    }

    private Long initialeDelay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0).withNano(0);

        if (!nextRun.isAfter(now)) nextRun = nextRun.plusDays(1);
        return Duration.between(now, nextRun).getSeconds();
    }
}
