package com.kashesoft.cleanarchitecture.app.bl.presenters

import com.kashesoft.cleanarchitecture.app.bl.contracts.PrimaryContract
import com.kashesoft.cleanarchitecture.app.bl.entities.User
import com.kashesoft.cleanarchitecture.app.bl.gateways.base.Params
import com.kashesoft.cleanarchitecture.app.bl.interactors.ReadUserInteractor
import com.kashesoft.cleanarchitecture.app.bl.interactors.base.BaseInteractor
import com.kashesoft.cleanarchitecture.app.bl.presenters.base.BasePresenter
import javax.inject.Inject

/**
 * Copyright (c) 2017 Kashesoft. All rights reserved.
 * Created on 2017-10-15.
 */

class PrimaryPresenter @Inject
constructor(val readUserInteractor: ReadUserInteractor): BasePresenter<PrimaryContract.View>(), PrimaryContract.Presenter,
        ReadUserInteractor.OnResult {

    companion object {
        private val PROGRESS_PERCENT = "PROGRESS_PERCENT"
        private val IN_PROGRESS = "IN_PROGRESS"
    }

    override fun provideInteractors(): Array<BaseInteractor<*, *, *>> {
        return arrayOf(readUserInteractor)
    }

    override fun readUsers(userIds: List<Int>) {
        readUserInteractor.execute(Params.Input.Ids(userIds), this)
        startProgress()
    }

    override fun onCreateView() {
        super.onCreateView()
        if (inProgress()) {
            restartProgress()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (inProgress()) {
            stopProgress()
        }
    }

    override fun readUserOnOutput(output: Params.Output.Entities<User>) {
        updateProgress(output.progress)
    }

    override fun readUserOnSuccess() {
        finishProgress()
    }

    override fun readUserOnFailure(error: Throwable) {
        finishProgress()
        error.printStackTrace()
        // TODO: Add error message
    }

    private fun startProgress() {
        log("start progress!")
        stateBundle.putBoolean(IN_PROGRESS, true)
        stateBundle.putInt(PROGRESS_PERCENT, 0)
        restartProgress()
    }

    private fun restartProgress() {
        log("restart progress!")
        view?.showProgress()
        val percent = stateBundle.getInt(PROGRESS_PERCENT)
        view?.refreshProgress(percent)
    }

    private fun updateProgress(progress: Int) {
        log("update progress: $progress%")
        stateBundle.putInt(PROGRESS_PERCENT, progress)
        view?.refreshProgress(progress)
    }

    private fun stopProgress() {
        log("stop progress!")
        view?.hideProgress()
    }

    private fun finishProgress() {
        log("finish progress!")
        stateBundle.putBoolean(IN_PROGRESS, false)
        stateBundle.putInt(PROGRESS_PERCENT, 0)
        stopProgress()
    }

    private fun inProgress(): Boolean {
        return stateBundle.getBoolean(IN_PROGRESS)
    }

}
