package tpm.yu.mol.share.bin

import android.content.Context
import android.view.View
import tpm.yu.mol.share.util.SaConstant
import tpm.yu.mol.share.util.SaLogger
import tpm.yu.mol.share.view.SaList
import tpm.yu.mol.share.view.SaOne
import tpm.yu.mol.share.view.SaPager

class SaManager {
    private var apps = arrayListOf<AppInfo>()

    companion object {
        val instance = SaManager()
    }

    fun requestApps(url: String) {
        // TODO : Regex check url format

        // TODO: Request

        // TODO : Parse to appInfo
    }

    fun setApps(apps: List<AppInfo>, isAdd: Boolean = false) {
        SaLogger.instance.i("setting application's information")
        if (!isAdd) this.apps.clear()
        this.apps.addAll(apps)
    }

    fun buildView(context: Context, data: SaData): View {
        SaLogger.instance.i("build ${data.viewType} format")
        val size = apps.size
        if (size <= 0) {
            SaLogger.instance.w(SaConstant.Error.emptyList.format("apps"))
            return View(context)
        }
        return when (data.viewType) {
            is SaData.List -> {
                SaList(context).createContent(apps = apps, data = data)
            }
            is SaData.Grid -> {
                SaList(context).createContent(apps = apps, data = data)
            }
            is SaData.Pager -> {
                SaPager(context).createContent(apps = apps, data = data)
            }
            is SaData.One -> {
                val position = (0 until size).random()
                SaOne(context).createContent(app = apps[position], data = data)
            }
        }
    }
}