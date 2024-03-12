package com.example.bonusmoney.api

import com.example.bonusmoney.db.CompanyEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Companies(
    @field:Json(name = "companies")
    val companies: List<Company>
)

@JsonClass(generateAdapter = true)
data class Company(
    @field:Json(name = "company")
    val company: CompanyId,
    @field:Json(name = "customerMarkParameters")
    val customerMarkParameters: CustomerMarkParameters,
    @field:Json(name = "mobileAppDashboard")
    val mobileAppDashboard: MobileAppDashboard
)

@JsonClass(generateAdapter = true)
data class CompanyId(
    @field:Json(name = "companyId")
    val companyId: String
)

@JsonClass(generateAdapter = true)
data class CustomerMarkParameters(
    @field:Json(name = "loyaltyLevel")
    val loyaltyLevel: LoyaltyLevel,
    @field:Json(name = "mark")
    val mark: Double
)

@JsonClass(generateAdapter = true)
data class LoyaltyLevel(
    @field:Json(name = "number")
    val number: Double,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "requiredSum")
    val requiredSum: Double,
    @field:Json(name = "markToCash")
    val markToCash: Double,
    @field:Json(name = "cashToMark")
    val cashToMark: Double
)

@JsonClass(generateAdapter = true)
data class MobileAppDashboard(
    @field:Json(name = "companyName")
    val companyName: String,
    @field:Json(name = "logo")
    val logo: String,
    @field:Json(name = "backgroundColor")
    val backgroundColor: String,
    @field:Json(name = "mainColor")
    val mainColor: String,
    @field:Json(name = "cardBackgroundColor")
    val cardBackgroundColor: String,
    @field:Json(name = "textColor")
    val textColor: String,
    @field:Json(name = "highlightTextColor")
    val highlightTextColor: String,
    @field:Json(name = "accentColor")
    val accentColor: String
)

fun Company.asEntity(id: Int): CompanyEntity = CompanyEntity(
    id = id,
    companyId = company.companyId,
    number = customerMarkParameters.loyaltyLevel.number,
    name = customerMarkParameters.loyaltyLevel.name,
    requiredSum = customerMarkParameters.loyaltyLevel.requiredSum,
    markToCash = customerMarkParameters.loyaltyLevel.markToCash,
    cashToMark = customerMarkParameters.loyaltyLevel.cashToMark,
    mark = customerMarkParameters.mark,
    companyName = mobileAppDashboard.companyName,
    logo = mobileAppDashboard.logo,
    backgroundColor = mobileAppDashboard.backgroundColor,
    mainColor = mobileAppDashboard.mainColor,
    cardBackgroundColor = mobileAppDashboard.cardBackgroundColor,
    textColor = mobileAppDashboard.textColor,
    highlightTextColor = mobileAppDashboard.highlightTextColor,
    accentColor = mobileAppDashboard.accentColor
)
