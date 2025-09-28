package microservice.ERP.demo.depense;

import lombok.RequiredArgsConstructor;
import microservice.ERP.demo.budget.Budget;
import microservice.ERP.demo.budget.BudgetRepo;
import microservice.ERP.demo.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepenseService {
    private final DepenseRepo depenseRepo;
    private final BudgetRepo budgetRepo;

   public Long createDepense(Depense depense){
       return depenseRepo.save(depense).getId();
   }

    public Depense getDepenseById(Long id){
       return depenseRepo.findById(id).get();
    }
    public PageResponse<Depense> getAllDepenses(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateDepense").descending());
        Page<Depense> depenses = depenseRepo.findAll(pageable);
       return new PageResponse<>(
               depenses.stream().toList(),
               depenses.getNumber(),
               depenses.getSize(),
               depenses.getTotalElements(),
               depenses.getTotalPages(),
               depenses.isFirst(),
               depenses.isLast()
       );
    }
    public Long updateDepense(Long id, Depense depense){
       Depense newDepense = depenseRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Depense not found"));
       newDepense.setDescription(depense.getDescription());
       newDepense.setDateDepense(depense.getDateDepense());
       newDepense.setMontant(depense.getMontant());
       newDepense.setBudget(depense.getBudget());
       return depenseRepo.save(newDepense).getId();
    }

    public void deleteDepense(Long id){
       depenseRepo.deleteById(id);
    }

    public PageResponse<Depense> getDepensesByBudget(Long budgetId, int page, int size) {
        Budget budget = budgetRepo.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("dateDepense").descending());
        Page<Depense> depenses = depenseRepo.findByBudget(budget, pageable);

        return new PageResponse<>(
                depenses.getContent(),
                depenses.getNumber(),
                depenses.getSize(),
                depenses.getTotalElements(),
                depenses.getTotalPages(),
                depenses.isFirst(),
                depenses.isLast()
        );
    }


    public Double getTotalDepensesByBudget(Long budgetId){
       Budget budget=budgetRepo.findById(budgetId).orElseThrow(()-> new IllegalArgumentException("Budget not found"));
       return budget.getDepenses().stream().mapToDouble(Depense::getMontant).sum();
    }
}
