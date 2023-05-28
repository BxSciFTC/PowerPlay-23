package org.firstinspires.ftc.teamcode.opMode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.IntakeLiftFSM;
import org.firstinspires.ftc.teamcode.hardware.MechanismInherit;


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

@Autonomous(name="AutonOpMain", group = "_1")
public class FiveConeRIght extends MechanismInherit {
    HardwareMap hwMap;
    public SampleMecanumDrive drive;
    public IntakeLiftFSM arm = new IntakeLiftFSM();
    ElapsedTime timer = new ElapsedTime();


    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        drive = new SampleMecanumDrive(hwMap);
        arm.init(hwMap);
    }


}
