package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.JpaDonationService;

import javax.validation.Valid;

@Controller
public class DonationController {
    private final JpaDonationService donationService;

    public DonationController(JpaDonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/createDonation")
    public String prepareDonation(Model model)
    {
        model.addAttribute("donation", new Donation());
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
