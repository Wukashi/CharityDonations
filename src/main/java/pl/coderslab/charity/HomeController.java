package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.JpaDonationService;
import pl.coderslab.charity.service.JpaInstitutionService;


@Controller
public class HomeController {
    private final JpaInstitutionService institutionService;
    private final JpaDonationService donationService;

    public HomeController(JpaInstitutionService institutionService, JpaDonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @RequestMapping("/")
    public String homeAction(Model model){

        model.addAttribute("allInstitutions", institutionService.getAllInstitutions());
        model.addAttribute("donationsProvided", donationService.getNumberOfDonationsProvided());
        return "index";
    }
}
