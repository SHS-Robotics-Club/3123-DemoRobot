package org.firstinspires.ftc.teamcode.cv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mentor.samples.ObjectDector.DNNObject;
import org.firstinspires.ftc.teamcode.mentor.samples.ObjectDector.OPCVFFObjectDetector3;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OpenCvShippingElementDetector extends OpenCvPipeline {



    public enum TSELocation {
        P1_RED_LEFT,
        P1_RED_MIDDLE,
        P1_RED_RIGHT,
        P2_RED_LEFT,
        P2_RED_MIDDLE,
        P2_RED_RIGHT,
        P1_BLUE_LEFT,
        P1_BLUE_MIDDLE,
        P1_BLUE_RIGHT,
        P2_BLUE_LEFT,
        P2_BLUE_MIDDLE,
        P2_BLUE_RIGHT,
        NONE
    }

    TSELocation location;
    Map<TSELocation, Integer> levels = new HashMap<>();


    private int width; // width of the image
    private int height = 240;
    private double inScaleFactor = 0.007843;
    private double thresholdDnn =  0.2;
    private double meanVal = 127.5;
    private Dnn cvDNN = null;
    private Net net = null;
    private Telemetry telemetry = null;
    private Mat imageRGB = new Mat();

    private final String[] classNames = {"background",
            "p1_blue_right", "p1_blue_left", "p1_blue_middle", "p2_blue_right", "p2_blue_left", "p2_blue_middle" };




    private static List<Scalar> colors=new ArrayList<>();

    /**
     *
     * @param width The width of the image (check your camera)
     */
    public OpenCvShippingElementDetector(int width, int height, Telemetry telemetry) {
        this.width = width;
        this.height = height;
        this.telemetry = telemetry;

        levels.put(TSELocation.NONE,0);

        levels.put(TSELocation.P1_BLUE_RIGHT,1);
        levels.put(TSELocation.P2_BLUE_RIGHT,1);
        levels.put(TSELocation.P1_RED_RIGHT,1);
        levels.put(TSELocation.P2_RED_RIGHT,1);

        levels.put(TSELocation.P1_BLUE_MIDDLE,2);
        levels.put(TSELocation.P2_BLUE_MIDDLE,2);
        levels.put(TSELocation.P1_RED_MIDDLE,2);
        levels.put(TSELocation.P2_RED_MIDDLE,2);

        levels.put(TSELocation.P1_BLUE_LEFT,3);
        levels.put(TSELocation.P2_BLUE_LEFT,3);
        levels.put(TSELocation.P1_RED_LEFT,3);
        levels.put(TSELocation.P2_RED_LEFT,3);

        location = TSELocation.NONE;


        cvDNN = new Dnn();
        net = cvDNN.readNetFromTensorflow("/sdcard/FIRST/EasyOpenCV/models/freight_frenzy_tse_optimized_graph.pb");
        for(int i=0; i<classNames.length; i++)
            colors.add(randomColor());

    }


    @Override
    public Mat processFrame(Mat inputFrame) {

        telemetry.addLine("Inside ProcessFrame");
        //telemetry.update();
        //Mat imageRGB = new Mat();
        Mat blob = null;
        Mat detections = null;

        Imgproc.cvtColor(inputFrame,imageRGB,Imgproc.COLOR_RGBA2RGB);

        blob = Dnn.blobFromImage(imageRGB, inScaleFactor,
                new Size(width, height),
                new Scalar(meanVal, meanVal, meanVal),
                false, false);

        net.setInput(blob);
        detections = net.forward();
        float confThreshold = 0.5f;

        for (int i = 0; i < detections.rows(); ++i) {
            Mat row = detections.row(i);
            //Mat scores = row.colRange(5, detections.cols());
            Core.MinMaxLocResult mm = Core.minMaxLoc(row);
            //Core.MinMaxLocResult mm = Core.minMaxLoc(scores);
            float confidence = (float) mm.maxVal;
            Point classIdPoint = mm.maxLoc;


            if (confidence > confThreshold) {


                int centerX = (int) (row.get(0, 0)[0] * imageRGB.cols());
                int centerY = (int) (row.get(0, 1)[0] * imageRGB.rows());
                int width = (int) (row.get(0, 2)[0] * imageRGB.cols());
                int height = (int) (row.get(0, 3)[0] * imageRGB.rows());

                int left = (int) (centerX - width * 0.5);
                int top = (int) (centerY - height * 0.5);
                int right = (int) (centerX + width * 0.5);
                int bottom = (int) (centerY + height * 0.5);

                Point left_top = new Point(left, top);
                Point right_bottom = new Point(right, bottom);
                Point label_left_top = new Point(left, top - 5);
                DecimalFormat df = new DecimalFormat("#.##");

                int class_id = (int) classIdPoint.x;
                String className = classNames[class_id].toString();
                String label =  className + ": " + df.format(confidence);
                Scalar color = colors.get(class_id);

                telemetry.addData("This is a real new", className);
                //telemetry.update();

                switch (className)
                {
                    case "p1_blue_left":
                        location = TSELocation.P1_BLUE_LEFT;
                        break;

                    case "p1_blue_right":
                        //telemetry.addData("This is a new p1br", className);
                        //telemetry.update();
                        location = TSELocation.P1_BLUE_RIGHT;
                        break;

                    case "p1_blue_middle":
                        location = TSELocation.P1_BLUE_MIDDLE;
                        break;

                    case "p2_blue_left":
                        location = TSELocation.P2_BLUE_LEFT;
                        break;

                    case "p2_blue_right":
                        location = TSELocation.P2_BLUE_RIGHT;
                        break;

                    case "p2_blue_middle":
                        location = TSELocation.P2_BLUE_MIDDLE;
                        break;

                    case "p1_red_left":
                        location = TSELocation.P1_RED_LEFT;
                        break;

                    case "p1_red_right":
                        //telemetry.addData("This is a new p1br", className);
                        //telemetry.update();
                        location = TSELocation.P1_RED_RIGHT;
                        break;

                    case "p1_red_middle":
                        location = TSELocation.P1_RED_MIDDLE;
                        break;

                    case "p2_red_left":
                        location = TSELocation.P2_RED_LEFT;
                        break;

                    case "p2_red_right":
                        location = TSELocation.P2_RED_RIGHT;
                        break;

                    case "p2_red_middle":
                        location = TSELocation.P2_RED_MIDDLE;
                        break;

                    default:
                        location = TSELocation.NONE;
                }

                Imgproc.rectangle(imageRGB, left_top, right_bottom, color, 3, 2);
                Imgproc.putText(imageRGB, label, label_left_top, Imgproc.FONT_HERSHEY_SIMPLEX, 1, new Scalar(0, 0, 0), 4);
                Imgproc.putText(imageRGB, label, label_left_top, Imgproc.FONT_HERSHEY_SIMPLEX, 1, new Scalar(255, 255, 255), 2);
            }

        }

        return imageRGB;
    }

    private Scalar randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Scalar(r,g,b);

    }
    public TSELocation getLocation() {
        return this.location;
    }
    public int getTSELevel(){
        telemetry.addData("getTSELevel", location);
        return levels.get(location);
    }
}