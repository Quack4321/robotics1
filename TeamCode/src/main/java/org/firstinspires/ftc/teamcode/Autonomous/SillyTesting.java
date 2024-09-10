package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.util.Vector;

@Config
@Autonomous(name = "SillyTesting", group = "Autonomous")
public class SillyTesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(11.8, 61.7, Math.toRadians(90)));

        Action trajectoryAction1;
        Action trajectoryAction2;
        Action trajectoryAction3;

        // actionBuilder builds from the drive steps passed to it,
        // and .build(); is needed to build the trajectory
        trajectoryAction1 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d (60, 5))
                .build();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1
                )
        );
    }
}
