package org.firstinspires.ftc.teamcode.opMode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Controls;

@TeleOp (name="TeleOpMain", group = "_1")
public class TeleOpMain extends LinearOpMode{
    Controls bot = new Controls();

    public void runOpMode(){
        bot.init(hardwareMap);
        telemetry.addData("TeleOpMain", "boop");
        telemetry.update();
        while(opModeIsActive()) {
            bot.run(gamepad1);
        }
        telemetry.addData("TeleOpMain", "end");
        telemetry.update();
    }
}