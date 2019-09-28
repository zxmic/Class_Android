package com.example.myapplicationnull;

class Question {
    private int mTextResId;
    private boolean mActionTrue;

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isActionTrue() {
        return mActionTrue;
    }

    public void setTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public void setActionTrue(boolean mActionTrue) {
        this.mActionTrue = mActionTrue;
    }

    public Question(int mTextResId, boolean mActionTrue) {
        this.mTextResId=mTextResId;
        this.mActionTrue=mActionTrue;
    }
}
