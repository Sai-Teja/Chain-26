package xyz.teja.base

import android.content.Context
import org.koin.core.KoinComponent
import org.koin.core.get

object AppConfig: KoinComponent {
    val DEBUG: Boolean = getFieldFromGradle(get(), "DEBUG") as Boolean
    const val DATABASE_NAME = "app_database"
    const val BASE_URL = "https://api.blockchain.info/"

    private fun getFieldFromGradle(context: Context, fieldName: String): Any? {
        return getStaticFieldValue(resolveBuildConfigClass(context), fieldName)
    }

    private fun resolveBuildConfigClass(context: Context): Class<*>? {
        val resId: Int = context.resources.getIdentifier(
            "build_config_package",
            "string",
            context.packageName
        )

        return if (resId != 0) loadClass(context.getString(resId) + ".BuildConfig")
        else loadClass(context.packageName.toString() + ".BuildConfig")
    }

    private fun loadClass(className: String): Class<*>? {
        try {
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getStaticFieldValue(
        clazz: Class<*>?,
        fieldName: String
    ): Any? {
        try {
            return clazz!!.getField(fieldName)[null]
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }
}