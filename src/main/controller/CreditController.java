package main.controller;

import main.enums.EnumDecision;
import main.model.Credit;
import main.service.interfaces.CreditService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
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

    public Map<String , Object> getAllETUDEMANUELLE() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Credit> credits = this.creditService.getAllCredits().stream()
                    .filter(credit -> credit.getDecision().equals(EnumDecision.ETUDEMANUELLE))
                    .collect(Collectors.toList());
            StringBuilder list = new StringBuilder("✅ trouver les credits qui need validation: ");
            for (Credit credit : credits ) {
                list.append("\n ").append(credit.toString());
            }
            result.put("message", list.toString());
            result.put("credits", credits);
            return result;
        } catch (RuntimeException e) {
            result.put("erreur", "❌ Erreur: " + e.getMessage());
            return result;
        }
    }
    private Credit instancedEmp(Map<String , Object> data) {
        return new Credit(
                (LocalDateTime) data.get("dateCredit"), (Double) data.get("montantDemande"),
                (Double) data.get("montantOctroye"), (String) data.get("typeCredit"), (Integer) data.get("person_id")
        );
    }

}
