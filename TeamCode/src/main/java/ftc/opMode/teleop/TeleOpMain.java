package ftc.opMode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ftc.drive.MecanumDrive;

@TeleOp(name="MAIN", group = "TeleOpMode")
public class TeleOpMain extends LinearOpMode{
    MecanumDrive robot = new MecanumDrive(this);

    public void runOpMode(){

    }
}