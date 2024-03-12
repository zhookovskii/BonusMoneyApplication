package com.example.bonusmoney.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bonusmoney.api.Company
import com.example.bonusmoney.api.CompanyId
import com.example.bonusmoney.api.CustomerMarkParameters
import com.example.bonusmoney.api.LoyaltyLevel
import com.example.bonusmoney.api.MobileAppDashboard

@Entity
data class CompanyEntity(
    @PrimaryKey
    val id: Int,
    val companyId: String,
    val number: Double,
    val name: String,
    val requiredSum: Double,
    val markToCash: Double,
    val cashToMark: Double,
    val mark: Double,
    val companyName: String,
    val logo: String,
    val backgroundColor: String,
    val mainColor: String,
    val cardBackgroundColor: String,
    val textColor: String,
    val highlightTextColor: String,
    val accentColor: String
)

fun CompanyEntity.asCompany(): Company = Company(
    company = CompanyId(companyId),
    customerMarkParameters = CustomerMarkParameters(
        loyaltyLevel = LoyaltyLevel(
            number,
            name,
            requiredSum,
            markToCash,
            cashToMark
        ),
        mark = mark
    ),
    mobileAppDashboard = MobileAppDashboard(
        companyName,
        logo,
        backgroundColor,
        mainColor,
        cardBackgroundColor,
        textColor,
        highlightTextColor,
        accentColor
    )
)