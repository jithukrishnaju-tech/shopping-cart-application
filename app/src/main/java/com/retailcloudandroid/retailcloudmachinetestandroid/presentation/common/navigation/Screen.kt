package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.navigation

sealed class Screen(val route: String) {
    object ItemList : Screen("item_list")
    object Cart : Screen("cart")
}
