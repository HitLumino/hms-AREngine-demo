package com.huawei.arengine.demos.java.body3d.animation.skeleton;

import android.util.Log;

import com.huawei.arengine.demos.java.body3d.animation.math.Quaternion;
import com.huawei.arengine.demos.java.body3d.animation.math.Vector3;
import com.huawei.hiar.ARBody;

import java.util.HashMap;
import java.util.Map;

public class SkeletonData {
    private static final String TAG = SkeletonData.class.getSimpleName();

    private static Map<Integer, Vector3> positionMap = new HashMap<>();
    private static Map<String, Vector3> directionMap = new HashMap<>();
    private static Map<String, Quaternion> quaternionMap = new HashMap<>();


    private static final int SKELETON_SIZE = 15;
    private static final String KEY_BONE_14_1 = "Bone_14_1";
    private static final String KEY_BONE_14_8 = "Bone_14_8";
    private static final String KEY_BONE_14_11 = "Bone_14_11";
    private static final String KEY_BONE_1_2 = "Bone_1_2";
    private static final String KEY_BONE_1_5 = "Bone_1_5";
    private static final String KEY_BONE_1_0 = "Bone_1_0";
    private static final String KEY_BONE_8_9 = "Bone_8_9";
    private static final String KEY_BONE_9_10 = "Bone_9_10";
    private static final String KEY_BONE_11_12 = "Bone_11_12";
    private static final String KEY_BONE_12_13 = "Bone_12_13";
    private static final String KEY_BONE_2_3 = "Bone_2_3";
    private static final String KEY_BONE_3_4 = "Bone_3_4";
    private static final String KEY_BONE_5_6 = "Bone_5_6";
    private static final String KEY_BONE_6_7 = "Bone_6_7";

    static {
        directionMap.put(KEY_BONE_14_1, Vector3.ZERO);
        directionMap.put(KEY_BONE_14_8, Vector3.ZERO);
        directionMap.put(KEY_BONE_14_11, Vector3.ZERO);
        directionMap.put(KEY_BONE_1_2, Vector3.ZERO);
        directionMap.put(KEY_BONE_1_5, Vector3.ZERO);
        directionMap.put(KEY_BONE_1_0, Vector3.ZERO);
        directionMap.put(KEY_BONE_8_9, Vector3.ZERO);
        directionMap.put(KEY_BONE_9_10, Vector3.ZERO);
        directionMap.put(KEY_BONE_11_12, Vector3.ZERO);
        directionMap.put(KEY_BONE_12_13, Vector3.ZERO);
        directionMap.put(KEY_BONE_2_3, Vector3.ZERO);
        directionMap.put(KEY_BONE_3_4, Vector3.ZERO);
        directionMap.put(KEY_BONE_5_6, Vector3.ZERO);
        directionMap.put(KEY_BONE_6_7, Vector3.ZERO);

        quaternionMap.put(KEY_BONE_14_1, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_14_8, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_14_11, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_1_2, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_1_5, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_1_0, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_8_9, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_9_10, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_11_12, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_12_13, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_2_3, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_3_4, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_5_6, Quaternion.UNIT);
        quaternionMap.put(KEY_BONE_6_7, Quaternion.UNIT);

        for (int i = 0; i < SKELETON_SIZE; i++) {
            positionMap.put(i, Vector3.ZERO);
        }
    }

    private static void updatePositionMap(ARBody body) {
        float[] data = body.getSkeletonPoint3D();
        for (int i = 0; i < SKELETON_SIZE; i++) {
            positionMap.put(i, new Vector3(data[3 * i], data[3 * i + 1], data[3 * i + 2]));
        }
        Log.e(TAG, "updatePositionMap: " + positionMap.get(0));
    }

    public static Map<Integer, Vector3> getPositionMap(ARBody body) {
        updatePositionMap(body);
        return positionMap;
    }

    private static void updateDirectionMap() {
        for (String key : directionMap.keySet()) {
            String[] points = key.split("_");
            directionMap.put(key, Vector3.direction(positionMap.get(Integer.valueOf(points[2])),
                positionMap.get(Integer.valueOf(points[1]))));
        }
    }

    public static Map<String, Vector3> getDirectionMap(ARBody body) {
        updatePositionMap(body);
        updateDirectionMap();
        return directionMap;
    }

    public static void updateQuaternionMap(Map<String, Vector3> directionMap1, Map<String, Vector3> directionMap2) {
        for (String key : directionMap1.keySet()) {
            Vector3 axis = directionMap1.get(key).cross(directionMap2.get(key)).normalize();
            float angle = directionMap1.get(key).bettwenRadian(directionMap2.get(key));
            quaternionMap.put(key, new Quaternion(axis, angle));
        }
    }

    public static Map<String, Quaternion> getQuaternionMap() {
        return quaternionMap;
    }
}
