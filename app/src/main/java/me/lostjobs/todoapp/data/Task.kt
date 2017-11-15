package me.lostjobs.todoapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

/**
 * @author lostjobs created on 14/11/2017
 */
@Entity(tableName = "tasks")
data class Task(@PrimaryKey @ColumnInfo(
    name = "entryId") val taskId: String = UUID.randomUUID().toString(), @ColumnInfo(
    name = "title") val mTitle: String?,
    @ColumnInfo(name = "description") val mDescription: String, @ColumnInfo(
        name = "completed")
    val mCompleted: Boolean = false) {

  fun titleForList(): String = mTitle ?: mDescription

  fun isActivate(): Boolean = !mCompleted
}