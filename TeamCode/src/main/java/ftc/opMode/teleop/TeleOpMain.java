package ftc.opMode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import ftc.drive.MecanumDrive;
import ftc.hardware.Bot;

@TeleOp(name="MAIN", group = "TeleOpMode")
public class TeleOpMain extends LinearOpMode{
    Bot bot = new Bot();

    public void runOpMode(){
        bot.init(hardwareMap);
    }
}