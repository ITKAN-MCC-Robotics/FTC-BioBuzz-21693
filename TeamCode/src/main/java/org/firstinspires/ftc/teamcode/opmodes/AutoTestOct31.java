package org.firstinspires.ftc.teamcode.opmodes;

import com.pedropathing.api.Paths;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
@Autonomous
public class AutoTestOct31 extends OpMode {
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(52, 7, 90);
    private final Pose ShooterPos = p.of(58, 117, 270);
    private final Pose controlPose = p.of(30.92, 68.55, 180);
    private Command autoRoutine() {
        return sequential(
                follow(follower, GoShoot())
                // Add mechanism commands here
        );
    }
    private Path GoShoot() {
        return curve(startPose, controlPose, ShooterPos).linear(startPose, ShooterPos);
    }
    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }
    @Override
    public void start() {
        schedule(follow(follower, GoShoot()));



    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
    }
}