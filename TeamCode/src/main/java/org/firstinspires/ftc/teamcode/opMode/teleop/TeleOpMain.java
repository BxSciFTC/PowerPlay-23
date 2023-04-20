package org.firstinspires.ftc.teamcode.opMode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Bot;

@TeleOp(name="MAIN", group = "TeleOpMode")
public class TeleOpMain extends LinearOpMode{
    Bot bot = new Bot();

    public void runOpMode(){
        bot.init(hardwareMap);
    }
}