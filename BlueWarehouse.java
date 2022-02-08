package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "BlueWarehouse (Blocks to Java)")
public class BlueWarehouse extends LinearOpMode {

  private DcMotor leftBackMotor;
  private DcMotor leftFrontMotor;
  private DcMotor rightFrontMotor;
  private DcMotor rightBackMotor;
  private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Swivel;


  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    
    // Put initialization blocks here.
    leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
    leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
    rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
    rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        Swivel = hardwareMap.get(DcMotor.class, "Swivel");
    rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    Swivel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);   
    rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    Swivel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    waitForStart();

    Go_Back(2.0);
    Start();  
  }

  /**
   * Describe this function...
   */
  private void Go_Back__1_M_old() {
    for (int count3 = 0; count3 < 2200; count3++) {
      if (opModeIsActive()) {
        rightFrontMotor.setPower(-1);
        rightBackMotor.setPower(-1);
        leftBackMotor.setPower(1);
        leftFrontMotor.setPower(1);
      }
    }
  }
  
  private void Go_Back(double runTime) {
    rightFrontMotor.setPower(-0.5);
    leftFrontMotor.setPower(0.5);
    rightBackMotor.setPower(-0.5);
    leftBackMotor.setPower(0.5);
    runtime.reset();
    
    while( (runtime.seconds() <  runTime ) && (opModeIsActive())){
    } 
    
    rightFrontMotor.setPower(0);
    leftFrontMotor.setPower(0);
    rightBackMotor.setPower(0);
    leftBackMotor.setPower(0);
        runtime.reset();
  }
    private void Start() {
 Swivel.setPower(1);
    runtime.reset();
    
    while( (runtime.seconds() <  .5 ) && (opModeIsActive())){
    } 
    
          Swivel.setPower(0);
        runtime.reset();
  }

  /**
   * Describe this function...
   */
  private void Wait() {
    rightBackMotor.setPower(0);
    leftFrontMotor.setPower(0);
    leftBackMotor.setPower(0);
    rightFrontMotor.setPower(0);
    sleep(1500);
  }

  /**
   * Describe this function...
   */
  private void ALL_STOP() {
    rightBackMotor.setPower(0);
    leftFrontMotor.setPower(0);
    leftBackMotor.setPower(0);
    rightFrontMotor.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void turn_90_degree() {
    for (int count4 = 0; count4 < 16000; count4++) {
      leftBackMotor.setPower(0.125);
      leftFrontMotor.setPower(0.125);
      rightFrontMotor.setPower(0.125);
      rightBackMotor.setPower(0.125);
    }
  }

  /**
   * Describe this function...
   */
  private void turn__90_degree() {
    for (int count5 = 0; count5 < 16000; count5++) {
      leftBackMotor.setPower(-0.125);
      leftFrontMotor.setPower(-0.125);
      rightFrontMotor.setPower(-0.125);
      rightBackMotor.setPower(-0.125);
    }
  }
}
