package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.Accesories.WobbleGoal;
import org.firstinspires.ftc.teamcode.Components.BasicChassis;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name= "CorgiFinalAuto", preselectTeleOp = "TwoGPTeleop")

public class CorgiFinalAutoPower extends LinearOpMode {
    @Override
    public void runOpMode(){
        Robot robot = new Robot(this, BasicChassis.ChassisType.ODOMETRY, true, false);
        robot.setPosition(0,0,0);
        int rings = robot.getRingsAndWaitForStart();
        robot.stopRingDetection();
        if(rings!=1&&rings!=4) {
            robot.goToPosition(-56,-15,0,1.0);
//            robot.moveWobbleGoalToPosition(WobbleGoal.Position.DROP);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            sleep(420);
            robot.openWobbleGoalClaw();
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RAISE);
            sleep(150);

//            robot.openWobbleGoalClaw();
//            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            robot.setVelocity(1385,3000);
            robot.goToPosition(-54,22,0,1);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.GRAB);
            robot.shootThreePowerShot();
            robot.turnInPlace(180,0.7);
            robot.goToPosition(-23,9,180,0.95);
            robot.closeWobbleGoalClaw();
            sleep(250);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            robot.goToPosition(-77,-3,90,1);
            sleep(350);
            robot.openWobbleGoalClaw();
            sleep(200);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.REST);
            robot.goToPositionWithoutStop((float)-118.5,-3,90,1);
            robot.startIntake();
            robot.startTransfer();
            robot.goToPositionWithoutStop((float)-117.5,46,90,(float)0.5);
            robot.reverseTransfer();
            sleep(100);
            robot.startTransfer();
            robot.setVelocity(1650,1000);
            robot.goToPosition(-56,4,-2,1);
            robot.reverseTransfer();
            sleep(100);
            robot.startTransfer();
            robot.stopIntake();
            robot.stopTransfer();
            robot.shootHighGoal(3);
            robot.goToPosition(-70,6,0,0.8);
            stop();
        }
        else if(rings==1) {
            robot.goToPositionWithoutStop(-55,-16,0,1);
            robot.goToPosition(-77.5,8.0,0,1);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            sleep(380);
            robot.openWobbleGoalClaw();
            robot.setVelocity(1650,1000);
            robot.goToPosition(-57,5,1.5,1);
            robot.shootHighGoal(1);
            robot.startIntake();
            robot.startTransfer();
            robot.goToPosition(-51,4,0,1);
            robot.setVelocity(1350,2000);
            robot.goToPosition(-54,22,0,1);
            robot.stopIntake();
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.GRAB);
            robot.shootThreePowerShot();
            robot.stopTransfer();
            robot.turnInPlace(180,1.0);
            robot.goToPosition(-23,9,180,0.95);
            robot.closeWobbleGoalClaw();
            sleep(250);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            //robot.goToPosition(-105, 20,90, 1);
            robot.goToPosition(-105, 22,90, 1);
            robot.openWobbleGoalClaw();
            sleep(200);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.REST);
            robot.goToPositionWithoutStop((float)(-118.5),14,90,1);
            robot.startIntake();
            robot.startTransfer();
            robot.goToPositionWithoutStop((float)-117,48,90,(float)0.7);
            robot.reverseTransfer();
            robot.setVelocity(1640,3000);
            robot.startTransfer();
            //robot.stopIntake();
            //            robot.setVelocity(1600,1000);
//            robot.goToPosition(-60,18,9,1);
//            robot.stopTransfer();
//            robot.shootHighGoalTest(1600,1000,2);
            robot.goToPositionWithoutStop(-75,20,0,1);
            robot.goToPosition(-57,4,0,1);
            robot.reverseTransfer();
            sleep(100);
            robot.startTransfer();
            robot.shootHighGoalTest(1640,2000,3);
            robot.stopIntake();
            robot.stopTransfer();
            robot.goToPosition(-70,4,0,0.1);
            stop();

        }
        else if(rings==4) {
            /*robot.goToPositionWithoutStop(-55,-16,0,(float)0.95);
            robot.goToPosition(-104,-16,0,0.9);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.GRAB);
            sleep(250);
            robot.openWobbleGoalClaw();
//            sleep(30);
            robot.setVelocity(1650,1000);
            robot.goToPosition(-54,4,2.5,1);
            robot.shootHighGoal(3);
            sleep(25);
            robot.startIntake();
            robot.startTransfer();
            robot.goToPosition(-52.5,4,2,0.95);
            sleep(50);
            robot.reverseTransfer();
            sleep(50);
            robot.startTransfer();
//            sleep(50);
            robot.shootHighGoalTest(1635,1000,1);
            sleep(50);
            robot.goToPosition(-29,4,0,0.7);
            robot.stopIntake();
            sleep(50);
            robot.reverseTransfer();
            sleep(50);
            robot.startTransfer();
            robot.setVelocity(1385,4000);
            robot.stopTransfer();
            robot.goToPosition(-54,21,0,1);
            robot.startIntake();
            robot.reverseTransfer();
            sleep(25);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.GRAB);
            robot.startTransfer();
            robot.shootThreePowerShotTransfer();
            robot.turnInPlace(180,0.75);
            robot.goToPosition(-30,9,180,1);
            robot.turnInPlace(180,1);
            robot.goToPosition(-22.5,8.5,178,0.92);
            sleep(10);
            robot.closeWobbleGoalClaw();
            sleep(175);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RAISE);
            sleep(10);
            robot.turnInPlace(0,0.8);
            robot.goToPosition(-103, -8.5,0,1);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            sleep(75);
            robot.openWobbleGoalClaw();
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.REST);
//            sleep(25);
//            robot.goToPosition(-100, -8.5,0, 0.9);
            robot.moveRightStick(0);
            robot.goToPosition(-78, -8.5,-1, 1);

//            robot.moveWobbleGoalToPosition(WobbleGoal.Position.DROP);
//            robot.moveWobbleGoalToPosition(WobbleGoal.Position.REST);
//            robot.goToPosition(70,-10,0,1);
//            robot.moveRightStick(0);
//            sleep(100);
//            robot.moveRightStick(1);
//            robot.goToPosition(-70,8,0,1);
//            robot.turnInPlace(-45,0.8);
            stop();

            robot.goToPosition(-50,-16,0,0.9);
            robot.goToPosition(-55,4,0,0.6);
            robot.shootHighGoal(3);
            sleep(500);
            robot.startIntake();
            robot.startTransfer();
            robot.goToPosition(-45,5,0.5,0.6);
            sleep(250);
            robot.shootHighGoal(1);
            sleep(250);
            robot.stopIntake();
            robot.stopTransfer();
            sleep(250);

            robot.goToPosition(-23,-10,-13.5,0.9);
//            robot.shootHighGoalTest(1670, 100,3);
            robot.goToPosition(-115, -7, 0,1);
//            robot.openWobbleGoalClaw();
//            robot.goToPosition(-60,-2,1,0.6);
//            robot.startIntake();
//            robot.startTransfer();
            robot.goToPosition(-45,-1,0.5,0.5);
            robot.goToPosition(-43,0,-2,0.4);
            sleep(500);
//            robot.shootHighGoal(1);
//            robot.startIntake();
//            robot.startTransfer();
            sleep(250);
            robot.goToPosition(-33,0,0,1);
            robot.goToPosition(-33,0,-2,1);
//            robot.stopIntake();
//            robot.stopTransfer();
            sleep(250);
            robot.goToPosition(-54.5,31,-2.5,0.7);//yPosition - 23
//            robot.shootThreePowerShot();
            robot.goToPosition(-18, -9,-180,1);
            robot.goToPosition(-9,-10, -180,1);
//            robot.closeWobbleGoalClaw();
//            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            robot.goToPosition(100,-2,0,1);
//            robot.openWobbleGoalClaw();
            sleep(250);
            robot.goToPosition(-72,23,0,1);
//            robot.moveWobbleGoalToPosition(WobbleGoal.Position.REST);
            sleep(200);
            robot.turnInPlace(180,0.7);*/
            robot.goToPositionWithoutStop(-55,-16,0,1);
            robot.goToPosition(-106,-16,0,1);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            sleep(400);
            robot.openWobbleGoalClaw();
            sleep(50);
            robot.setVelocity(1650,1000);
            robot.goToPosition(-54.5,3,2.5,1.0);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.GRAB);
            robot.shootHighGoal(3);
            sleep(100);
            robot.startIntake();
            robot.startTransfer();
            robot.goToPosition(-49,3,0.5,0.8);
            sleep(250);
            robot.goToPosition(-55.5, 3, 2.5, 0.8);
            robot.reverseTransfer();
            sleep(60);
            robot.startTransfer();
            robot.shootHighGoal(1);
            sleep(100);
            robot.goToPosition(-30,4,0,0.4);//40
            sleep(250);
            robot.reverseTransfer();
            sleep(60);
            robot.startTransfer();
            robot.goToPosition(-56.5,2.5,2.5,0.8);
            robot.reverseTransfer();
            sleep(60);
            robot.startTransfer();
            robot.shootHighGoal(3);
            robot.setVelocity(0,1000);
            robot.stopIntake();
            robot.stopTransfer();
            robot.turnInPlace(180,0.7);
            robot.goToPosition(-30,9,180,1);
            robot.goToPosition(-20.55,8.5,172.5,0.9);
            robot.closeWobbleGoalClaw();
            sleep(250);
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.RUN);
            robot.turnInPlace(0,0.7);
            robot.goToPosition(-104, -12,0,1);
            robot.openWobbleGoalClaw();
            robot.moveWobbleGoalToPosition(WobbleGoal.Position.REST);
            sleep(50);
            robot.goToPosition(-74, -8.5,0,1);
        }
        stop();
    }
}
