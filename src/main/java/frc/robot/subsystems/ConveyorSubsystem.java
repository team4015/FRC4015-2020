/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.NumberConstants;
import frc.robot.RobotMap;

public class ConveyorSubsystem extends Subsystem {
    private PWMSparkMax conveySpark;
    public DigitalInput limitSwitch1;
    public DigitalInput limitSwitch2;
    public DigitalInput limitSwitchTop;
    private boolean prevState1;
    private boolean prevState2;
    private boolean prevStateTop;
    private boolean run;
    private boolean runShooter;
    private Timer timer;
    private Timer shootTimer;
    public int balls;
    public int topBalls;
    private double timeLimit;
    private double shootDelay;
    public ConveyorSubsystem() {
      conveySpark = new PWMSparkMax(RobotMap.CONVEYOR_SPARK);
      limitSwitch1 = new DigitalInput(0);
      limitSwitch2 = new DigitalInput(1);
      limitSwitchTop = new DigitalInput(2);
      prevState1 = false;
      prevState2 = false;
      prevStateTop = false;
      run = false;
      runShooter = true;
      timer = new Timer();
      shootTimer = new Timer();
      balls = 0;
      topBalls = 0;
      timeLimit = NumberConstants.AUTOCONVEY_TIME;
      shootDelay = NumberConstants.SHOOTER_DELAY_BETWEEN_BALLS;
    }
    public void autoConvey() {
      if (pressed1()&&(balls==0||timer.get()<0.2)) {
        balls++;
        System.out.println("BALLS "+balls);
        if (balls<5) {
          timer.start();
          run = true;
        }
      }
      if (pressed2()||timer.get()>timeLimit) {
        if (timer.get()<timeLimit) {
          System.out.println("_____LIMIT SWITCH: "+timer.get());
        } else {
          System.out.println("TIMER ACTIVATED");
        }
        timer.stop();
        timer.reset();
        run = false;
        // if (timeLimit<0.3) {
        //   timeLimit+=0.1;
        // }
      }
      if (pressedTop()&&balls>3) {
        topBalls++;
        System.out.println("****TOP STOPPED****");
        balls = 4;
        run = false;
      }
      if (run) {
        forward();
      } else {
        idle();
      }
      
    }
    public void forward(){
      conveySpark.set(NumberConstants.CONVEYOR_SPEED);
    }
    public void shootConveyor() {
      if (pressedTop()) {
        runShooter = false;
        shootTimer.start();
      }
      if (shootTimer.get()>shootDelay) {
        runShooter = true;
        shootTimer.stop();
        shootTimer.reset();
      }
      if (runShooter) {
        conveySpark.set(NumberConstants.CONVEYOR_SHOOT_SPEED);
      } else {
        idle();
      }
    }

    public void reverse(){
        conveySpark.setSpeed(-NumberConstants.CONVEYOR_REVERSE_SPEED);
        balls = 0;//im not going to bother counting the balls as they roll out
    }

    public void idle(){
      conveySpark.stopMotor();
    }

    public void resetSwitches() {
      prevState1 = false;
      prevState2 = false;
      prevStateTop = false;
    }
    public boolean pressed1() {
      if (prevState1==false&&limitSwitch1.get()==true) {
        // System.out.println("1111111111111111111111");
        prevState1 = true;
        return true;
      } else if (prevState1==true&&limitSwitch1.get()==false) {
        prevState1 = false;
        return false;
      } else {
        return false;
      }
    }

    public boolean pressed2() {
      if (prevState2==false&&limitSwitch2.get()==true) {
        // System.out.println("2222222222222222222222");
        prevState2 = true;
        return true;
      } else if (prevState2==true&&limitSwitch2.get()==false) {
        prevState2 = false;
        return false;
      } else {
        return false;
      }
    }
    public boolean pressedTop() {
      if (prevStateTop==false&&limitSwitchTop.get()==true) {
        // System.out.println("TOP");
        prevStateTop = true;
        return true;
      } else if (prevStateTop==true&&limitSwitchTop.get()==false) {
        prevStateTop = false;
        return false;
      } else {
        return false;
      }
    }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
