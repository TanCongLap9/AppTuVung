package com.baitapandroid.apptuvung.ui.games.unscramble;

import androidx.lifecycle.MutableLiveData;

import com.baitapandroid.apptuvung.util.Arrays;
import com.baitapandroid.apptuvung.ui.games.GameViewModel;

public class GameUnscrambleViewModel extends GameViewModel {
    private MutableLiveData<char[]> mInput = new MutableLiveData<>();

    public MutableLiveData<char[]> getInput() {
        return mInput;
    }

    public char[] getInputValue() {
        return mInput.getValue();
    }

    public void setInput(char[] input) {
        mInput.setValue(input);
    }

    public void swapInput(int i, int j) {
        char[] array = getInputValue().clone();
        Arrays.swap(array, i, j);
        setInput(array);
    }
}
