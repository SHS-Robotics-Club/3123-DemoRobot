package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.AutoClearEncoder;
import org.firstinspires.ftc.teamcode.hardware.HardwareMapper;
import org.firstinspires.ftc.teamcode.hardware.HardwareName;
import org.firstinspires.ftc.teamcode.hardware.Reversed;
import org.firstinspires.ftc.teamcode.hardware.ZeroPower;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;


public class Hardware extends HardwareMapper {
    // left = left motor = exp 0 frontLeft
    // right = right motor = ctr 0 frontRight
    // center = ctr 3 intake

    @HardwareName("frontLeft")
    @Reversed
    @ZeroPower(DcMotor.ZeroPowerBehavior.BRAKE)
    public DcMotor frontLeft;

    @HardwareName("frontRight")
    @ZeroPower(DcMotor.ZeroPowerBehavior.BRAKE)
    public DcMotor frontRight;

    @HardwareName("backLeft")
    @ZeroPower(DcMotor.ZeroPowerBehavior.BRAKE)
    @Reversed
    public DcMotor backLeft;

    @HardwareName("backRight")
    @ZeroPower(DcMotor.ZeroPowerBehavior.BRAKE)
    public DcMotor backRight;

    @HardwareName("verticalSlides")
    @ZeroPower(DcMotor.ZeroPowerBehavior.BRAKE)
    public DcMotor verticalSlide;

    @HardwareName("verticalSlides")
    @AutoClearEncoder
    public DcMotor encoderVerticalSlide;
    /*
        @HardwareName("frontLeft")
        @AutoClearEncoder
        public DcMotor encoderLeft;

        @HardwareName("intake")
        @AutoClearEncoder
        public DcMotor encoderCenter;

        @HardwareName("frontRight")
        @AutoClearEncoder
        public DcMotor encoderRight;
    */
    @HardwareName("gyro")
    public NavxMicroNavigationSensor gyro;


    public Hardware(HardwareMap hwMap) {
        super(hwMap);
    }

}