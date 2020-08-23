package com.huawei.arengine.demos.java.body3d.animation.math;

import androidx.annotation.NonNull;

public class Quaternion {

    public static final Quaternion UNIT = new Quaternion(1.f, 0.f, 0.f, 0.f);

    public float w  = 1.f;
    public float x = 0.f;
    public float y = 0.f;
    public float z = 0.f;

    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion(Vector3 vector3, float radian) {
        this.w = (float) Math.cos(radian / 2);
        this.x = (float) (vector3.x * Math.sin(radian / 2));
        this.y = (float) (vector3.y * Math.sin(radian / 2));
        this.z = (float) (vector3.z * Math.sin(radian / 2));
    }



    @NonNull
    @Override
    public String toString() {
        return "w: " + w + " x: " + x + " y: " + y + " z: " + z;
    }
}
