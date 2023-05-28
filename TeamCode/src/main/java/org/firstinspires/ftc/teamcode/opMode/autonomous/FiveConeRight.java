package org.firstinspires.ftc.teamcode.opMode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.IntakeLiftFSM;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


/*
* The big idea here is to emulate a repetition of tasks for maximum points
* There is an easy path, which is (from a right side POV)
* go foward
* go left
* Score your cone on highest pole
* go right
* pick up another cone
* go left
* score again
* Go to Parking Station around 6 secs before time is up
* */

@Autonomous(name="Left", group = "_1")
public class FiveConeRight extends LinearOpMode {

    public SampleMecanumDrive drive;
    public IntakeLiftFSM arm = new IntakeLiftFSM();
    ElapsedTime timer = new ElapsedTime();

    public static double servoTime = 0.625;
    public static double liftTime = 0.625;

    Pose2d endPose;

    @Override
    public void runOpMode() {
        //init
        drive = new SampleMecanumDrive(hardwareMap);
        arm.init(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        drive.setPoseEstimate(AutoConstants.R_START);
        TrajectorySequence initial = drive.trajectorySequenceBuilder(AutoConstants.R_START)
                .lineToLinearHeading(AutoConstants.R_PICKUP_PRE)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.pickUpOpen();
                })
                .lineToLinearHeading(AutoConstants.R_PICKUP_POST)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.pickUpClose();
                })
                .waitSeconds(servoTime)
                .lineToLinearHeading(AutoConstants.R_SCORE_PRE)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.high();
                })
                .waitSeconds(liftTime)
                .lineToLinearHeading(AutoConstants.R_SCORE_POST)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.score();
                })
                .waitSeconds(servoTime)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.pickUpOpen();
                })
                .waitSeconds(liftTime)
                .build();

        TrajectorySequence mainPath = drive.trajectorySequenceBuilder(endPose)
                .lineToLinearHeading(AutoConstants.R_PICKUP_PRE)
                .lineToLinearHeading(AutoConstants.R_PICKUP_POST)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.pickUpClose();
                })
                .waitSeconds(servoTime)
                .lineToLinearHeading(AutoConstants.R_SCORE_PRE)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.high();
                })
                .waitSeconds(liftTime)
                .lineToLinearHeading(AutoConstants.R_SCORE_POST)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.score();
                })
                .waitSeconds(servoTime)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    arm.pickUpOpen();
                })
                .waitSeconds(liftTime)
                .build();

        //PATHS FOR LMR parking
        TrajectorySequence leftPark = drive.trajectorySequenceBuilder(endPose)
                .addTemporalMarker(0, () -> {
                    arm.pickUpOpen();
                })
                .lineToLinearHeading(AutoConstants.R_PARK_LEFT)
                .build();
        TrajectorySequence midPark = drive.trajectorySequenceBuilder(endPose)
                .addTemporalMarker(0, () -> {
                    arm.pickUpOpen();
                })
                .lineToLinearHeading(AutoConstants.R_PARK_MIDDLE)
                .build();
        TrajectorySequence rightPark = drive.trajectorySequenceBuilder(endPose)
                .addTemporalMarker(0, () -> {
                    arm.pickUpOpen();
                })
                .lineToLinearHeading(AutoConstants.R_PARK_RIGHT)
                .build();

        //OPENCV


        drive.followTrajectorySequenceAsync(initial);
        endPose = initial.end();
        for (int i = 0; i < 5; i++) {
            drive.followTrajectorySequenceAsync(mainPath);
            endPose = mainPath.end();
        }

        //follow to parking
    }

}
