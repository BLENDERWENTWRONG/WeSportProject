package com.example.we_sport.Controllers;

import javafx.fxml.FXML;
import org.controlsfx.control.Rating;

public class avisController {

    @FXML
    private Rating starRating;



    @FXML
    private void submitAvis() {
        double ratingValue = starRating.getRating();
        // Add your code to handle the submission of the rating (store it, process it, etc.)
    }
}
