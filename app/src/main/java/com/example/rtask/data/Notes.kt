package com.example.rtask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Notes(
            @ColumnInfo(name = "title") val taskTitle:String ,
            @ColumnInfo(name = "description")val taskDetails:String ,
            @ColumnInfo(name = "timestamp")val timeStamp: String,
            @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id :Int = 0

)



