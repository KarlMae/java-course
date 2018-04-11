package io.github.karlmae.minegoat.helpers;

import android.content.Context;

/*   Method from
 *   https://medium.com/@euryperez/android-pearls-set-size-to-a-view-in-dp-programatically-71d22eed7fc0
 */

public class PixelSizeConverter {

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
