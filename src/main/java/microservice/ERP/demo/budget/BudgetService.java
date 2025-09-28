package microservice.ERP.demo.budget;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import microservice.ERP.demo.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepo budgetRepo;

    public Long createBudget(Budget budget){
        return budgetRepo.save(budget).getId();
    }
    public Budget getBudgetById(Long id){
        return budgetRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Budget not found"));
    }
    public PageResponse<Budget> getAllBudgets(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateDebut").descending());
        Page<Budget> budgets = budgetRepo.findAll(pageable);
        return new PageResponse<>(
                budgets.stream().toList(),
                budgets.getNumber(),
                budgets.getSize(),
                budgets.getTotalElements(),
                budgets.getTotalPages(),
                budgets.isFirst(),
                budgets.isLast()
        );
    }
    public Long updateBudget(Long id, Budget budget){
        Budget budgetUpdate = budgetRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Budget not found"));
        budgetUpdate.setNom(budget.getNom());
        budgetUpdate.setMontant(budget.getMontant());
        budgetUpdate.setDateDebut(budget.getDateDebut());
        budgetUpdate.setDateFin(budget.getDateFin());
        budgetUpdate.setIdProjet(budget.getIdProjet());
        return budgetRepo.save(budgetUpdate).getId();
    }

    public void deleteBudget(Long id){
        budgetRepo.deleteById(id);
    }


}

 /* public Budget getBudgetsByProjet(Long projetId){
        return
    }*/