package com.baitapandroid.apptuvung.ui.games;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.baitapandroid.apptuvung.R;
import com.baitapandroid.apptuvung.util.Settings;
import com.baitapandroid.apptuvung.databinding.GameFinishedBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GameFinished {
    private final Context mCtx;
    private final GameFinishedBinding mB;
    private boolean mIsSuccess;
    private MediaPlayer mSuccessPlayer, mFailPlayer;
    private DialogInterface.OnDismissListener mListener;
    private final Settings mSt;

    public GameFinished(Context context) {
        mCtx = context;
        mSt = Settings.from(context);
        mB = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.game_finished, null, false);;
        mB.setSettings(mSt);
        if (!mSt.getInGameSound()) return;
        mFailPlayer = MediaPlayer.create(context, R.raw.fail);
        mSuccessPlayer = MediaPlayer.create(context, R.raw.success);
    }

    public GameFinished with(boolean success, Date elapsed, int hearts, int progress) {
        mIsSuccess = success;
        mB.setSuccess(success);
        mB.setTime(new SimpleDateFormat("mm:ss", Locale.US).format(elapsed));
        mB.setHearts(hearts);
        mB.setProgress(progress);
        return this;
    }

    public GameFinished withListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mListener = onDismissListener;
        return this;
    }

    public ViewGroup getView() {
        return (ViewGroup) mB.getRoot();
    }

    public AlertDialog.Builder createDialog() {
        if (mSt.getInGameSound())
            (mIsSuccess ? mSuccessPlayer : mFailPlayer).start();
        return new AlertDialog.Builder(mCtx)
                .setTitle(mIsSuccess ? R.string.game_finished_success_title : R.string.game_finished_failure_title)
                .setView(getView())
                .setPositiveButton("OK", null)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        release();
                        mListener.onDismiss(dialogInterface);
                    }
                });
    }

    public static AlertDialog.Builder createConfirmLeaveDialog(Context context, DialogInterface.OnClickListener onLeave) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.game_leave_title)
                .setMessage(R.string.game_leave_text)
                .setNegativeButton(R.string.game_leave_no, null)
                .setPositiveButton(R.string.game_leave_yes, onLeave);
    }

    private void release() {
        if (!mSt.getInGameSound()) return;
        mSuccessPlayer.release();
        mFailPlayer.release();
    }
}
