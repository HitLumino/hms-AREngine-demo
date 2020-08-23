package com.huawei.arengine.demos.java.body3d.animation.math;

import android.util.Log;

import androidx.annotation.NonNull;

public class Vector3 {
    private static final String TAG = Vector3.class.getSimpleName();
    private static final float MINIMUN = 1e-6f;

    public static final Vector3 ONE = new Vector3(1.f, 1.f, 1.f);
    public static final Vector3 ZERO = new Vector3(0.f, 0.f, 0.f);

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

    public float bettwenRadian(Vector3 vector3) {
        if (vector3 == null) {
            return 0.f;
        }
        float len = Math.max(getLength() * vector3.getLength(), MINIMUN);
        float value = dot(vector3) / len;
        value = Math.min(Math.max(value, -1), 1);
        return (float) Math.acos(value);

    }

    public static Vector3 direction(Vector3 start, Vector3 end) {
        if (start == null || end == null) {
            return Vector3.ONE;
        }
        return new Vector3(end.x - start.x, end.y - start.y, end.z - start.z);
    }

    public Vector3 normalize() {
        if (getLength() < MINIMUN) {
            return this;
        }
        return new Vector3(x / getLength(), y / getLength(), z / getLength());
    }

    @NonNull
    @Override
    public String toString() {
        return " x: " + x + " y: " + y + " z: " + z;
    }
}
