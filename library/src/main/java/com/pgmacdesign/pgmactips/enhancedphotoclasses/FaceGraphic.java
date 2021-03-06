package com.pgmacdesign.pgmactips.enhancedphotoclasses;

/**
 * Graphic instance for rendering face position, orientation, and landmarks within an associated
 * graphic overlay view.
 * Samples pulled from: https://github.com/googlesamples/android-vision
 */
@Deprecated
class FaceGraphic { //extends GraphicOverlay.Graphic {
//    private static final float FACE_POSITION_RADIUS = 10.0f;
//    private static final float ID_TEXT_SIZE = 40.0f;
//    private static final float ID_Y_OFFSET = 50.0f;
//    private static final float ID_X_OFFSET = -50.0f;
//    private static final float BOX_STROKE_WIDTH = 5.0f;
//
//    private static final int COLOR_CHOICES[] = {
//            Color.BLUE,
//            Color.CYAN,
//            Color.GREEN,
//            Color.MAGENTA,
//            Color.RED,
//            Color.WHITE,
//            Color.YELLOW
//    };
//    private static int mCurrentColorIndex = 0;
//
//    private Paint mFacePositionPaint;
//    private Paint mIdPaint;
//    private Paint mBoxPaint;
//
//    private volatile Face mFace;
//    private int mFaceId;
//    private float mFaceHappiness;
//
//    FaceGraphic(GraphicOverlay overlay) {
//        super(overlay);
//        L.m("Face Graphic = " + 44);
//        // TODO: 8/30/2016 this is kinda neat... look into using for other projects
//        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
//        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];
//
//        mFacePositionPaint = new Paint();
//        mFacePositionPaint.setColor(selectedColor);
//
//        mIdPaint = new Paint();
//        mIdPaint.setColor(selectedColor);
//        mIdPaint.setTextSize(ID_TEXT_SIZE);
//
//        mBoxPaint = new Paint();
//        mBoxPaint.setColor(selectedColor);
//        mBoxPaint.setStyle(Paint.Style.STROKE);
//        mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
//    }
//
//    void setId(int id) {
//        mFaceId = id;
//    }
//
//
//    /**
//     * Updates the face instance from the detection of the most recent frame.  Invalidates the
//     * relevant portions of the overlay to trigger a redraw.
//     */
//    void updateFace(Face face) {
//        mFace = face;
//        postInvalidate();
//    }
//
//    /**
//     * Draws the face annotations for position on the supplied canvas.
//     */
//    @Override
//    public void draw(Canvas canvas) {
//        L.m("Face Graphic = " + 82);
//        Face face = mFace;
//        if (face == null) {
//            return;
//        }
//
//        // Draws a circle at the position of the detected face, with the face's track id below.
//        float x = translateX(face.getPosition().x + face.getWidth() / 2);
//        float y = translateY(face.getPosition().y + face.getHeight() / 2);
//        //canvas.drawCircle(x, y, FACE_POSITION_RADIUS, mFacePositionPaint);
//        //canvas.drawText("id: " + mFaceId, x + ID_X_OFFSET, y + ID_Y_OFFSET, mIdPaint);
//
//        float smilingProbability = face.getIsSmilingProbability();
//        float rightEyeOpenProbability = face.getIsRightEyeOpenProbability();
//        float leftEyeOpenProbability = face.getIsLeftEyeOpenProbability();
//
//        /*
//        if(smilingProbability >= 0.85F){
//            canvas.drawText("You Are Smiling!", x - ID_X_OFFSET, y - ID_Y_OFFSET, mIdPaint);
//        } else {
//            canvas.drawText("happiness: " + String.format("%.2f", face.getIsSmilingProbability()), x - ID_X_OFFSET, y - ID_Y_OFFSET, mIdPaint);
//        }
//        if(rightEyeOpenProbability >= 0.50F){
//            canvas.drawText("Right Eye is open!", x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mIdPaint);
//        } else {
//            canvas.drawText("right eye: " + String.format("%.2f", face.getIsRightEyeOpenProbability()), x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mIdPaint);
//        }
//        if(leftEyeOpenProbability >= 0.50F){
//            canvas.drawText("Left eye is open!", x - ID_X_OFFSET*2, y - ID_Y_OFFSET*2, mIdPaint);
//        } else {
//            canvas.drawText("left eye: " + String.format("%.2f", face.getIsLeftEyeOpenProbability()), x - ID_X_OFFSET*2, y - ID_Y_OFFSET*2, mIdPaint);
//        }
//        */
//
//        // Draws a bounding box around the face.
//        float xOffset = scaleX(face.getWidth() / 1.5f);
//        float yOffset = scaleY(face.getHeight() / 1.5f);
//        float left = x - xOffset;
//        float top = y - yOffset;
//        float right = x + xOffset;
//        float bottom = y + yOffset;
//        //canvas.drawRect(left, top, right, bottom, mBoxPaint);
//        if(SystemUtilities.userHasLollipopOrHigher()){
//            canvas.drawOval(left, top, right, bottom, mBoxPaint);
//        } else {
//            canvas.drawCircle(x, y, 500F, mBoxPaint);
//        }
//
//
//    }
}
