package com.example.lastfmapp.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(actionId: Int, args: Bundle? = null, navOptions: NavOptions? = null) { findNavController().navigate(actionId, args, navOptions) }
