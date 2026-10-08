package com.vedant.smartcomplaintmanagement.controller;

import com.vedant.smartcomplaintmanagement.model.Complaint;
import com.vedant.smartcomplaintmanagement.repository.ComplaintRepository;
import com.vedant.smartcomplaintmanagement.service.ClassifierService;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintRepository complaintRepository;
    private final ClassifierService classifierService;

    public ComplaintController(
            ComplaintRepository complaintRepository,
            ClassifierService classifierService) {

        this.complaintRepository = complaintRepository;
        this.classifierService = classifierService;
    }


    // ============================
    // SUBMIT COMPLAINT
    // ============================

    @PostMapping
    public Complaint submitComplaint(
            @RequestBody Complaint complaint) {

        String category =
                classifierService.classifyCategory(
                        complaint.getDescription());

        String priority =
                classifierService.determinePriority(
                        complaint.getDescription());

        String department =
                classifierService.determineDepartment(
                        category);

        complaint.setCategory(category);
        complaint.setPriority(priority);
        complaint.setDepartment(department);
        complaint.setStatus("OPEN");

        return complaintRepository.save(complaint);
    }


    // ============================
    // GET ALL COMPLAINTS
    // ============================

    @GetMapping
    public java.util.List<Complaint> getAllComplaints() {

        return complaintRepository.findAll();
    }


    // ============================
    // GET COMPLAINT BY ID
    // ============================

    @GetMapping("/{id}")
    public Complaint getComplaintById(
            @PathVariable Long id) {

        return complaintRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Complaint not found"));
    }


    // ============================
    // UPDATE COMPLAINT STATUS
    // ============================

    @PutMapping("/{id}/status")
    public Complaint updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Complaint not found"));

        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }


    // ============================
    // SUBMIT FEEDBACK
    // ============================

    @PostMapping("/{id}/feedback")
    public Complaint submitFeedback(
            @PathVariable Long id,
            @RequestParam Integer rating,
            @RequestParam String comment) {

        Complaint complaint =
                complaintRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Complaint not found"));

        if (!"RESOLVED".equals(complaint.getStatus())) {

            throw new RuntimeException(
                    "Feedback can only be submitted for resolved complaints");
        }

        if (rating < 1 || rating > 5) {

            throw new RuntimeException(
                    "Rating must be between 1 and 5");
        }

        complaint.setFeedbackRating(rating);
        complaint.setFeedbackComment(comment);

        return complaintRepository.save(complaint);
    }
}