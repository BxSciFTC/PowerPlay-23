package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

public class Bot extends MechanismInherit {

    public MecanumDrive drive;

    @Override
    public void init(HardwareMap hwMap) {
        drive = new MecanumDrive(hwMap);
    }

    //PS4 Controller

}
