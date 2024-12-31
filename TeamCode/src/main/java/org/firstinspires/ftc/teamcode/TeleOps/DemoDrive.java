package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

//@Disabled
// Possible Groups: Competition, Development, Test, Training
@TeleOp(name = "DemoDrive", group = "Development")

public class DemoDrive extends OpMode {

    // Drivetrain constants
    public static final double DRIVETRAIN_LOW_POWER_FACTOR = 0.4;
    public static final double DRIVETRAIN_HIGH_POWER_FACTOR = 0.8;
    private static final int TELEMETRY_UPDATE_FREQUENCY = 20; // Update every 20 iterations

    private org.firstinspires.ftc.teamcode.Subsystems.Drivetrain drivetrain;
    private boolean lowPowerMode = true;
    private int telemetryUpdateCounter = 0;


    @Override
    public void init() {

        // Build robot
        drivetrain = new org.firstinspires.ftc.teamcode.Subsystems.Drivetrain(hardwareMap, telemetry);
        // Initialize subsystems
        drivetrain.init();
        // Report status
        telemetry.addData("Status", "Initialized");
        drivetrain.getTelemetry();
        telemetry.update();

    }

    @Override
    public void init_loop() {

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

        // Update telemetry every TELEMETRY_UPDATE_FREQUENCY iterations
        if (telemetryUpdateCounter >= TELEMETRY_UPDATE_FREQUENCY) {
            telemetryUpdateCounter = 0;
            drivetrain.getTelemetry();
            telemetry.update();
        } else {
            telemetryUpdateCounter++;
        }

    }
}
