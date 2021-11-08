package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaDonationService implements DonationService{
    private final DonationRepository donationRepository;

    public JpaDonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public int getNumberOfDonationsProvided(){
        List<Donation> allDonations = getAllDonations();
        List<Donation> filteredDonations = allDonations.stream()
                .filter(d -> LocalDate.now().isAfter(d.getPickUpDate()) || (LocalDate.now().equals(d.getPickUpDate()) && LocalTime.now().isAfter(d.getPicUpTime())))
                .collect(Collectors.toList());
        return filteredDonations.stream().mapToInt(Donation::getQuantity).sum();
    }
}
