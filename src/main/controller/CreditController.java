package main.controller;

import main.enums.EnumDecision;
import main.model.Credit;
import main.model.Echeance;
import main.model.Incident;
import main.service.interfaces.CreditService;
import main.service.interfaces.EcheanceService;
import main.service.interfaces.IncidentService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreditController {
    private final CreditService creditService;
    private final IncidentService incidentService;

    public CreditController(CreditService creditService, IncidentService incidentService) {
        this.creditService = creditService;
        this.incidentService = incidentService;
    }

    public Map<String, Object> create(Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();
        try {
            for (String key : data.keySet()) if (data.get(key) == null) throw new RuntimeException("La valeur de " + key + " ne peut pas être null");
            Credit credit = this.creditService.ajouterCredit(this.instancedEmp(data));
            result.put("credit", credit);
            result.put("message", "✅ Le Credit est ajouter avec success");
            result.put("erreur", "");

            return result;
        } catch (RuntimeException e) {
            result.put("erreur", "❌ Erreur: " + e.getMessage());
            result.put("credit", "");
            result.put("message", "");
            return result;
        }
    }

    public String find(Integer id) {
        try {
            Credit credit = this.creditService.findCredit(id);
            if (credit != null) {
                return credit.toString();
            } else {
                return "⚠️ Aucun credit trouvé avec ID " + id;
            }
        } catch (RuntimeException e) {
            return "❌ Erreur: " + e.getMessage();
        }
    }

    public Map<String , Object> update(Integer id, Map<String, Object> updates) {
        Map<String, Object> result = new HashMap<>();
        try {
            Credit credit = this.creditService.updateCredit(id, updates);

            result.put("message", "✅ Les information de ce credit est modifier avec success");
            result.put("credit", credit);
            result.put("erreur", "");
            return result;
        } catch (RuntimeException e) {
            result.put("erreur", "❌ Erreur: " + e.getMessage());
            result.put("credit", null);
            result.put("message", "");
            return result;
        }
    }

    public String getAllCredit() {
        try {
            List<Credit> credits = this.creditService.getAllCredits();
            if (credits.isEmpty()) return  "⚠️ Aucun credit trouvé dans le systeme";
            StringBuilder list = new StringBuilder("✅ trouver tous les credits: ");
            for (Credit credit : credits ) {
                list.append("\n ").append(credit.toString());
            }
            return list.toString();
        } catch (RuntimeException e) {
            return "❌ Erreur: " + e.getMessage();
        }
    }

    public Map<String , Object> getAllETUDEMANUELLE() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Credit> credits = this.creditService.getAllCredits().stream()
                    .filter(credit -> credit.getDecision().equals(EnumDecision.ETUDEMANUELLE))
                    .collect(Collectors.toList());
            result.put("credits", credits);
            result.put("message", "⚠️ Aucun credit trouvé dans le systeme");
            result.put("erreur", "");
            if (credits.isEmpty()) return  result;
            StringBuilder list = new StringBuilder("✅ trouver les credits qui need validation: ");
            for (Credit credit : credits ) {
                list.append("\n ").append(credit.toString());
            }
            result.put("message", list.toString());
            return result;
        } catch (RuntimeException e) {
            result.put("erreur", "❌ Erreur: " + e.getMessage());
            result.put("message", "");
            result.put("credits", "");
            return result;
        }
    }
    private Credit instancedEmp(Map<String , Object> data) {
        return new Credit(
                (LocalDateTime) data.get("dateCredit"), (Double) data.get("montantDemande"),
                (Double) data.get("montantOctroye"), (String) data.get("typeCredit"), (Integer) data.get("person_id")
        );
    }

    public String getEcheances(Integer id) {
        try {
            List<Echeance> echeances = this.creditService.selectCreditEcheances(id);
            if (echeances.isEmpty()) return  "⚠️ Aucun echeance trouvé pour ce credit";
            StringBuilder list = new StringBuilder("✅ trouver les Echeance de ce credit: ");
            for (Echeance echeance : echeances ) {
                list.append("\n ").append(echeance.toString());
            }
            return list.toString();
        } catch (RuntimeException e) {
            return "❌ Erreur: " + e.getMessage();
        }
    }

    public String getIncedents(Integer id) {
        try {
            List<Incident> incidents = this.incidentService.getCreditIncidents(id);
            if (incidents.isEmpty()) return  "⚠️ Aucun incident trouvé pour ce credit";
            StringBuilder list = new StringBuilder("✅ trouver les Incident de ce credit: ");
            for (Incident incident : incidents ) {
                list.append("\n ").append(incident.toString());
            }
            return list.toString();
        } catch (RuntimeException e) {
            return "❌ Erreur: " + e.getMessage();
        }

    }

    public Echeance getEcheanceRemb(Integer id) {
        try {
            List<Echeance> echeances = this.creditService.selectCreditEcheances(id);
            return echeances.stream().filter(echeance -> echeance.getDatePaiement() == null).findFirst().orElse(null);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
