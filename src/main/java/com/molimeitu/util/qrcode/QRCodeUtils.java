/**
 * @项目名称：ParkingApp
 * @文件名：QRCodeUtils.java
 * @版本信息：
 * @日期：2015-4-15
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.molimeitu.util.qrcode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.molimeitu.util.qrcode.RGBLuminanceSource;

/**
 *
 * @项目名称：ParkingApp
 * @类名称：QRCodeUtils
 * @类描述：
 * @创建人：lianfeng
 * @创建时间：2015-4-15 下午3:45:59    
 * @修改人：lianfeng
 * @修改时间：2015-4-15 下午3:45:59    
 * @修改备注：
 * @version
 *
 */
public class QRCodeUtils {

    public static final int QR_WIDTH = 500;

    public static final int QR_HEIGHT = 500;

    public static final int IC_WIDTH = 40;

    public static final int IC_HEIGHT = 40;

    /**
     * 创建二维码图片
     * @param content
     * @return
     */
    public static Bitmap createImage(String content) {
        Bitmap bitmap = null;
        try {
            // 需要引入core包
            QRCodeWriter writer = new QRCodeWriter();

            if (content == null || "".equals(content) || content.length() < 1) {
                return null;
            }

            // 把输入的文本转为二维码
            // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
            BitMatrix martix = writer.encode(content, BarcodeFormat.QR_CODE,
                    QR_WIDTH, QR_HEIGHT);

            System.out.println("w:" + martix.getWidth() + "h:"
                    + martix.getHeight());

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(content,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }

            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * @description 生成带中心图标的二维码
     * @date 2015-4-15
     * @param content 二维码内容
     * @param bmIcon 中心图标
     * @return
     */
    public static Bitmap createImageWithIcon(String content, Bitmap bmIcon) {
        if (content == null || "".equals(content) || content.length() < 1 || bmIcon == null) {
            return null;
        }

        // 缩放图片
        Matrix iconM = new Matrix();
        float scaleX = (float) 2 * IC_WIDTH / bmIcon.getWidth();
        float scaleY = (float) 2 * IC_HEIGHT / bmIcon.getHeight();
        iconM.setScale(scaleX, scaleY);
        // 重新构造一个图片
        Bitmap mNewIcon = Bitmap.createBitmap(bmIcon, 0, 0, bmIcon.getWidth(),
                bmIcon.getHeight(), iconM, false);

        Bitmap bitmap = null;
        try {
            // 需要引入core包
            QRCodeWriter writer = new QRCodeWriter();
            // 生成二维矩阵
            BitMatrix martix = writer.encode(content, BarcodeFormat.QR_CODE,
                    QR_WIDTH, QR_HEIGHT);
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(content,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int halfW = QR_WIDTH / 2;
            int halfH = QR_HEIGHT / 2;
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (x > halfW - IC_WIDTH && x < halfW + IC_WIDTH && y > halfH - IC_HEIGHT && y < halfH + IC_HEIGHT) {
                        pixels[y * QR_WIDTH + x] = mNewIcon.getPixel(x - halfW + IC_WIDTH, y - halfH + IC_HEIGHT);
                    }
                    else {
                        pixels[y * QR_WIDTH + x] = bitMatrix.get(x, y) ? 0xff000000 : 0xffffffff;
                    }
                }
            }

            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 解析二维码图片
     * @param srImage
     * @return
     */
    public static String scanningImage(Bitmap srImage) {

        Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

        // 获得待解析的图片
//        Bitmap bitmap = ((BitmapDrawable) qr_image.getDrawable()).getBitmap();
        RGBLuminanceSource source = new RGBLuminanceSource(srImage);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        try {
            result = reader.decode(bitmap1, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return result.getText();
    }
}
