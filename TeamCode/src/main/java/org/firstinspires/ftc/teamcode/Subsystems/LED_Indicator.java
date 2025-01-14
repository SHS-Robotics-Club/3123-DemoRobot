package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * The LED_Indicator class defines all the Rev LED Indicator components for the robot.
 *
 * PUBLIC METHODS:
 *     LED_Indicator(hardwareMap) - constructor for instantiating a LED_Indicator
 *     void frontLED_red_on() - turns on the front red LED indicator
 *     void frontLED_red_off() - turns off the front red LED indicator
 *     void frontLED_green_on() - turns on the front green LED indicator
 *     void frontLED_green_off() - turns off the front green LED indicator
 *     void rearLED_red_on() - turns on the rear red LED indicator
 *     void rearLED_red_off() - turns off the rear red LED indicator
 *     void rearLED_green_on() - turns on the rear green LED indicator
 *     void rearLED_green_off() - turns off the rear green LED indicator
 *
 * VERSION   DATE     WHO  DETAIL
 * 00.01.00  14Jan25  SEB  Initial release
 *
 */
public class LED_Indicator {

    private LED frontLED_red, frontLED_green;
    private LED rearLED_red, rearLED_green;

    public LED_Indicator(HardwareMap hardwareMap) {
        // Initialize the LEDs from the hardware map
        frontLED_red = hardwareMap.get(LED.class, "ledFrontRed");
        frontLED_green = hardwareMap.get(LED.class, "ledFrontGreen");
        rearLED_red = hardwareMap.get(LED.class, "ledRearRed");
        rearLED_green = hardwareMap.get(LED.class, "ledRearGreen");
    }

    // Method to turn on the front red LED
    public void frontLED_red_on() {
        frontLED_red.on();
    }

    // Method to turn off the front red LED
    public void frontLED_red_off() {
        frontLED_red.off();
    }

    // Method to turn on the front green LED
    public void frontLED_green_on() {
        frontLED_green.on();
    }

    // Method to turn off the front green LED
    public void frontLED_green_off() {
        frontLED_green.off();
    }

    // Method to turn on the rear red LED
    public void rearLED_red_on() {
        rearLED_red.on();
    }

    // Method to turn off the rear red LED
    public void rearLED_red_off() {
        rearLED_red.off();
    }

    // Method to turn on the rear green LED
    public void rearLED_green_on() {
        rearLED_green.on();
    }

    // Method to turn off the rear green LED
    public void rearLED_green_off() {
        rearLED_green.off();
    }
}
