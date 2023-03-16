package tpm.yu.mol.share.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tpm.yu.mol.share.bin.AppInfo

class MainViewModel : ViewModel() {
    private val gameFI = AppInfo(
        id = 1,
        name = "Found It! Hidden Objects Game",
        logo = "https://play-lh.googleusercontent.com/bB0RQrvcPOJBuQjvC8KXwiamEGWQ5rmignpGfk0sq4vvspkVXE-WgF1OZO1nsrl6EQ=w240-h480-rw",
        packageName = "games.urmobi.found.it",
        bundleId = "games.urmobi.found.it",
        tag = ""
    )
    private val gameFD = AppInfo(
        id = 2,
        name = "Final Destiny",
        logo = "https://play-lh.googleusercontent.com/3O1c-f7HDiIjy6S50uHxc6SU-mY7n-ypkgjm_0MerqZ537ScchLO5sfQsmgMigI_UQ=s48-rw",
        packageName = "gs.hadi.pocketisland",
        bundleId = "gs.hadi.pocketisland",
        tag = ""
    )
    private val gameSCCT = AppInfo(
        id = 3,
        name = "Simon’s Cat Crunch Time",
        logo = "https://play-lh.googleusercontent.com/5PITQJPg3IBQa96HaCY-EQVVMQWKU-chBK0VILc3zjqERYQqtkajxgEWiGT6UCRUMg=w240-h480-rw",
        packageName = "com.strawdogstudios.simonscatcrunchtime",
        bundleId = "com.strawdogstudios.simonscatcrunchtime",
        tag = ""
    )
    private val gameTG = AppInfo(
        id = 4,
        name = "Tile Garden : Tiny Home Design",
        logo = "https://play-lh.googleusercontent.com/5rNP3Dlfn6XyzJy_ba27YvJo1UDjajVoQjniQ2yZU1Bohm4A7nfaJh7AttuP9Sv1cSE=s48-rw",
        packageName = "com.lobstargames.tilematch.garden",
        bundleId = "com.lobstargames.tilematch.garden",
        tag = ""
    )
    private val gameUC = AppInfo(
        id = 5,
        name = "Undead City: Zombie Survivor",
        logo = "https://play-lh.googleusercontent.com/V6v12H-U2QgEv97ZLV1lnQmW4QpwSjhlrYNXIxc_ffWo4gwJe3UOA1Z6lS80XqQtUyQ=s48-rw",
        packageName = "com.unimob.undead.city",
        bundleId = "com.unimob.undead.city",
        tag = ""
    )
    private val gameHR = AppInfo(
        id = 6,
        name = "High Risers",
        logo = "https://play-lh.googleusercontent.com/5CDgpF6pgn7y9lYHJbqHvBiG5ckl-Eia1klnZPRBpwCOdL_fQQExYogqBqEsILn2TQ=s48-rw",
        packageName = "com.kumobius.android.highrisers",
        bundleId = "com.kumobius.android.highrisers",
        tag = ""
    )
    private val games = listOf(gameFI, gameFD, gameSCCT, gameTG, gameUC, gameHR)

    val apps = MutableLiveData<List<AppInfo>>()

    fun dummyApps() {
        val result = arrayListOf<AppInfo>()
        for (index in (1..100)) {
            val position = index % games.size
            result.add(games[position])
        }
        apps.postValue(result)
    }
}