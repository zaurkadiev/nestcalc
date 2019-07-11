package nestcalc.mymorce.com.Interface;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

public class ButtonTouchListener implements View.OnTouchListener {

    private Animator.AnimatorListener upAnimatorListener;
    private Animator.AnimatorListener downAnimatorListener;

    ButtonTouchListener(Animator.AnimatorListener releaseAnimatorListener, Animator.AnimatorListener downAnimatorListener){
        this.upAnimatorListener = releaseAnimatorListener;
        this.downAnimatorListener = downAnimatorListener;
    }

    ButtonTouchListener(){
        this.downAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
        this.upAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.92f),
                        ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.92f)
                );
                set.setInterpolator(new OvershootInterpolator());
                set.addListener(downAnimatorListener);
                set.start();
                break;
            case MotionEvent.ACTION_HOVER_ENTER:
                AnimatorSet set1 = new AnimatorSet();
                set1.playTogether(
                        ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.92f),
                        ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.92f)
                );
                set1.setInterpolator(new OvershootInterpolator());
                set1.start();
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                AnimatorSet set2 = new AnimatorSet();
                set2.playTogether(
                        ObjectAnimator.ofFloat(view, View.SCALE_X, 0.92f, 1f),
                        ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.92f, 1f)
                );
                set2.setInterpolator(new OvershootInterpolator());
                set2.start();
                break;
            case MotionEvent.ACTION_OUTSIDE:
                AnimatorSet set4 = new AnimatorSet();
                set4.playTogether(
                        ObjectAnimator.ofFloat(view, View.SCALE_X, 0.92f, 1f),
                        ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.92f, 1f)
                );
                set4.setInterpolator(new OvershootInterpolator());
                set4.start();
                break;
            case MotionEvent.ACTION_UP:
                AnimatorSet set3 = new AnimatorSet();
                set3.playTogether(
                        ObjectAnimator.ofFloat(view, View.SCALE_X, 0.92f, 1f),
                        ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.92f, 1f)
                );
                set3.addListener(upAnimatorListener);
                set3.setInterpolator(new OvershootInterpolator());
                set3.start();
                break;
        }

        return false;
    }
}
