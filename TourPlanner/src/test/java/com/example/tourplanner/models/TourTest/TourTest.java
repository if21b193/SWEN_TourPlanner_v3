package com.example.tourplanner.models.TourTest;

import com.example.tourplanner.models.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourTest {
    private final Tour tour = new Tour();

    @Test
    void testCalculateVeryHighChildFriendliness() {
        assertEquals("Very High", tour.calculateChildFriendliness(2.0f));
    }
    @Test
    void testCalculateHighChildFriendliness() {
        assertEquals("High", tour.calculateChildFriendliness(4.0f));

    }
    @Test
    void testCalculateMiddleChildFriendliness() {
        assertEquals("Middle", tour.calculateChildFriendliness(6.0f));

    }
    @Test
    void testCalculateLowChildFriendliness() {
        assertEquals("Low", tour.calculateChildFriendliness(8.0f));

    }
    @Test
    void testCalculateVeryLowChildFriendliness() {
        assertEquals("Very low", tour.calculateChildFriendliness(10.0f));
    }

    @Test
    public void testCalculateAccessibilityByCarVeryHigh() {
        assertEquals("Very High Accessibility", tour.calculateAccessibility("by car", 4.0f));
    }
    @Test
    public void testCalculateAccessibilityByCarHigh() {
        assertEquals("High Accessibility", tour.calculateAccessibility("by car", 6.0f));

    }
    @Test
    public void testCalculateAccessibilityByCarModerate() {
        assertEquals("Moderate Accessibility", tour.calculateAccessibility("by car", 8.0f));
    }

    @Test
    public void testCalculateAccessibilityFastestVeryHigh() {
        assertEquals("Very High Accessibility", tour.calculateAccessibility("fastest", 4.0f));

    }
    @Test
    public void testCalculateAccessibilityFastestHigh() {
        assertEquals("High Accessibility", tour.calculateAccessibility("fastest", 6.0f));
    }
    @Test
    public void testCalculateAccessibilityFastestModerate() {
        assertEquals("Moderate Accessibility", tour.calculateAccessibility("fastest", 8.0f));
    }
    @Test
    public void testCalculateAccessibilityBicycleHigh() {
        assertEquals("High Accessibility", tour.calculateAccessibility("Bicycle", 2.0f));

    }
    @Test
    public void testCalculateAccessibilityBicycleModerate() {
        assertEquals("Moderate Accessibility", tour.calculateAccessibility("Bicycle", 4.0f));
    }
    public void testCalculateAccessibilityBicycleLow() {
        assertEquals("Low Accessibility", tour.calculateAccessibility("Bicycle", 6.0f));
    }

    @Test
    public void testCalculateAccessibilityPedestrianModerate() {
        assertEquals("Moderate Accessibility", tour.calculateAccessibility("Pedestrian", 2.0f));

    }
    @Test
    public void testCalculateAccessibilityPedestrianLow() {
        assertEquals("Low Accessibility", tour.calculateAccessibility("Pedestrian", 4.0f));
    }
    @Test
    public void testCalculateAccessibilityPedestrianVeryLow() {
        assertEquals("Very Low Accessibility", tour.calculateAccessibility("Pedestrian", 6.0f));

    }
    @Test
    public void testCalculateAccessibilitySlowestModerate() {
        assertEquals("Moderate Accessibility", tour.calculateAccessibility("slowest", 2.0f));
    }

    @Test
    public void testCalculateAccessibilitySlowestLow() {
        assertEquals("Low Accessibility", tour.calculateAccessibility("slowest", 4.0f));

    }
    @Test
    public void testCalculateAccessibilitySlowestVeryLow() {
        assertEquals("Very Low Accessibility", tour.calculateAccessibility("slowest", 6.0f));
    }

    @Test
    public void testCalculateAccessibilityInvalid() {
        assertEquals("Invalid transportation", tour.calculateAccessibility("unicycle", 4.0f));
    }
}