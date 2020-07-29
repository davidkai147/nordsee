package com.ptbc.kotlin_mvvm.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cities")

class City {
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var idCity: Int? = null

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var nameCity: String? = null

    @Expose
    @SerializedName("slug")
    @ColumnInfo(name = "slug")
    var slugCity: String? = null

    @Expose
    @SerializedName("thumbnail")
    @ColumnInfo(name = "thumbnail")
    var thumbnailCity: String? = null

    @Expose
    @SerializedName("is_active")
    @ColumnInfo(name = "is_active")
    var isActiveCity: String? = null

    @Expose
    @SerializedName("is_search")
    @ColumnInfo(name = "is_search")
    var isSearchCity: Int? = null

    @Expose
    @SerializedName("lang")
    @ColumnInfo(name = "lang")
    var langCity: String? = null

    @Expose
    @SerializedName("is_deleted")
    @ColumnInfo(name = "is_deleted")
    var isDeletedCity: Int? = null

    @Expose
    @SerializedName("deleted_at")
    @ColumnInfo(name = "deleted_at")
    var deletedAtCity: Int? = null

    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createdAtCity: Int? = null

    @Expose
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    var updatedAtCity: Int? = null
}