package com.sandeshshetty.jamlab.framework.presentation

import com.sandeshshetty.jamlab.business.domain.state.Response

interface UIController {

    fun onResponseReceived(
        response: Response
    )

}