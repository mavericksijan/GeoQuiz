/**
 * Author: Sijan Shrestha
 * Prepared for University of the Sunshine Coast
 * CRN: 1148556
 * Mobile App Project, 2022
 */
package com.example.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)