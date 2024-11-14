// Program created by: Danny and William
// Purpose: FTC Robot Software

// The file path of the class
package org.firstinspires.ftc.teamcode.Robotics_10650_2024_2025_Code;

// Import all of the necessary FTC libraries and code
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Create an Autonomous program (Auto) that preselects a TeleOp (controller operated)
@Autonomous(name = "AutonomousCodeHighBasket", preselectTeleOp = "TeleOpCode_RobotCentric")
public class AutonomousCodeHighBasket extends LinearOpMode {

    // Execute the function from the RobotInitialize class
    RobotInitialize robot;

    // The code that runs in Auto
    @Override
    public void runOpMode() throws InterruptedException {
        // Way for the RobotInitialize class to be used inside of this class
        robot = new RobotInitialize(this);
        // Waits for a person to press start on the control hub
        // then it runs the rest of the program
        waitForStart();
//        robot.newTurnFunction(90);
//        robot.newTurnFunction(-90);
//        sleep(10000);
        robot.strafeR(104, 500);
        robot.liftPitch(100, 400);
        sleep(10000);




        // Phase 1 auto program (go forward, raise arm device, place pre-loaded sample, then strafe
        // into the ascent zone and use the arm device to touch the first bar
        // Fairly accurate but might need improvement

        //robot.claw.setPower(.5);
        //robot.liftExtender.setTargetPosition(0);

        //Positive values make the lift go backwards
        //Negative values make the lift go forwards
//        robot.liftPitch(200, 0.05);
//        robot.intakeToggle(1);

        // Main movement of drivetrain during Auto
        //robot.goStraight(1100, 500); // 500 is good velocity for now
//        robot.goStraight(200, 500); // 500 is good velocity for now
//        robot.newTurnFunction(90); // 500 is good velocity for now
        //telemetry.addData("angle", getAngle() );
        robot.strafeR(120, 500);
        robot.goStraight(470, 500); // 500 is good velocity for now
        robot.liftExtender(2702, 800);
        robot.liftPitch(172, 300);
        robot.extake(1000);
        robot.clawRoll.setPosition(0.3372);
        robot.liftPitch(0, 600);
        //sleep(100);
        //robot.goStraight(-70, 300);
        //sleep(100);
// 500 is good velocity for now
        robot.liftExtender(0, 800);



        //to score another sample
        robot.newTurnFunction(-90);
        robot.clawRoll.setPosition(0);
        robot.goStraight(187, 500);
        //prev 164
        robot.strafeR(64, 500);
        sleep(1000);
        robot.liftPitch(400, 400);
        robot.intake(4000);




        //to park
//         robot.newTurnFunction(-180);
//        robot.goStraight(3000, 500);






        //robot.liftPitch(1042, 500);




        //robot.strafeR(200, 500);









        //robot.newTurnFunction()
        //robot.strafeL(11, 500);




        //Log.d("Testing Value ", String.valueOf((robot.inchesToEncoderTicks)));


        //Old auto code (keep for now)
        {
//            robot.strafeR(1100, 500);
//            robot.goStraight(500, 500);
//            robot.strafeL(50, 100);
//            robot.newTurnFunction(90);
//            robot.goStraight(200, 100);
            //}

            // Shutdown all mechanisms when the code ends
            robot.stopMechanisms();
        }
    }
}