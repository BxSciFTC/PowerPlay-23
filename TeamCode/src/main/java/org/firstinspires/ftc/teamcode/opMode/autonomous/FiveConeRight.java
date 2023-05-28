package org.firstinspires.ftc.teamcode.opMode.autonomous;

import android.annotation.SuppressLint;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.IntakeLiftFSM;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;


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
    OpenCvWebcam cam;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    AprilTagDetection tagOfInterest = null;

    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    //Tag IDs (Sleeve)
    final int left = 17;
    final int middle = 18;
    final int right = 19;

    public static double servoTime = 0.625;
    public static double liftTime = 0.625;

    Pose2d endPose;
    public static int coneCount = 0;

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
                    coneCount++;
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
                    coneCount++;
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
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        cam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(
                WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        cam.setPipeline(aprilTagDetectionPipeline);

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                cam.startStreaming(800, 600, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Camera failed :((", "D:");
                telemetry.update();
            }
        });

        telemetry.setMsTransmissionInterval(50);
        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == left || tag.id == middle || tag.id == right)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }
        cam.stopStreaming();

        drive.followTrajectorySequenceAsync(initial);
        endPose = initial.end();
        for (int i = 0; i < 5; i++) {
            drive.followTrajectorySequenceAsync(mainPath);
            endPose = mainPath.end();
        }

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        timer.reset();
        while (!isStarted() && !isStopRequested()) {
            drive.update();
            arm.loop();
            if (coneCount == 5) {
                if (tagOfInterest.id == left) {
                    drive.followTrajectorySequenceAsync(leftPark);
                }else if (tagOfInterest.id == middle) {
                    drive.followTrajectorySequenceAsync(midPark);
                }else if (tagOfInterest.id == right) {
                    drive.followTrajectorySequenceAsync(rightPark);
                }
            }
            telemetry.update();
        }
        //follow to parking
    }

    @SuppressLint("DefaultLocale")
    void tagToTelemetry(AprilTagDetection detection) {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
    }
}
