package com.pgmacdesign.pgmacutilities;

import io.realm.RealmObject;

/**
 * Created by pmacdowell on 8/19/2016.
 */
public class TESTINGPOJO3 extends RealmObject {
    private int x;
    private String testing;

    public String getTesting() {
        return testing;
    }

    public void setTesting(String testing) {
        this.testing = testing;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
