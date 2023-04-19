package ftc.opMode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ftc.drive.MecanumDrive;



@Autonomous(name="MAIN", group="autonomous")
public class AutonOpMain extends LinearOpMode {
    MecanumDrive robot = new MecanumDrive(this);

    public void runOpMode(){

    }
}