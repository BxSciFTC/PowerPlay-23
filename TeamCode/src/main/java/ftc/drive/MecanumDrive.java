package ftc.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class MecanumDrive {

    /*
    *
    * Can edit or delete anything, this is just a template
    *
    * */

    private OpMode opMode = null;

    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor armMotor = null;

    private double leftFrontPower;
    private double leftBackPower;
    private double rightFrontPower;
    private double rightBackPower;

    private Servo axis1 = null;
    private Servo axis2 = null;
    private Servo axis3 = null;

    //constants
    public static final double MID_SERVO       =  0.5 ;
    public static final double HAND_SPEED      =  0.02 ;  // sets rate to move servo
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    public MecanumDrive(OpMode opmode) {
        this.opMode = opmode;
    }

    public void init() {
        leftFrontDrive  = opMode.hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = opMode.hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = opMode.hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = opMode.hardwareMap.get(DcMotor.class, "right_back_drive");
        armMotor   = opMode.hardwareMap.get(DcMotor.class, "arm");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        axis1 = opMode.hardwareMap.get(Servo.class, "axis1");
        axis2 = opMode.hardwareMap.get(Servo.class, "axis2");
        axis3 = opMode.hardwareMap.get(Servo.class, "axis3");

        axis1.setPosition(MID_SERVO);
        axis2.setPosition(MID_SERVO);
        axis3.setPosition(MID_SERVO);

        opMode.telemetry.addData("Status", "Hardware initialized");
        opMode.telemetry.update();
    }


}
