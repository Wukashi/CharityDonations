package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.JpaCategoryService;
import pl.coderslab.charity.service.JpaDonationService;
import pl.coderslab.charity.service.JpaInstitutionService;

import javax.validation.Valid;

@Controller
public class DonationController {
    private final JpaDonationService donationService;
    private final JpaCategoryService categoryService;
    private final JpaInstitutionService institutionService;

    public DonationController(JpaDonationService donationService, JpaCategoryService categoryService, JpaInstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/createDonation")
    public String prepareDonation(Model model)
    {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("donation", new Donation());
        model.addAttribute("institutions", institutionService.getAllInstitutions());
        return "donation/create";
    }
    @PostMapping("/createDonation")
    public String addDonationToDB(@Valid Donation donation, BindingResult result)
    {
        if(result.hasErrors())
            return "donation/create";
        donationService.saveDonation(donation);
        return "redirect:/";
    }
}
