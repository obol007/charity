package pl.coderslab.charity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionController {

    InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @ModelAttribute("institutions")
    public List<Institution> allInstitutions() {
        return institutionService.findAllOderByIdDesc();
    }

    @GetMapping
    public String institutions() {
        return "admin/institutions";
    }


    @GetMapping("/active/{id}")
    public String institutionsActivation(@PathVariable Long id, Model model) {
        InstitutionDTO institutionDTO = institutionService.findById(id);
        institutionDTO.setActive(!institutionDTO.getActive());
        institutionService.save(institutionDTO);
        return "admin/institutions";
    }

    @GetMapping("/add")
    public String addInstitution(Model model) {
        InstitutionDTO institutionDTO = new InstitutionDTO();
        model.addAttribute("newInstitution", institutionDTO);
        return "admin/institutions";
    }

    @PostMapping("/add")
    public String addingInstitution(@Valid @ModelAttribute("newInstitution") InstitutionDTO institutionDTO,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/institutions";
        }
        institutionService.addOrUpdate(institutionDTO);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/edit/{id}")
    public String editInstitution(Model model, @PathVariable Long id) {
        InstitutionDTO institutionDTO = institutionService.findInstitution(id);
        model.addAttribute("newInstitution", institutionDTO);
        return "admin/institutions";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable Long id, Model model) {
        InstitutionDTO institutionDTO = institutionService.findInstitution(id);
        model.addAttribute("institutionToDelete", institutionDTO);
        if (institutionDTO.getActive()) {
            model.addAttribute("isActive", true);
            return "admin/institutions";
        } else {
            model.addAttribute("isActive", false);
            return "admin/institutions";
        }
    }

    @GetMapping("/delete/confirmed/{id}")
    public String deletingInstitution(@PathVariable Long id, Model model) {
        institutionService.delete(id);
        model.addAttribute("showInstitutions", true);
        return "redirect:/admin/institutions";
    }

}


