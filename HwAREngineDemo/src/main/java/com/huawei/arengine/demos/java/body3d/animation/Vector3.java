package com.huawei.arengine.demos.java.body3d.animation;

import android.util.Log;

public class Vector3 {
    private static final String TAG = Vector3.class.getSimpleName();
    private static final float MINMUN = 1e-6f;

    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float dot(Vector3 vector3) {
        return x * vector3.x + y * vector3.y + z * vector3.z;
    }

    public Vector3 cross(Vector3 vector3) {
        if (vector3 == null) {
            Log.e(TAG, "invalid param.");
            return this;
        }
        return new Vector3(
            vector3.z * y - vector3.y * z,
            vector3.x * z - vector3.z * x,
            vector3.y * x - vector3.x * y
        );
    }

    public float getLength() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float bettwenRadian(Vector3 left, Vector3 right) {
        float value = left.getLength() * right.getLength();
        float dot = left.dot(right);
        if (dot < MINMUN) {
            dot = MINMUN;
        }

        return (float) Math.min(Math.acos(value / dot), Math.PI);
    }
}
