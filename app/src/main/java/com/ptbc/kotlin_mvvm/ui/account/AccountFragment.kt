package com.ptbc.kotlin_mvvm.ui.account

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ptbc.kotlin_mvvm.R
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_account.view.*
import java.io.FileNotFoundException
import java.io.InputStream

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.fragment_account, container, false)

        setUpView(view)

        return view
    }

    private fun setUpView(view: View) {
        view.cicrle_imageview_profile.setOnClickListener {
            pickImage()
        }
    }

    private fun pickImage() {
        val intent = Intent(CropImage.getPickImageChooserIntent(requireContext()))
        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                val result = CropImage.getPickImageResultUri(requireContext(),data)
                try {
                    if (result!=null){
                        val inputStream: InputStream =
                            requireContext().contentResolver.openInputStream(result)
                        val photoBitmap = BitmapFactory.decodeStream(inputStream)
                        view?.cicrle_imageview_profile?.setImageBitmap(photoBitmap)
                    }

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        fun newInstance() = AccountFragment()
    }
}