package net.wospy.swirly;

public  class Utils {
    public static float findMinimum(float... nums){
        float temp=0;
        for (float num : nums) {
            temp=Math.min(num,temp);
        }
        return temp;
    }
    public static float findMaximum(float... nums){
        float temp=0;
        for (float num : nums) {
            temp=Math.max(num,temp);
        }
        return temp;
    }

}
