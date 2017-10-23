package com.kashesoft.cleanarchitecture.app.al.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.View
import com.kashesoft.cleanarchitecture.R
import com.kashesoft.cleanarchitecture.app.al.Viewable
import com.kashesoft.cleanarchitecture.app.al.activities.base.BaseActivity
import com.kashesoft.cleanarchitecture.app.bl.contracts.PrimaryContract
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

@Viewable(layout = R.layout.activity_main)
class PrimaryActivity : BaseActivity<PrimaryContract.View, PrimaryContract.Presenter>(),
        PrimaryContract.View, View.OnClickListener {

    private lateinit var progressDialog: ProgressDialog
    @Inject override lateinit var presenterProvider: Provider<PrimaryContract.Presenter>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
    }

    override fun onClick(v: View) {
        val userIds = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        presenter.readUsers(userIds)
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun refreshProgress(percent: Int) {
        progressDialog.progress = percent
    }

    private fun setUpView() {
        //
        button.setOnClickListener(this)
        //
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog.setCancelable(false)
        progressDialog.progress = 0
        this.progressDialog = progressDialog
    }

}
