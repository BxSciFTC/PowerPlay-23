package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

/*
*
* This is the controller class.
*
* */

public class Controls extends MechanismInherit {

    public MecanumDrive drive;

    //1 is current gamepad, 2 is previous iteration
    Gamepad gamepad1 = new Gamepad();

    Gamepad gamepad2 = new Gamepad();

    @Override
    public void init(HardwareMap hwMap) {
        drive = new MecanumDrive(hwMap);
    }

    public void run(Gamepad gamepad) {
        gamepad2.copy(gamepad1);
        gamepad1.copy(gamepad);
        move();
    }

    private void move() {
        //rising
        if (gamepad1.cross && !gamepad2.cross) {
            drive.forwardTest(1);
        }
        if (gamepad1.circle && !gamepad2.circle) {

        }
        if (gamepad1.triangle && !gamepad2.triangle) {

        }
        if (gamepad1.square && !gamepad2.square) {

        }
        if (gamepad1.dpad_up && !gamepad2.dpad_up) {

        }
        if (gamepad1.dpad_down && !gamepad2.dpad_down) {

        }
        if (gamepad1.dpad_left && !gamepad2.dpad_left) {

        }
        if (gamepad1.dpad_right && !gamepad2.dpad_right) {

        }


        //falling
        if (!gamepad1.cross && gamepad2.cross) {
            drive.forwardTest(0);
        }
        if (!gamepad1.circle && gamepad2.circle) {

        }
        if (!gamepad1.triangle && gamepad2.triangle) {

        }
        if (!gamepad1.square && gamepad2.square) {

        }
        if (!gamepad1.dpad_up && gamepad2.dpad_up) {

        }
        if (!gamepad1.dpad_down && gamepad2.dpad_down) {

        }
        if (!gamepad1.dpad_left && gamepad2.dpad_left) {

        }
        if (!gamepad1.dpad_right && gamepad2.dpad_right) {

        }
    }


}
