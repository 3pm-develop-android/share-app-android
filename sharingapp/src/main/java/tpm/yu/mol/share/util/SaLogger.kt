package tpm.yu.mol.share.util

import android.util.Log
import tpm.yu.mol.share.BuildConfig

class SaLogger(
    private var isLogger: Boolean = true, var tag: String? = SaLogger::class.simpleName
) {
    companion object {
        val instance = SaLogger()
    }

    private enum class Type {
        VERBOSE, DEBUG, INFO, WARNING, ERROR
    }

    fun v(message: String, tag: String? = this.tag) = print(Type.VERBOSE, tag, message)
    fun d(message: String, tag: String? = this.tag) = print(Type.DEBUG, tag, message)
    fun i(message: String, tag: String? = this.tag) = print(Type.INFO, tag, message)
    fun w(message: String, tag: String? = this.tag) = print(Type.WARNING, tag, message)
    fun e(message: String, tag: String? = this.tag) = print(Type.ERROR, tag, message)

    private fun print(type: Type = Type.VERBOSE, tag: String? = "", message: String = "") {
        if (!isLogger) return
        if (message.isEmpty()) return
        this.tag = tag ?: "Logger";

        val element = Throwable().stackTrace[3]
        val currentPackage = BuildConfig.LIBRARY_PACKAGE_NAME
        val file = element.className.replace("${currentPackage}.", "")
        val method = element.methodName
        val line = element.lineNumber
        val data = "[$file]$method($line) : $message";

        when (type) {
            Type.VERBOSE -> Log.v(this.tag, data)
            Type.DEBUG -> Log.d(this.tag, data)
            Type.INFO -> Log.i(this.tag, data)
            Type.WARNING -> Log.w(this.tag, data)
            Type.ERROR -> Log.e(this.tag, data)
        }
    }
}