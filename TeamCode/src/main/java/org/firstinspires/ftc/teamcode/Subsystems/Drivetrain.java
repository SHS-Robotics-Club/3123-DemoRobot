package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain {

    // Constants
    public static final double MOTOR_POWER_ZERO = 0.0;  // Power to motors before START
    public static final double ABSOLUTE_MAX_POWER = 1.0;  // Maximum power to motors

    private DcMotor leftFront, rightFront, leftRear, rightRear;
    private double powerFactor;
    // Telemetry object
    private Telemetry telemetry;


    /**
     * The Drivetrain class defines all the drivetrain components for the robot.
     *
     * PUBLIC METHODS:
     *     Drivetrain(hardwareMap, telemetry) - constructor for instantiating a drivetrain
     *     void init() - initializes the components of the drivetrain
     *     void setPowerFactor(factor) - sets power factor applied to motor power
     *     void drive(forward, turn) - applies power to the motors
     *     void getTelemetry() - reports drivetrain telemetry
     *
     * VERSION   DATE     WHO  DETAIL
     * 00.01.00  11Nov24  SEB  Initial release
     *
     */
    public Drivetrain(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        try {
            // Instantiate all four motors as DcMotor class and use the configuration
            // to connect the port to each existing motor
            leftFront = hardwareMap.get(DcMotor.class, "leftFront");
            rightFront = hardwareMap.get(DcMotor.class, "rightFront");
            leftRear = hardwareMap.get(DcMotor.class, "leftRear");
            rightRear = hardwareMap.get(DcMotor.class, "rightRear");

            // Define motor directions - typically left motors are forward but not always!
            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
            leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
            rightRear.setDirection(DcMotorSimple.Direction.FORWARD);

        } catch (Exception e) {
            telemetry.addData("Error", "Drivetrain initialization failed: " + e.getMessage());
            telemetry.update();
        }

        // Set default power factor (default 1.0)
        powerFactor = 0.4;
    }

    /**
     * Initializes all drivetrain subsystems.
     * Motors are set to zero power and power is based directly from setPower (no PID controller).
     */
    public void init() {
        if (leftFront != null && rightFront != null && leftRear != null && rightRear != null) {

            // Initialize motors to brake applies without encoders
            leftFront.setPower(MOTOR_POWER_ZERO);  // SAFETY: Make sure motor is set to zero
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Encoder data collected but no PID

            rightFront.setPower(MOTOR_POWER_ZERO);  // SAFETY: Make sure motor is set to zero
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Encoder data collected but no PID

            leftRear.setPower(MOTOR_POWER_ZERO);  // SAFETY: Make sure motor is set to zero
            leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Encoder data collected but no PID

            rightRear.setPower(MOTOR_POWER_ZERO);  // SAFETY: Make sure motor is set to zero
            rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // Encoder data collected but no PID

        } else {
            telemetry.addData("Error", "Drivetrain motors are not initialized.");
        }
    }

    // Method to set the power factor
    public void setPowerFactor(double factor) {
        powerFactor = factor;
    }

    // Method to control the robot's movement (forward/backward and turning only)
    public void drive(double forward, double turn) {

        double leftFrontPower = forward + turn;
        double rightFrontPower = forward - turn;
        double leftRearPower = forward + turn;
        double rightRearPower = forward - turn;

        // Clamp the power to prevent it from exceeding the max power limit
        leftFrontPower = Math.max(Math.min(leftFrontPower * powerFactor, ABSOLUTE_MAX_POWER), -ABSOLUTE_MAX_POWER);
        rightFrontPower = Math.max(Math.min(rightFrontPower * powerFactor, ABSOLUTE_MAX_POWER), -ABSOLUTE_MAX_POWER);
        leftRearPower = Math.max(Math.min(leftRearPower * powerFactor, ABSOLUTE_MAX_POWER), -ABSOLUTE_MAX_POWER);
        rightRearPower = Math.max(Math.min(rightRearPower * powerFactor, ABSOLUTE_MAX_POWER), -ABSOLUTE_MAX_POWER);

        // Apply the power to the motors
        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftRear.setPower(leftRearPower);
        rightRear.setPower(rightRearPower);
    }

    /**
     * Reports the current encoder position and power level for each motor.
     */
    public void getTelemetry() {

        // Send motor data as telemetry data
        telemetry.addData("-----  DRIVETRAIN", "  -----");
        telemetry.addData("Power Factor", powerFactor);
        telemetry.addData("Motor Power", "LF: %.2f, RF: %.2f, LR: %.2f, RR: %.2f",
                leftFront.getPower(), rightFront.getPower(), leftRear.getPower(), rightRear.getPower());
    }

}
