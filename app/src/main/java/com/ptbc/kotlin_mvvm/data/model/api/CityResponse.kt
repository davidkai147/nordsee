package com.ptbc.kotlin_mvvm.data.model.api

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResponse {

    @Expose
    @SerializedName("data")
    val data: List<Cities>? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is CityResponse) {
            return false
        }

        val that = other as CityResponse?

        return data == that?.data
    }

    override fun hashCode(): Int {
        var result = data!!.hashCode()
        return result
    }

    class Cities {

        @Expose
        @SerializedName("id")
        var id: Int? = null

        @Expose
        @SerializedName("name")
        var nameCity: String? = null

        @Expose
        @SerializedName("slug")
        var slugCity: String? = null

        @Expose
        @SerializedName("thumbnail")
        var thumbnailCity: String? = null

        @Expose
        @SerializedName("is_active")
        var isActiveCity: String? = null

        @Expose
        @SerializedName("is_search")
        var isSearchCity: Int? = null

        @Expose
        @SerializedName("lang")
        var langCity: String? = null

        @Expose
        @SerializedName("is_deleted")
        var isDeletedCity: Int? = null

        @Expose
        @SerializedName("deleted_at")
        var deletedAtCity: Int? = null

        @Expose
        @SerializedName("created_at")
        var createdAtCity: Int? = null

        @Expose
        @SerializedName("updated_at")
        var updatedAtCity: Int? = null

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other !is Cities) {
                return false
            }

            val city = other as Cities?

            if (slugCity != city!!.slugCity) {
                return false
            }
            if (thumbnailCity != city.thumbnailCity) {
                return false
            }
            if (isActiveCity != city.isActiveCity) {
                return false
            }
            if (isSearchCity != city.isSearchCity) {
                return false
            }
            if (langCity != city.langCity) {
                return false
            }
            if (isDeletedCity!= city.isDeletedCity) {
                return false
            }
            if (deletedAtCity != city.deletedAtCity) {
                return false
            }
            if (createdAtCity != city.createdAtCity) {
                return false
            }
            if (updatedAtCity != city.updatedAtCity) {
                return false
            }
            return if (id != city.id) {
                false
            } else nameCity == city.nameCity

        }

        override fun hashCode(): Int {
            var result = slugCity!!.hashCode()
            result = 31 * result + thumbnailCity!!.hashCode()
            result = 31 * result + isActiveCity!!.hashCode()
            result = 31 * result + isSearchCity!!.hashCode()
            result = 31 * result + langCity!!.hashCode()
            result = 31 * result + isDeletedCity!!.hashCode()
            result = 31 * result + deletedAtCity!!.hashCode()
            result = 31 * result + createdAtCity!!.hashCode()
            result = 31 * result + updatedAtCity!!.hashCode()
            return result
        }
    }

}