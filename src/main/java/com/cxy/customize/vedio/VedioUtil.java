//package com.cxy.customize.vedio;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class VedioUtil {
//
//    /**
//     * 获取视频时长，单位为秒
//     *
//     * @param video 源视频文件
//     * @return 时长（s）
//     */
//    public static long getVideoDuration(File video) {
//        long duration = 0L;
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
//        try {
//            ff.start();
//            duration = ff.getLengthInTime() / (1000 * 1000);
//            ff.stop();
//        } catch (FrameGrabber.Exception e) {
//            e.printStackTrace();
//        }
//        return duration;
//    }
//
//
//    /**
//     * 截取视频获得指定帧的图片
//     *
//     * @param video   源视频文件
//     * @param picPath 截图存放路径
//     */
//    public static void getVideoPic(File video, String picPath) {
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
//        try {
//            ff.start();
//
//            // 截取中间帧图片(具体依实际情况而定)
//            int i = 0;
//            int length = ff.getLengthInFrames();
//            int middleFrame = length / 2;
//            Frame frame = null;
//            while (i < length) {
//                frame = ff.grabFrame();
//                if ((i > middleFrame) && (frame.image != null)) {
//                    break;
//                }
//                i++;
//            }
//
//            // 截取的帧图片
//            Java2DFrameConverter converter = new Java2DFrameConverter();
//            BufferedImage srcImage = converter.getBufferedImage(frame);
//            int srcImageWidth = srcImage.getWidth();
//            int srcImageHeight = srcImage.getHeight();
//
//            // 对截图进行等比例缩放(缩略图)
//            int width = 480;
//            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
//            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
//
//            File picFile = new File(picPath);
//            ImageIO.write(thumbnailImage, "jpg", picFile);
//
//            ff.stop();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        String videoPath =  "D:\\cxy\\1.mp4";
//        File video = null;
//        video = new File(videoPath);
//        String picPath = "video.jpg";
//        getVideoPic(video, picPath);
//
//        long duration = getVideoDuration(video);
//        System.out.println("videoDuration = " + duration);
//    }
//
//
//
//}
