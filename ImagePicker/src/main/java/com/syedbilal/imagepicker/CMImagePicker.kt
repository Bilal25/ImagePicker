package com.syedbilal.imagepicker

import android.content.Context
import android.content.Intent
import com.syedbilal.imagepicker.activities.ImagePickerMainActivity
import com.syedbilal.imagepicker.utils.BOTH
import com.syedbilal.imagepicker.utils.CAMERA
import com.syedbilal.imagepicker.utils.COMPRESSION_PERCENTAGE
import com.syedbilal.imagepicker.utils.GALLERY
import com.syedbilal.imagepicker.utils.ResultImage
import com.syedbilal.imagepicker.utils.SELECTION_TYPE
import com.syedbilal.imagepicker.utils.WANT_COMPRESSION
import com.syedbilal.imagepicker.utils.WANT_CROP

class CMImagePicker(
    private val activity: Context,
    private var resultImageCallback: ResultImage

) {
    private var crop: Boolean = false
    private var cameraOnly: Boolean = false
    private var galleryOnly: Boolean = false
    private var compress: Boolean = false
    private var compressionPercentage: Int = 100


    fun allowCrop(crop: Boolean): CMImagePicker {
        this.crop = crop
        return this
    }

    fun allowCameraOnly(cameraOnly: Boolean): CMImagePicker {
        this.cameraOnly = cameraOnly
        return this
    }

    fun allowGalleryOnly(galleryOnly: Boolean): CMImagePicker {
        this.galleryOnly = galleryOnly
        return this
    }

    fun allowCompress(compress: Boolean, compressionPercentage: Int): CMImagePicker {
        this.compress = compress
        this.compressionPercentage = compressionPercentage
        return this
    }

    fun start() {
        val selection = if (cameraOnly && !galleryOnly) {
            CAMERA
        } else if (galleryOnly && !cameraOnly) {
            GALLERY
        } else {
            BOTH
        }

        resultImageCallback.result.launch(
            Intent(
                activity,
                ImagePickerMainActivity::class.java
            ).apply {
                putExtra(WANT_CROP, crop)
                putExtra(SELECTION_TYPE, selection)
                putExtra(WANT_COMPRESSION, compress)
                putExtra(COMPRESSION_PERCENTAGE, compressionPercentage)
            }
        )
    }


}

