/*
Copyright 2023 FIRST Tech Challenge Team 9505

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp

public class    BasicDrive extends OpMode {
    /* Declare OpMode members. */

    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;


    int level;
    float[] speed;


    // Controller 1 Variables:
    boolean rightBumperLastTime;
    boolean leftBumperLastTime;
    boolean aLastTime;
    boolean yLastTime;
    boolean xLastTime;
    boolean bLastTime;
    boolean dpadUpLastTime;
    boolean dpadDownLastTime;
    boolean dpadLeftLastTime;
    boolean dpadRightLastTime;
    boolean rightStickButtonLastTime;
    boolean leftStickButtonLastTime;

    // Controller 2 Variables:
    boolean rightBumperLastTime2;
    boolean leftBumperLastTime2;
    boolean aLastTime2;
    boolean yLastTime2;
    boolean xLastTime2;
    boolean bLastTime2;
    boolean dpadUpLastTime2;
    boolean dpadDownLastTime2;
    boolean dpadLeftLastTime2;
    boolean dpadRightLastTime2;
    boolean rightStickButtonLastTime2;
    boolean leftStickButtonLastTime2;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize hardware variable values:
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");                  // Control Hub motor port 0
        backRight = hardwareMap.get(DcMotor.class, "backRight");                // Control Hub motor port 1
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");                // Control Hub motor port 2
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");              // Control Hub motor port 3

        level = 1;
        speed = new float[] {.25f, .5f, .75f, 1.0f};

        // Sets these motors to brake upon zero power:
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Sets these motors to run the direction WE want:
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
// Bleh
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */

    @Override

    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {
        // Displays info on Driver Hub:
        telemetry.addData("Speed", speed[level]*100 + "%"); // Tells us the wheel motors' current speed

        // Changes speed if driver1 presses left/right bumper:
        if (gamepad1.right_bumper && !rightBumperLastTime) {
            if (level != 3) {
                level++;
            }
        }
        if (gamepad1.left_bumper && !leftBumperLastTime) {
            if (level != 0) {
                level--;
            }
        }

        // Robot strafes if driver1 holds left/right trigger. Drives normally with joysticks if triggers are not pressed
        if ((gamepad1.left_trigger == 0) && (gamepad1.right_trigger == 0)) {
            // Multiplying by speeds[level] will make the wheels' speed whatever the current element speeds[level] is.
            // For our robot, we have 25%, 50%, 75%, and 100% speed.
                frontRight.setPower((gamepad1.right_stick_y) * speed[level]);
                backRight.setPower((gamepad1.right_stick_y) * speed[level]);
                frontLeft.setPower((gamepad1.left_stick_y) * speed[level]);
                backLeft.setPower((gamepad1.left_stick_y) * speed[level]);
            } else {
            // This is what allows the robot to strafe.
            // If the left trigger is pressed, the robot will go left.
            // If the right trigger is pressed, the robot will go right.
            frontRight.setPower((gamepad1.right_trigger - gamepad1.left_trigger) * speed[level]);
            backLeft.setPower((gamepad1.right_trigger - gamepad1.left_trigger) * speed[level]);
            frontLeft.setPower((gamepad1.left_trigger - gamepad1.right_trigger) * speed[level]);
            backRight.setPower((gamepad1.left_trigger - gamepad1.right_trigger) * speed[level]);
        }

        // The LastTime variables check if a particular button was pressed in the previous loop.
        // Due to our implementation of these, if a button was pressed in a previous loop,
        // then continuing to hold the button for a bit will not spam the function.

        aLastTime = gamepad1.a;
        bLastTime = gamepad1.b;
        xLastTime = gamepad1.x;
        yLastTime = gamepad1.y;
        dpadUpLastTime = gamepad1.dpad_up;
        dpadDownLastTime = gamepad1.dpad_down;
        dpadLeftLastTime = gamepad1.dpad_left;
        dpadRightLastTime = gamepad1.dpad_right;
        leftBumperLastTime = gamepad1.left_bumper;
        rightBumperLastTime = gamepad1.right_bumper;
        leftStickButtonLastTime = gamepad1.left_stick_button;
        rightStickButtonLastTime = gamepad1.right_stick_button;

        aLastTime2 = gamepad2.a;
        bLastTime2 = gamepad2.b;
        xLastTime2 = gamepad2.x;
        yLastTime2 = gamepad2.y;
        dpadUpLastTime2 = gamepad2.dpad_up;
        dpadDownLastTime2 = gamepad2.dpad_down;
        dpadLeftLastTime2 = gamepad2.dpad_left;
        dpadRightLastTime2 = gamepad2.dpad_right;
        leftBumperLastTime2 = gamepad2.left_bumper;
        rightBumperLastTime2 = gamepad2.right_bumper;
        leftStickButtonLastTime2 = gamepad2.left_stick_button;
        rightStickButtonLastTime2 = gamepad2.right_stick_button;
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}
