package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LED_Indicator;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "DemoDriveLights", group = "Development")
public class DemoDriveLights extends OpMode {

    // Drivetrain constants
    public static final double DRIVETRAIN_LOW_POWER_FACTOR = 0.4;
    public static final double DRIVETRAIN_HIGH_POWER_FACTOR = 0.8;
    private static final int TELEMETRY_UPDATE_FREQUENCY = 20; // Update every 20 iterations

    private Drivetrain drivetrain;
    private LED_Indicator leds; // Create an instance of the LED subsystem
    private boolean lowPowerMode = true;
    private int telemetryUpdateCounter = 0;

    // ElapsedTime for blinking LEDs
    private ElapsedTime timer = new ElapsedTime();
    private int blinkPhase = 0;

    @Override
    public void init() {
        // Build robot
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        leds = new LED_Indicator(hardwareMap); // Initialize the LED subsystem

        // Initialize subsystems
        drivetrain.init();

        // Start the timer for blinking LEDs
        timer.reset();

        // Report status
        telemetry.addData("Status", "Initialized");
        drivetrain.getTelemetry();
        telemetry.update();
    }

    @Override
    public void init_loop() {
        // Blink LEDs continuously until the START button is pressed
        if (!gamepad1.start) { // Continue blinking until the match starts
            blinkLEDs(); // Blink LEDs during INIT phase
        }

        // Report status
        telemetry.addData("Status", "Initialized");
        drivetrain.getTelemetry();
        telemetry.update();
    }

    @Override
    public void loop() {
        // Check if the B button is pressed to activate higher power factor
        if (gamepad1.b) {
            if (!lowPowerMode) {
                drivetrain.setPowerFactor(DRIVETRAIN_LOW_POWER_FACTOR); // Set higher power factor
                lowPowerMode = true;
            }
        } else if (gamepad1.a) {
            if (lowPowerMode) {
                drivetrain.setPowerFactor(DRIVETRAIN_HIGH_POWER_FACTOR); // Set lower power factor
                lowPowerMode = false;
            }
        }

        // Get joystick inputs for driving (forward/backward and turning)
        double forward = -gamepad1.left_stick_y; // Y-axis for forward/backward
        double turn = gamepad1.left_stick_x;     // X-axis for turning

        // Use the drivetrain to drive the robot
        drivetrain.drive(forward, turn);

        // LED control logic based on left joystick activity
        if (Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.left_stick_x) > 0.1) {
            // If the left joystick is being moved, turn on green LEDs
            leds.frontLED_green_on();
            leds.rearLED_green_on();
            leds.frontLED_red_off();
            leds.rearLED_red_off();
        } else {
            // If the left joystick is not moving, turn on red LEDs
            leds.frontLED_red_on();
            leds.rearLED_red_on();
            leds.frontLED_green_off();
            leds.rearLED_green_off();
        }

        // Update telemetry every TELEMETRY_UPDATE_FREQUENCY iterations
        if (telemetryUpdateCounter >= TELEMETRY_UPDATE_FREQUENCY) {
            telemetryUpdateCounter = 0;
            drivetrain.getTelemetry();
            telemetry.update();
        } else {
            telemetryUpdateCounter++;
        }
    }

    /**
     * Blinks the LEDs in red, green, and amber during the INIT phase.
     * This will blink LEDs continuously until the start of the match.
     */
    private void blinkLEDs() {
        // Handle LED blinking phases based on the elapsed time
        double elapsedTime = timer.seconds();

        switch (blinkPhase) {
            case 0:
                leds.frontLED_red_on();
                leds.rearLED_red_on();
                if (elapsedTime >= 2) {
                    // After 2 seconds, switch to the next phase
                    leds.frontLED_red_off();
                    leds.rearLED_red_off();
                    blinkPhase++;
                    timer.reset(); // Reset timer for the next phase
                }
                break;
            case 1:
                leds.frontLED_green_on();
                leds.rearLED_green_on();
                if (elapsedTime >= 2) {
                    // After 2 seconds, switch to the next phase
                    leds.frontLED_green_off();
                    leds.rearLED_green_off();
                    blinkPhase++;
                    timer.reset(); // Reset timer for the next phase
                }
                break;
            case 2:
                leds.frontLED_red_on();
                leds.rearLED_red_on();
                leds.frontLED_green_on();
                leds.rearLED_green_on();
                if (elapsedTime >= 2) {
                    // After 2 seconds, return to the first phase to repeat the cycle
                    leds.frontLED_red_off();
                    leds.rearLED_red_off();
                    leds.frontLED_green_off();
                    leds.rearLED_green_off();
                    blinkPhase = 0; // Reset phase to 0 to repeat the cycle
                    timer.reset(); // Reset timer for the next cycle
                }
                break;
        }
    }
}
