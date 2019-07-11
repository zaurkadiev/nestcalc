package nestcalc.mymorce.com.Helpers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by user on 30/01/2018.
 */

public class AnimationHelper {

    private static boolean animFlag = false;

    public static void animateSaveBuffer(View view){
        if (animFlag)
            return;

        AnimatorSet set = new AnimatorSet();
        AnimatorSet s1 = new AnimatorSet();
        AnimatorSet s2 = new AnimatorSet();
        s1.playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.96f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.96f)
        );
        s2.playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 0.96f, 1f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.96f, 1f)
        );
        set.playSequentially(
                s1,
                s2
        );
        set.setInterpolator(new OvershootInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animFlag = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animFlag = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public static void animateTranslationY(View view, float start, float end, Interpolator interpolator, Long duration, Animator.AnimatorListener animationListener){

        if (animFlag)
            return;

        duration = duration == null ? 300 : duration;

        Animator animator = ObjectAnimator.ofFloat(view, View.Y, start, end);
        animator.setInterpolator(interpolator);
        animator.setDuration(duration);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animFlag = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animFlag = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.addListener(animationListener);
        animator.start();
    }

    public static void animateEstimateError(View view, Context context){

        int offset = CommonHelper.convertDpToPixels(6, context);

        if (animFlag)
            return;

        AnimatorSet set = new AnimatorSet();
        Animator a1 = ObjectAnimator.ofFloat(view, View.Y, 0, -offset);
        Animator a2 = ObjectAnimator.ofFloat(view, View.Y, -offset, 0);
        a1.setInterpolator(new DecelerateInterpolator());
        a2.setInterpolator(new BounceInterpolator());
        set.playSequentially(
                a1,
                a2
        );
        set.setDuration(200);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animFlag = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animFlag = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public static void animateButtonTap(View view){
        AnimatorSet set = new AnimatorSet();
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.92f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.92f)
        );
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 0.92f, 1f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.92f, 1f)
        );
        set.playSequentially(
                set1,
                set2
        );
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(160);
        set.start();
    }

    public static void animateContainerScale(View view, boolean flag){

        if (!flag){
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(view, View.SCALE_X, 0.92f, 1f),
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.92f, 1f)
            );
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    animFlag = true;
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    animFlag = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    animFlag = false;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            set.setInterpolator(new OvershootInterpolator());
            set.start();
        } else {
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.92f),
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.92f)
            );
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    animFlag = true;
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    animFlag = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    animFlag = false;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            set.setInterpolator(new OvershootInterpolator());
            set.start();
        }


    }

    public static void animateToggle(View view){

        if (animFlag)
            return;

        if (view.getScaleX() == 1f){
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.92f),
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.92f)
            );
            set.setInterpolator(new OvershootInterpolator());
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    animFlag = true;
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    animFlag = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            set.setDuration(300);
            set.start();
        } else {
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(view, View.SCALE_X, 0.92f, 1f),
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.92f, 1f)
            );
            set.setInterpolator(new OvershootInterpolator());
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    animFlag = true;
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    animFlag = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            set.setDuration(300);
            set.start();
        }
    }

    public static void animateVersion(View view, int distance, int angle){
        if (animFlag)
            return;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, View.ROTATION, 0, -angle),
                ObjectAnimator.ofFloat(view, View.X, view.getX(), view.getX() - distance)
        );
        set.setInterpolator(new OvershootInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animFlag = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animFlag = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public static void crazyTripAnimation(View view){
        if (animFlag)
            return;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.01f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.01f),
                ObjectAnimator.ofFloat(view, View.ROTATION, 0, 2160),
                ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0.01f)
        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animFlag = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animFlag = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.setDuration(1000);
        set.start();
    }
}
