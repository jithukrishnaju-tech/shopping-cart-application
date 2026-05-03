package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.utils

import java.util.Locale

fun Double.toPrice(): String = "$${String.format(Locale.US, "%.2f", this)}"
