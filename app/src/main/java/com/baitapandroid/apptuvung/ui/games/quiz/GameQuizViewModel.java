package com.baitapandroid.apptuvung.ui.games.quiz;

import android.util.Range;

import androidx.annotation.IntRange;
import androidx.lifecycle.MutableLiveData;

import com.baitapandroid.apptuvung.ui.games.GameViewModel;

import java.io.IOException;
import java.util.Arrays;

public class GameQuizViewModel extends GameViewModel {
    private final MutableLiveData<Integer> mQuizMode = new MutableLiveData<>(0),
            mAnswer = new MutableLiveData<>(0),
            mInput = new MutableLiveData<>(0);

    private final MutableLiveData<String[]> mChoices = new MutableLiveData<>();
    private final MutableLiveData<String> mQuestion = new MutableLiveData<>();

    public MutableLiveData<Integer> getAnswer() {
        return mAnswer;
    }

    public int getAnswerValue() {
        return mAnswer.getValue() == null ? 0 : mAnswer.getValue();
    }

    public void setAnswer(int answer) {
        if (answer < 0 || answer > 3) throw new IllegalArgumentException("Quiz choice can only range from 0 to 3");
        mAnswer.setValue(answer);
    }

    public MutableLiveData<Integer> getInput() {
        return mInput;
    }

    public int getInputValue() {
        return mInput.getValue() == null ? 0 : mInput.getValue();
    }

    public void setInput(int input) {
        if (input < 0 || input > 3) throw new IllegalArgumentException("Quiz choice can only range from 0 to 3");
        mInput.setValue(input);
    }

    public MutableLiveData<Integer> getQuizMode() {
        return mQuizMode;
    }

    public int getQuizModeValue() {
        return mQuizMode.getValue() == null ? 0 : mQuizMode.getValue();
    }

    public void setQuizMode(int quizMode) {
        mQuizMode.setValue(quizMode);
    }

    public MutableLiveData<String[]> getChoices() {
        return mChoices;
    }

    public String[] getChoicesValue() {
        return mChoices.getValue();
    }

    public void setChoices(String[] choices) {
        mChoices.setValue(Arrays.copyOf(choices, choices.length));
    }

    public MutableLiveData<String> getQuestion() {
        return mQuestion;
    }

    public String getQuestionValue() {
        return mQuestion.getValue();
    }

    public void setQuestion(String question) {
        mQuestion.setValue(question);
    }
}
