package com.sandeshshetty.jamlab.framework.presentation.consultation

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sandeshshetty.jamlab.R
import com.sandeshshetty.jamlab.databinding.FragmentDoctorDetailBinding
import com.sandeshshetty.jamlab.framework.permission.Permission
import com.sandeshshetty.jamlab.framework.permission.PermissionManager
import com.sandeshshetty.jamlab.framework.presentation.UIController
import com.sandeshshetty.jamlab.utils.displayToast
import com.sandeshshetty.jamlab.utils.printLogD
import com.sandeshshetty.jamlab.utils.setUiController
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import io.agora.rtc.video.VideoEncoderConfiguration


class DoctorDetailFragment : Fragment() {

    val args: DoctorDetailFragmentArgs by navArgs()

    private var _binding: FragmentDoctorDetailBinding? = null
    private val binding get() = _binding!!

    private val doctorDetailViewModel: DoctorDetailViewModel by viewModels()

    private var uiController: UIController? = null

    private val permissionManager = PermissionManager.from(this)

    private var mRtcEngine: RtcEngine? = null

    private var mRtcEventHandler = object : IRtcEngineEventHandler() {

        override fun onUserJoined(uid: Int, elapsed: Int) {
            activity?.runOnUiThread {
                setUpRemoteVideo(uid)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        permissionManager
            .request(Permission.MandatoryFeatureForVideoCalling)
            .rationale("Necessary for this to run")
            .checkPermission { granted ->
                if (granted) {
                    displayToast("Success")
                } else {
                    displayToast("Still mission some permission")
                }
            }

        args.doctor?.let {
            displayToast(it.fname.toString())
//            initializeAndJoinChannel()
//            setupVideoProfile(it.id)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        uiController = requireActivity().setUiController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRtcEngine!!.leaveChannel()
        RtcEngine.destroy()
        mRtcEngine = null
    }

//    fun onClickRequestPermission(permissions: String){
//        when{
//            ContextCompat.checkSelfPermission(
//                requireContext(),
//                permissions
//            ) == PackageManager.PERMISSION_GRANTED ->{
//
//            }
//
//            ActivityCompat.shouldShowRequestPermissionRationale(
//                requireActivity(),
//                permissions
//            ) -> {
//                uiController?.onResponseReceived(
//                    response = Response(
//                        message = "Permission needed for smooth functionality",
//                        uiComponentType = UIComponentType.AreYouSureDialog(
//                            object : AreYouSureCallback {
//                                override fun proceed() {
//                                    requestPermissionLauncher.launch(
//                                        permissions
//                                    )
//                                }
//
//                                override fun cancel() {
//
//                                }
//
//                            }
//                        ),
//                        messageType = MessageType.Info()
//                    )
//                )
//            }
//            else -> {
//                requestPermissionLauncher.launch(
//                    permissions
//                )
//            }
//        }
//    }


    fun onLocalVideoMuteClicked(view: View) {
        val iv = view as ImageView
        if (iv.isSelected) {
            iv.isSelected = false
            iv.clearColorFilter()
        } else {
            iv.isSelected = true
            iv.setColorFilter(
                resources.getColor(R.color.colorPrimary),
                PorterDuff.Mode.MULTIPLY
            )
        }

        // Stops/Resumes sending the local video stream.
        mRtcEngine!!.muteLocalVideoStream(iv.isSelected)

        val container = binding.localVideoViewContainer as FrameLayout
        val surfaceView = container.getChildAt(0) as SurfaceView
        surfaceView.setZOrderMediaOverlay(!iv.isSelected)
        surfaceView.visibility = if (iv.isSelected) View.GONE else View.VISIBLE
    }

    fun onLocalAudioMuteClicked(view: View) {
        val iv = view as ImageView
        if (iv.isSelected) {
            iv.isSelected = false
            iv.clearColorFilter()
        } else {
            iv.isSelected = true
            iv.setColorFilter(
                resources.getColor(R.color.colorPrimary),
                PorterDuff.Mode.MULTIPLY
            )
        }

        // Stops/Resumes sending the local audio stream.
        mRtcEngine!!.muteLocalAudioStream(iv.isSelected)
    }

    fun onSwitchCameraClicked(view: View) {
        // Switches between front and rear cameras.
        mRtcEngine!!.switchCamera()
    }

    fun onEndCallClicked(view: View) {
        findNavController().popBackStack()
    }

    fun initializeAndJoinChannel() {
        try {
            mRtcEngine =
                RtcEngine.create(context, getString(R.string.agora_app_id), mRtcEventHandler)
        } catch (e: Exception) {
            printLogD(this.javaClass.simpleName, e.localizedMessage)
        }


    }

    private fun setupVideoProfile(uid: Int) {
        // In simple use cases, we only need to enable video capturing
        // and rendering once at the initialization step.
        // Note: audio recording and playing is enabled by default.
        mRtcEngine!!.enableVideo()
//      mRtcEngine!!.setVideoProfile(Constants.VIDEO_PROFILE_360P, false) // Earlier than 2.3.0

        // Please go to this page for detailed explanation
        // https://docs.agora.io/en/Video/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_rtc_engine.html#af5f4de754e2c1f493096641c5c5c1d8f
        mRtcEngine!!.setVideoEncoderConfiguration(
            VideoEncoderConfiguration(
                VideoEncoderConfiguration.VD_640x360,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
            )
        )

        val localFrame = RtcEngine.CreateRendererView(context)
        localFrame.setZOrderMediaOverlay(true)
        binding.localVideoViewContainer.addView(localFrame)
        mRtcEngine!!.setupLocalVideo(VideoCanvas(localFrame, VideoCanvas.RENDER_MODE_FIT, uid))

        mRtcEngine!!.joinChannel(
            getString(R.string.agora_access_token),
            "demoChannel",
            "Extra Optional",
            uid
        )
    }

    fun setUpRemoteVideo(uid: Int) {
        val remoteFrame = RtcEngine.CreateRendererView(context)
        remoteFrame.setZOrderMediaOverlay(true)
        binding.remoteVideoViewContainer.addView(remoteFrame)
        mRtcEngine!!.setupRemoteVideo(
            VideoCanvas(
                remoteFrame,
                VideoCanvas.RENDER_MODE_FIT,
                uid
            )
        )
    }

    companion object {
        private const val PERMISSION_RECORD_AUDIO = 22
        private const val PERMISSION_CAMERA = PERMISSION_RECORD_AUDIO + 1
    }

}