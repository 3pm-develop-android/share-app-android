package tpm.yu.mol.share.bin

import android.content.Context
import android.view.View
import tpm.yu.mol.share.util.SaLogger
import tpm.yu.mol.share.view.SaList

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

    fun buildViewAllApp(context: Context, data: SaView): View {
        return when (data.viewType) {
            is SaView.List -> {
                SaLogger.instance.i("build list format")
                SaList(context).createContent(apps = apps, data = data)
            }
            is SaView.Grid -> {
                SaLogger.instance.i("build grid format")
                SaList(context).createContent(apps = apps, data = data)
            }
        }
    }

    fun buildViewOneApp(context: Context) {

    }
}