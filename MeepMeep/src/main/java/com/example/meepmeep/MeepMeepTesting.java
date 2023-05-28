package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static double servoTime = 0.625;
    public static double liftTime = 0.625;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(38,-52))
                                .lineToLinearHeading(AutoConstants.R_PICKUP_PRE)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.pickUpOpen();
                                })
                                .lineToLinearHeading(AutoConstants.R_PICKUP_POST)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.pickUpClose();
                                })
                                .waitSeconds(servoTime)
                                .lineToLinearHeading(AutoConstants.R_SCORE_PRE)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.high();
                                })
                                .waitSeconds(liftTime)
                                .lineToLinearHeading(AutoConstants.R_SCORE_POST)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.score();
                                    //coneCount++;
                                })
                                .waitSeconds(servoTime)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.pickUpOpen();
                                })
                                .waitSeconds(liftTime)


                                .lineToLinearHeading(AutoConstants.R_PICKUP_PRE)
                                .lineToLinearHeading(AutoConstants.R_PICKUP_POST)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.pickUpClose();
                                })
                                .waitSeconds(servoTime)
                                .lineToLinearHeading(AutoConstants.R_SCORE_PRE)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.high();
                                })
                                .waitSeconds(liftTime)
                                .lineToLinearHeading(AutoConstants.R_SCORE_POST)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.score();
                                    //coneCount++;
                                })
                                .waitSeconds(servoTime)
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //arm.pickUpOpen();
                                })
                                .waitSeconds(liftTime)

                                .addTemporalMarker(0, () -> {
                                    //arm.pickUpOpen();
                                })
                                .lineToLinearHeading(AutoConstants.R_PARK_MIDDLE)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.setBackground(MeepMeep.Background.FIELD_ULTIMATEGOAL_INNOV8RZ_DARK)
                .setTheme(new ColorSchemeRedDark())
                .addEntity(myBot)
                .start();
    }
}