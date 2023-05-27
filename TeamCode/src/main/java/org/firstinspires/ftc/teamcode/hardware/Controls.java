package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

/*
*
* This is the TeleOp controller class.
*
* I made a design decision to not use field oriented driving
* because of potential gyro drift and limited time.
*
* Thus I will use the MecamumDrive class instead of combining with another
*
* */

public class Controls extends MechanismInherit {

    public MecanumDrive drive;
    public IntakeLiftFSM arm;

    //1 is current gamepad, 2 is previous iteration
    private Gamepad gamepad1 = new Gamepad();

    private Gamepad gamepad2 = new Gamepad();

    @Override
    public void init(HardwareMap hwMap) {
        drive = new MecanumDrive(hwMap);
    }

    public void run(Gamepad gamepad) {
        gamepad2.copy(gamepad1);
        gamepad1.copy(gamepad);
        move();
        score();
    }

    private void move() {
        drive.setWeightedDrivePower(gamepad1);

    }
    private void score() {
        //rising~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        if (gamepad1.cross && !gamepad2.cross) {
            arm.low();
        }
        if (gamepad1.circle && !gamepad2.circle) {
            arm.mid();
        }
        if (gamepad1.triangle && !gamepad2.triangle) {
            arm.high();
        }
        if (gamepad1.square && !gamepad2.square) {
            arm.score();
        }


        if (gamepad1.left_bumper && !gamepad2.left_bumper) {
            arm.pickUpOpen();
        }
        if (gamepad1.right_bumper && !gamepad2.right_bumper) {
            arm.pickUpClose();
        }


        if (gamepad1.dpad_up && !gamepad2.dpad_up) {

        }
        if (gamepad1.dpad_down && !gamepad2.dpad_down) {

        }
        if (gamepad1.dpad_left && !gamepad2.dpad_left) {

        }
        if (gamepad1.dpad_right && !gamepad2.dpad_right) {

        }

        //falling~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        if (!gamepad1.cross && gamepad2.cross) {

        }
        if (!gamepad1.circle && gamepad2.circle) {

        }
        if (!gamepad1.triangle && gamepad2.triangle) {

        }
        if (!gamepad1.square && gamepad2.square) {

        }


        if (gamepad1.left_bumper && !gamepad2.left_bumper) {

        }
        if (gamepad1.right_bumper && !gamepad2.right_bumper) {

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
