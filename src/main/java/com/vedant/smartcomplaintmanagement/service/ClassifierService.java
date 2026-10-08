package com.vedant.smartcomplaintmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class ClassifierService {

    public String classifyCategory(String description) {

        String text = description.toLowerCase();

        if (text.contains("electricity")
                || text.contains("light")
                || text.contains("fan")
                || text.contains("power")) {
            return "Electricity";
        }

        if (text.contains("water")
                || text.contains("tap")
                || text.contains("leakage")) {
            return "Water";
        }

        if (text.contains("hostel")
                || text.contains("room")
                || text.contains("bed")) {
            return "Hostel";
        }

        if (text.contains("internet")
                || text.contains("wifi")
                || text.contains("network")) {
            return "Internet";
        }

        if (text.contains("academic")
                || text.contains("exam")
                || text.contains("lecture")
                || text.contains("teacher")) {
            return "Academic";
        }

        return "General";
    }

    public String determinePriority(String description) {

        String text = description.toLowerCase();

        if (text.contains("urgent")
                || text.contains("fire")
                || text.contains("danger")) {
            return "HIGH";
        }

        if (text.contains("please")
                || text.contains("suggestion")
                || text.contains("request")) {
            return "LOW";
        }

        return "MEDIUM";
    }

    public String determineDepartment(String category) {

        return switch (category) {

            case "Electricity" -> "Electrical Dept";
            case "Water" -> "Water Department";
            case "Hostel" -> "Hostel Department";
            case "Internet" -> "IT Department";
            case "Academic" -> "Academic Department";
            default -> "General Department";
        };
    }
}